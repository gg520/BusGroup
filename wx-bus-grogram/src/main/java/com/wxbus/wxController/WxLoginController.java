package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.wxbus.daomain.Driver;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.ResponseUserInfo;
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

        UserInfo userInfo =  fullUserInfo.getUserInfo();

        ResponseUserInfo responseUserInfo = new ResponseUserInfo();

        responseUserInfo.setUserInfo(userInfo);

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
            user.setPassengerNickname(responseUserInfo.getNickName());  // 其实没有用，因为用户没有真正注册
            user.setWeixinOpenid(openId);
            user.setPassengerAvater(responseUserInfo.getAvatarUrl());
            user.setPassengerGender(responseUserInfo.getGender() == 1 ? "男" : "女");
            user.setPassengerStatus(0);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            user.setFistLoginIp(IpUtil.client(request));
            user.setFistLoginTime(new Date());
//            user.setPassengerPassword();
            user.setId(userService.add(user));
            //获取插入后的id

        }
        else{
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
//            responseUserInfo.setAddress();
            responseUserInfo.setNickName(user.getPassengerNickname());
            responseUserInfo.setCitizenship(user.getPassengerCitizenship());
            responseUserInfo.setMobile(user.getPassengerMobile());
            responseUserInfo.setBirthday(TimeUtil.getTimeByType(user.getPassengerBirthday(),"yyyy-MM-dd"));

            responseUserInfo.setAddress(user.getPassengerAddress());
            responseUserInfo.setName(user.getPassengerName());
            userService.update(user);
        }


        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId(),"乘客");

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        //设置几个数据

        result.put("userInfo", responseUserInfo);
        //返回的数据信息需要更改
        return ResponseUtil.ok(result);
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

        String username= JacksonUtil.parseString(body, "username").trim();
        String password= JacksonUtil.parseString(body, "password").trim();

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
            map.put("userInfo", JacksonUtil.parseObject(userInfo,"userInfo",ResponseUserInfo.class));
            System.out.println("userInfo : "+ JacksonUtil.parseObject(userInfo,"userInfo",ResponseUserInfo.class));
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
    @RequestMapping(value ="/register",method = {RequestMethod.POST})
    public Object register(@RequestBody String boby, HttpServletRequest request){
        logger.info("司机用户注册"+boby);
        Date date = new Date();
        Driver driver=new Driver();
        driver.setRegisterTime(date);
        driver.setRegisterIp(IpUtil.client(request));
//        boby=JacksonUtil.parseString(boby,"userInfo");
        driver.setDriverName(JacksonUtil.parseString(boby,"name"));
        driver.setDriverPassword(MD5Util.toMD5(JacksonUtil.parseString(boby,"password").trim()));
        driver.setDriverMobile(JacksonUtil.parseString(boby,"mobile"));
        driver.setDriverCitizenship(JacksonUtil.parseString(boby,"citizenship"));//身份证号码
        driver.setDriverLicence(JacksonUtil.parseString(boby,"licence"));//驾驶证号码
        driver.setDriverGender(JacksonUtil.parseString(boby,"gender"));
        driver.setBirthday(TimeUtil.getTimeByString(JacksonUtil.parseString(boby,"birthday"),"yyyy-MM-dd"));

        driver.setFirstGetlicence(TimeUtil.getTimeByString(JacksonUtil.parseString(boby,"firstgetlicence"),"yyyy-MM-dd"));

        driver.setDrivingType(JacksonUtil.parseString(boby,"bustype"));

//        driver.setDriverNationality(JacksonUtil.parseString(boby,"nationality"));
        driver.setDriverAddress(JacksonUtil.parseString(boby,"address"));
        driver.setDriverAvater(JacksonUtil.parseString(boby,"avatarUrl"));//头像

        if(driver!=null){
            if(driver.getDriverName()==null||driver.getDriverMobile()==null ||
                    driver.getDriverGender()==null||driver.getDriverPassword()==null){
                return ResponseUtil.fail();

            }

            if(userService.checkDriverByDriver(driver)){
                return ResponseUtil.fail(301,"账号已被注册");
            }

            //查询服务器

            int num=0;
            while (true){
                num++;
                if(num%10==0){
                    UserTokenManager.addDriverIdLength();
                }
                driver.setDriverId(UUIDUtil.getDriverId(UserTokenManager.getDriverIdLength()));//生成ID
                if(userService.findDriverById(driver.getDriverId())==null){
                    break;
                }
            }


            userService.addDriver(driver);

            Map map=new HashMap();
            map.put("driverId",driver.getDriverId());
            return ResponseUtil.ok(map);
        }
        else {
            return ResponseUtil.fail();
        }
    }

    /**
     *@type method
     *@parameter  [passenger]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/26
     *@describe 更新用户信息用于更改密码
     */
    @RequestMapping(value ="/reset",method = {RequestMethod.POST})
    public Object reset(@RequestBody String body){
        logger.info("更新用户");
        String mobile=JacksonUtil.parseString(body,"mobile");
        String password=JacksonUtil.parseString(body,"password");
        String code = JacksonUtil.parseString(body,"code");//验证码暂不处理，该功能未实现
        if(mobile!=null&&!"".equals(mobile)&&password!=null&&!"".equals(password)){
            password=MD5Util.toMD5(password);
            Passenger passenger=new Passenger();
            passenger.setPassengerMobile(mobile);
            passenger.setPassengerPassword(password);
            userService.updatePassenger(passenger);
            return ResponseUtil.ok();
        }
        else {
            return ResponseUtil.fail();
        }

    }

}
