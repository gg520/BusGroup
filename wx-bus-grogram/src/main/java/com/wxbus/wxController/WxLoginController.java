package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.UserInfo;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.UserService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.*;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by g1154 on 2018/4/14.
 */
@RestController
@RequestMapping("/weixin/auth")
public class WxLoginController {
    private final Log logger = LogFactory.getLog(WxLoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private WxMaService wxService;
    /**
     * 微信登录
     *
     * @param body 请求内容，{ code: xxx, userInfo: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      errno: 0,
     *      errmsg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { errno: XXX, errmsg: XXX }
     */
    @RequestMapping(value = "loginByWeixin",method = {RequestMethod.POST})
    public Object loginByWeixin(@RequestBody String body, HttpServletRequest request) {

        logger.info("微信用户登录");

        String code = JacksonUtil.parseString(body, "code");

        if(code.equals("the code is a mock one")){
            return ResponseUtil.badArgument();
        }
        FullUserInfo fullUserInfo = JacksonUtil.parseObject(body, "userInfo", FullUserInfo.class);
        if(code == null || fullUserInfo == null){
            return ResponseUtil.badArgument();
        }

        UserInfo userInfo = fullUserInfo.getUserInfo();

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        if(sessionKey == null || openId == null){
            return ResponseUtil.fail();
        }

        //验证用户信息完整性
        if (!this.wxService.getUserService().checkUserInfo(sessionKey, fullUserInfo.getRawData(), fullUserInfo.getSignature())) {
            return ResponseUtil.fail();
        }

        // 解密用户信息
//        WxMaUserInfo wxMaUserInfo = this.wxService.getUserService().getUserInfo(sessionKey, fullUserInfo.getEncryptedData(), fullUserInfo.getIv());

        Passenger user = userService.queryByOid(openId);//未获取到用户，将以新用户插入其中
        if(user == null){
            user = new Passenger();
            user.setPassengerNickname(userInfo.getNickName());  // 其实没有用，因为用户没有真正注册
            user.setWeixinOpenid(openId);
            user.setPassengerAvater(userInfo.getAvatarUrl());
            user.setPassengerGender(userInfo.getGender() == 1 ? "男" : "女");
            user.setPassengerStatus(0);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            user.setFistLoginIp(IpUtil.client(request));
            user.setFistLoginTime(new Date());
//            user.setPassengerPassword();
            userService.add(user);
        }
        else{
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            userService.update(user);
        }


        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId(),"乘客");

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }

    @RequestMapping(value = "test/{id}")
    public Object test(@PathVariable("id")Integer id){
//        System.err.println(id);
        return id;
    }

//    @PostMapping(value = "login")
//    public Object login(@RequestBody String body){
//        System.out.println(body);
//        logger.info("登录");
//        return ResponseUtil.ok("登录成功");
//    }
    /**
     *@type method
     *@parameter  [passenger]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/25
     *@describe 微信账号密码登录
     */
    @RequestMapping(value ="/login",method = {RequestMethod.POST})
    public Object loginByMoPaw(@RequestBody String body, HttpServletRequest request,
                               HttpServletResponse response){
        logger.info("微信账号密码登录");

        String username= JacksonUtil.parseString(body, "username");
        String password= JacksonUtil.parseString(body, "password");

        if(username==null||"".equals(username)||"".equals(password)||password==null){
            return ResponseUtil.fail(-1,"账号或密码不能为空");
        }

        password = MD5Util.toMD5(password);

        String userInfo= userService.findUserByMoPaw(username,password);

        if(userInfo==null||"".equals(userInfo)){
            return ResponseUtil.fail(-1,"账号或密码错误");
        }else{
//            String Connect_Platform=JacksonUtil.parseString(userInfo,"Connect_Platform");

            String Connect_Platform= JacksonUtil.parseString(userInfo,"Connect_Platform");

            String userId= JacksonUtil.parseString(userInfo,"userId");

//            UserInfo userInfo1=(UserInfo) JacksonUtil.parseObject(userInfo,"userInfo",UserInfo.class);



            response.setHeader("Connect_Platform",Connect_Platform);

            UserToken userToken=null;
            //将数据添加到token
            if("Weixin_Driver".equals(Connect_Platform)){//司机
                 userToken= UserTokenManager.generateToken(Integer.valueOf(userId),"司机");
            }else if("Weixin_Passenger".equals(Connect_Platform)){
                userToken= UserTokenManager.generateToken(Integer.valueOf(userId),"乘客");
            }
            //返回数据到前台中
            //昵称和token
            Map map=new HashMap();
            map.put("username", JacksonUtil.parseString(userInfo,"username"));
            map.put("token",userToken.getToken());
            map.put("userInfo", JacksonUtil.parseObject(userInfo,"userInfo",UserInfo.class));
            System.out.println("userInfo : "+ JacksonUtil.parseObject(userInfo,"userInfo",UserInfo.class));
            return ResponseUtil.ok(map);
        }
    }
    /**
     *@type method
     *@parameter  [passenger]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/26
     *@describe 微信用户注册
     */
//    @RequestMapping(value ="/register",method = {RequestMethod.POST})
//    public Object register(@RequestBody Driver driver, HttpServletRequest request){
//        logger.info("用户注册");
//        Date date = new Date();
//        driver.setRegisterTime(date);
//        driver.setRegisterIp(IpUtil.client(request));
//
//        passenger.setFistLoginTime(date);
//        passenger.setFistLoginIp(IpUtil.client(request));
//        passenger.setWeixinOpenid(CharUtil.getRandomString(8));
//        passenger.setDeleted(0);
//        if(passenger!=null){
//            if(passenger.getPassengerMobile()==null||passenger.getPassengerPassword()==null ||
//                    passenger.getPassengerGender()==null||passenger.getPassengerAvater()==null){
//                return ResponseUtil.fail();
//
//            }
//            userService.addPassenger(passenger);
//            return ResponseUtil.ok();
//        }
//        else {
//            return ResponseUtil.fail();
//        }
//    }

    /**
     *@type method
     *@parameter  [passenger]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/26
     *@describe 更新用户信息用于更改密码
     */
    @RequestMapping(value ="/reset",method = {RequestMethod.GET})
    public Object reset(Passenger passenger){
        logger.info("更新用户");
        if(passenger!=null){
            if(passenger.getPassengerMobile()!=null&&passenger.getPassengerPassword()!=null){
                userService.updatePassenger(passenger);
                return ResponseUtil.ok();
            }
            else{
                return ResponseUtil.fail();
            }
        }
        else {
            return ResponseUtil.fail();
        }

    }

}
