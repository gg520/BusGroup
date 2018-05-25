package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.UserInfo;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.UserService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.IpUtil;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by g1154 on 2018/4/14.
 */
@RestController
@RequestMapping("/weixin/auth")
public class LoginController {
    private final Log logger = LogFactory.getLog(LoginController.class);

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
            userService.add(user);
        }
        else{
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            userService.update(user);
        }


        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

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

    @PostMapping(value = "login")
    public Object login(@RequestBody String body){
        System.out.println(body);
        logger.info("登录");
        return ResponseUtil.ok("登录成功");
    }
}
