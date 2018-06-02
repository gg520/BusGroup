package java.com.wxbus.annotation.support;

import com.wxbus.annotation.LoginUser;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserTokenManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * 自定义参数解析器
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
//    public static final String LOGIN_TOKEN_KEY = "X-Bus-Token";

    //日志
    static Log log= LogFactory.getLog(LoginUserHandlerMethodArgumentResolver.class.getName());

    /**
     * 用于判定是否需要处理该参数分解，返回true为需要，并会去调用下面的方法resolveArgument。
     * @param parameter 参数
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Integer.class)&&parameter.hasParameterAnnotation(LoginUser.class);
//        return true;
    }


    /**
     *
     * 真正用于处理参数分解的方法，返回的Object就是controller方法上的形参对象。
     * @param parameter
     * @param container
     * @param request
     * @param factory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {

//        return new Integer(1);
        System.err.println("参数解析器");
        //获取需要的请求头
        String token = request.getHeader(HeadersName.TOKEN);
        if(token == null || token.isEmpty()){
            log.warn("没有请求头："+HeadersName.TOKEN);
            return null;
        }
        log.info("验证登录函数的运行");

        return UserTokenManager.getUserId(token);
    }
}
