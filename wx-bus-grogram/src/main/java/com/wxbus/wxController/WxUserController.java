package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.sun.net.httpserver.HttpsServer;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.ResponseUserInfo;
import com.wxbus.pojo.UserInfo;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.MD5Util;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.TimeUtil;
import me.chanjar.weixin.common.exception.WxErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Demon
 * @date: 2018/6/20
 * @time: 11:31
 * Description:
 */
@RestController
@RequestMapping(value = "/weixin/user")
public class WxUserController {
    private final Log logger= LogFactory.getLog(WxUserController.class.getName());
    @Autowired
    private UserService userService;
    @RequestMapping(value = "changeUserInfo",method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/20
     *@describe 修改个人信息
     */
    public Object changeUserInfo(@RequestBody String body, HttpServletRequest request){
        logger.info("修改个人信息");
        ResponseUserInfo responseUserInfo= JacksonUtil.parseObject(body,"userInfo",ResponseUserInfo.class);
        if(responseUserInfo==null){
            return ResponseUtil.badArgument();
        }
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if(!"乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail302();
        }

        Passenger passenger=new Passenger();
        passenger.setId(JacksonUtil.parseInteger(json,"userId"));
        passenger.setPassengerCitizenship(responseUserInfo.getCitizenship());
        passenger.setPassengerNickname(responseUserInfo.getNickName());
        passenger.setPassengerAvater(responseUserInfo.getAvatarUrl());
        passenger.setPassengerAddress(responseUserInfo.getAddress());
        if(responseUserInfo.getGender()==0){
            passenger.setPassengerGender("女");
        }else{
            passenger.setPassengerGender("男");
        }
        passenger.setPassengerMobile(responseUserInfo.getMobile());
        passenger.setPassengerName(responseUserInfo.getName());
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");

//            Date date=simpleDateFormat.parse(responseUserInfo.getBirthday());
        passenger.setPassengerBirthday(TimeUtil.getTimeByString(responseUserInfo.getBirthday(),"yyyy-MM-dd"));

        Integer flag=userService.updatePassenger(passenger);
        if(flag==0){
            return ResponseUtil.fail502();
        }
        Map map=new HashMap();
        map.put("userInfo", responseUserInfo);

        return ResponseUtil.ok(map);
    }
    @RequestMapping(value = "changePassword",method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/21
     *@describe 更改密码
     */
    public Object changePassword(@RequestBody String body,HttpServletRequest request){
        logger.info("更改密码");
        String json=UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if(!"乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail302();
        }

        Integer passengerId=JacksonUtil.parseInteger(json,"userId");
        Passenger passenger=userService.findById(passengerId);
        String oldPassword=JacksonUtil.parseString(body,"oldPassword");
        String newPassword=JacksonUtil.parseString(body,"newPassword");
        oldPassword=MD5Util.toMD5(oldPassword);
        newPassword=MD5Util.toMD5(newPassword);
        if(!passenger.getPassengerPassword().equals(oldPassword)){
            return ResponseUtil.fail502();
        }
        passenger.setPassengerPassword(newPassword);
        userService.updatePassenger(passenger);
        return ResponseUtil.ok("更改成功");

    }
}