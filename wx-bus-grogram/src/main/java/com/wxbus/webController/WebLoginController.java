package com.wxbus.webController;


import com.wxbus.daomain.Admin;
import com.wxbus.service.AdminService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.MD5Util;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
    private final Log logger = LogFactory.getLog(WebLoginController.class);
    @Autowired
    private AdminService adminService;

    @PostMapping("/webLogin")
    public String login(@Param("username")String username,
                        @Param("password")String password, Model model,HttpSession session)throws Exception{

        if(username.equals("")||username==null||password.equals("")||password==null){
            logger.info("用户名或者密码为空");

            return "login";
        }
        password=MD5Util.toMD5(password);
        Admin admin=adminService.admin(username,password);
        if(admin==null){
            logger.info("账号或者密码错误");
            model.addAttribute("loginmsg","用户名或密码错误");

            return "login";
        }else {
            logger.info("管理员"+username+"登录成功");
            session.setAttribute("user",admin);
            return "redirect:/web/index";
        }

    }


    @GetMapping(value = "/loginout")
    /**
     * @author ks
     */
    public String logout(HttpSession session,HttpServletRequest request){
        logger.info("退出执行");
        if(session.getAttribute("user")!=null){
            session.removeAttribute("user");
            session.invalidate();
            request.setAttribute("message","退出成功");
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