package com.wxbus.config;

import com.wxbus.annotation.support.WebLoginHanderIntercept;
import com.wxbus.annotation.support.WeixinLoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web 拦截器
 * Created by g1154 on 2018/5/12.
 */
@Configuration
public class WebInterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置web的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new WebLoginHanderIntercept()).addPathPatterns("/web/**").excludePathPatterns("/web/operate/**","/web/add/**","/web/search/**","/web/login/**","/web/view/**","/web/index");

    }
}
