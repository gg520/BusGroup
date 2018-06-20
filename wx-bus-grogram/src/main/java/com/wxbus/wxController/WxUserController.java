package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.sun.net.httpserver.HttpsServer;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.ResponseUserInfo;
import com.wxbus.pojo.UserInfo;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.UserService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.MD5Util;
import com.wxbus.util.ResponseUtil;
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
    private static Map<String, UserToken> tokenMap = new HashMap<>();
    @Autowired
    private UserService userService;
//    @RequestMapping(value = "changeUserInfo",method = RequestMethod.POST)

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
        UserToken userToken=tokenMap.get(request.getHeader("X-Bus-Token"));
        if(userToken.getUser().equals("司机")){
            return ResponseUtil.fail(500,"司机没有此权限");
        }
        Passenger passenger=new Passenger();
        passenger.setId(userToken.getUserId());
        passenger.setPassengerCitizenship(responseUserInfo.getCitizenship());
        passenger.setPassengerNickname(responseUserInfo.getNickName());
        passenger.setPassengerAvater(responseUserInfo.getAvatarUrl());
        passenger.setPassengerAddress(responseUserInfo.getAddress());
        if(responseUserInfo.getGender()==0){
            passenger.setPassengerGender("女");
        }
        passenger.setPassengerGender("男");
        passenger.setPassengerMobile(responseUserInfo.getMobile());
        passenger.setPassengerName(responseUserInfo.getName());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=simpleDateFormat.parse(responseUserInfo.getBirthday());
            passenger.setPassengerBirthday(date);
        }
        catch (ParseException pe){
            pe.printStackTrace();
        }
        Integer flag=userService.updatePassenger(passenger);
        if(flag==0){
            return ResponseUtil.fail(500,"更新失败");
        }
        return ResponseUtil.ok("更新成功");
    }
}