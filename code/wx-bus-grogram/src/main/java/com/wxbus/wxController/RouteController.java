package com.wxbus.wxController;

import com.wxbus.daomain.Route;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.HeadersName;
import com.wxbus.service.RouteService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 实现线路的controller
 * Created by g1154 on 2018/5/6.
 */
@RestController
@RequestMapping(value = "/weixin/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    private final Log logger= LogFactory.getLog(RouteController.class.getName());

    @RequestMapping(value = "/inquiryRoute",method = {RequestMethod.POST})
    /**
     *@type method
     *@parameter  [body, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/28
     *@describe  乘客发起招募
     */
    public Object inquiryRoute(@RequestBody String body, HttpServletRequest request){

        String token=request.getHeader(HeadersName.TOKEN);
        String str = UserTokenManager.getUserId(token);

        Integer id=JacksonUtil.parseInteger(str,"userId");
        Route route =new Route();
        route.setStartSite(JacksonUtil.parseString(body,"startSite"));
        route.setEndSite(JacksonUtil.parseString(body,"endSite"));
        route.setCreatUser(String.valueOf(id));
        route.setCreatTime(new Date());
        route.setRouteStatus(0);
        if(route.getStartSite()==null||route.getEndSite()==null){
            return ResponseUtil.fail();
        }
        routeService.addRoute(route);


        System.out.println(body);
        logger.info("招募路线查询");




        return ResponseUtil.ok();
    }


    /**
     *@type method
     *@parameter  [routeId]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/28
     *@describe 获取线路详细信息
     */
    @RequestMapping(value="/routeInfo",method = {RequestMethod.POST})
    public Object routeInfo(@RequestBody  Integer routeId){
        logger.info("获取线路的详细信息:"+routeId);
        if(routeId==null){
            return ResponseUtil.fail();

        }
        else{
            Route route=routeService.findRouteById(routeId);

            return ResponseUtil.ok(route);


        }

    }

    /**
     * 获取正在招募的羡慕的信息，分页，每次获取10个
     * @param body
     *      body:
     *          pm:（0,1,2）不区分上午下午 0，上午 1，下午 2
     *          countRout： 当前以获取的数量
     *
     * @return
     */
    @RequestMapping(value = "/routePlant",method = {RequestMethod.POST})
    public Object routePlant(@RequestBody String body){

        logger.info("招募路线查询：" +body);
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        Integer routeStatus=3;
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList = routeService.findRouteByStatus(routeStatus,startNum,num);
        return ResponseUtil.ok(routeList);
    }

    /**
     * 获取正在运行路线的信息
     * @param body
     *      body:
     *          pm:（0,1,2）不区分上午下午 0，上午 1，下午 2
     *          countRout： 当前已获取的数量
     * @return
     */
    @RequestMapping(value = "/routeRun",method = {RequestMethod.POST})
    public Object routeRun(@RequestBody String body){

        logger.info("运行路线查询：" +body);
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        Integer routeStatus=5;
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList = routeService.findRouteByStatus(routeStatus,startNum,num);
        return ResponseUtil.ok(routeList);
    }
}
