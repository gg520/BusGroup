package com.wxbus.wxController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.sun.net.httpserver.HttpsServer;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.FullUserInfo;
import com.wxbus.pojo.UserInfo;
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
//    @RequestMapping(value = "changeUserInfo",method = RequestMethod.POST)

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/20
     *@describe 修改个人信息
     */
    /*public Object changeUserInfo(@RequestBody String body, HttpServletRequest request){
        logger.info("修改个人信息");
        Passenger passenger= JacksonUtil.parseObject(body,"userInfo",Passenger.class);
        if(passenger==null){
            return ResponseUtil.badArgument();
        }
        String password=passenger.getPassengerPassword();
        if(password==null||"".equals(password)){
            return ResponseUtil.fail(500,"密码不能为空");
        }
        password= MD5Util.toMD5(password);
        if(passenger.getPassengerMobile()==null||"".equals(passenger.getPassengerMobile())){
            return ResponseUtil.fail(500,"手机号不能为空");
        }
        Integer flag=userService.updatePassenger(passenger);
        if(flag==0){
            return ResponseUtil.fail(500,"手机密码不匹配更新失败");
        }
        return ResponseUtil.ok("更新成功");
    }*/
}