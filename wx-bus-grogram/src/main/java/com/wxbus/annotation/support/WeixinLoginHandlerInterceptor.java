package com.wxbus.annotation.support;

import com.wxbus.service.HeadersName;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JsonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 登录拦截
 * Created by g1154 on 2018/5/11.
 */
public class WeixinLoginHandlerInterceptor implements HandlerInterceptor{

    Log logger= LogFactory.getLog(WeixinLoginHandlerInterceptor.class);

//    //微信登录的信息录入
//    private static final String LOGIN_TOKEN_KEY = "X-Bus-Token";
//
//    //微信端识别请求的标示
//    private static final String CONNECT_PLATFORM="Connect_Platform";

    /**
     * 拦截的方法
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

//        String platform= request.getHeader(CONNECT_PLATFORM);//获取链接的平台信息
        String platform= request.getHeader(HeadersName.CONN_USER);//获取链接的平台信息
        if(platform==null||platform.length()<=0)//不是微信平台不做拦截
            return false;//不是微信端请求不能是使用微信端的接口
        else{
            //获取是否登录，不登录不允许请求任何界面
//            String token=request.getHeader(LOGIN_TOKEN_KEY);
            String token=request.getHeader(HeadersName.TOKEN);
            if(token==null||token.length()<=0|| UserTokenManager.getUserId(token)==null){

                System.out.println(token);
                response.setStatus(200);//未登录
                response.setContentType("text/html;charset=utf-8");
                PrintWriter out=response.getWriter();
                out.println(JsonUtil.stringify(ResponseUtil.unlogin()));
                out.close();
                logger.info("成功拦截");
                return false;
            }else{

                logger.info("验证结果已登录");
                return true;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
