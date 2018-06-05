package com.wxbus.webController;


import com.wxbus.daomain.Admin;
import com.wxbus.service.AdminService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.MD5Util;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * @author: Demon
 * @date: 2018/5/31
 * @time: 18:58
 * Description:
 */
@Controller
@RequestMapping("/web/login")
public class WebLoginController {
    private final Log logger = LogFactory.getLog(com.wxbus.wxController.LoginController.class);
    @Autowired
    private AdminService adminService;
    @RequestMapping(value ="/webLogin",method = {RequestMethod.POST})
    /**
     *@type method
     *@parameter  [body, response]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 登陆
     */
    @ResponseBody
    public String loginByNamePaw(@RequestBody String body, HttpServletRequest request,
                                 HttpSession session){
        String userName= JacksonUtil.parseString(body,"userName");
        String password= JacksonUtil.parseString(body,"password");
        if(userName==null||password==null||"".equals(userName)||"".equals(password)){
            logger.info("用户名密码不能为空,请重新登陆");
            request.setAttribute("message","用户名密码不能为空,请重新登陆");
            return "forward:/web/login";
        }
        password= MD5Util.toMD5(password);
        String string=adminService.findUserByNamePaw(userName,password);
        if(string==null||"".equals(string)){
            request.setAttribute("message","登陆失败，账号密码有错");
            return "forward:/web/login";
        }
        session.setAttribute("username",userName);
        return "index";
    }
    @RequestMapping(value = "/logout",method = {RequestMethod.POST})
    /**
     *@type method
     *@parameter  [session, request]
     *@back  java.lang.String
     *@author  如花
     *@creattime 2018/6/1
     *@describe 退出注销session
     */
    @ResponseBody
    public String logout(HttpSession session, HttpServletRequest request){
        if(session.getAttribute("username")!=null){
            session.removeAttribute("username");

        }
        else {
            request.setAttribute("message","不存在session");
        }
        return "redirect:/web/login";
    }

    /**
     *@type method
     *@parameter  [admin, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/1
     *@describe 管理员注册
     */

    @RequestMapping(value ="/register",method = {RequestMethod.POST})
    @ResponseBody
    public Object register(@RequestBody Admin admin, HttpServletRequest request){
        logger.info("管理员用户注册");
        admin.setAdminPassword(MD5Util.toMD5(admin.getAdminPassword()));
        adminService.addAdmin(admin);
        Map map=new HashMap();
        map.put(admin.getAdminId(),"注册成功");
        return ResponseUtil.ok(map);
    }
    @RequestMapping(value ="/forgetPassword",method = {RequestMethod.POST})
    /**
     *@type method
     *@parameter  [admin, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/1
     *@describe 忘记密码
     */
    public Object forgetPassword(@RequestBody Admin admin, HttpServletRequest request){
        return null;
    }



}