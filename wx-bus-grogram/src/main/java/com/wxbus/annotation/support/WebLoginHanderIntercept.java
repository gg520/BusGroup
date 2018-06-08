package com.wxbus.annotation.support;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Created by g1154 on 2018/5/12.
 */
public class WebLoginHanderIntercept implements HandlerInterceptor {
    private final Log log= LogFactory.getLog(WebLoginHanderIntercept.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       log.info("web拦截执行");

        HttpSession session=httpServletRequest.getSession();
        Object user=session.getAttribute("user");
        if(user==null){
            log.info("尚未登录,跳转登录页面");
            httpServletResponse.sendRedirect("/web/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
