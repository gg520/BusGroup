package java.com.wxbus.config;


import com.wxbus.annotation.support.LoginUserHandlerMethodArgumentResolver;
import com.wxbus.annotation.support.WeixinLoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@Configuration
public class WxInterceptorConfig extends WebMvcConfigurerAdapter {



    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }

    //拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        /**
         * 拦截所有微信端的请求
         * 微信端请求规定带上识别的请求头进行拦截
         * 将除登录意外不需要验证登录的链接放到/view的子链接下
         * 其他的链接必须登录才能获取
         */
        registry.addInterceptor(new WeixinLoginHandlerInterceptor()).addPathPatterns("/weixin/**").excludePathPatterns("/weixin/auth/*","/weixin/view/**","/weixin/route/**",
                "/weixin/search/**");

    }

    /**
     * 视图映射配置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        super.addViewControllers(registry);

//        registry.addViewController("controller类中的方法的路径").setViewName("前台界面");
        //各种方法自己研究


    }
}
