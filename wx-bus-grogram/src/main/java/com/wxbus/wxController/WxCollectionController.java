package com.wxbus.wxController;

import com.wxbus.daomain.PassengerRoute;
import com.wxbus.daomain.Route;
import com.wxbus.pojo.NewUserRoute;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.HeadersName;
import com.wxbus.service.PassengerRouteService;
import com.wxbus.service.RouteService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.TimeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.InsufficientResourcesException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: Demon
 * @date: 2018/6/21
 * @time: 11:04
 * Description:
 */

@RestController
@RequestMapping("/weixin/collect")
public class WxCollectionController {
    private final Log logger = LogFactory.getLog(WxCollectionController.class);
//    private static Map<String, UserToken> tokenMap = new HashMap<>();
    @Autowired
    private PassengerRouteService passengerRouteService;
    @Autowired
    private RouteService routeService;

    /**
     *@type method
     *@parameter  [request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/21
     *@describe 获取用户所有收藏信息
     */
    @RequestMapping(value = "/getCollect",method = RequestMethod.POST)
    public Object getCollect(HttpServletRequest request){
        //获取线路信息
        Route route=new Route();
        logger.info("获取用户所有收藏信息");
//        UserToken userToken=tokenMap.get();
        String token=request.getHeader(HeadersName.TOKEN);
        String jsonID=UserTokenManager.getUserId(token);
        System.out.println(JacksonUtil.parseString(jsonID,"user"));
        if(!"乘客".equals(JacksonUtil.parseString(jsonID,"user").trim())){
            return ResponseUtil.fail401();
        }
        Integer passengerId=JacksonUtil.parseInteger(jsonID,"userId");
        if(passengerId==null||"".equals(passengerId)){
            return ResponseUtil.fail();
        }

        List<PassengerRoute> passengerRouteList=passengerRouteService.findCollectionRouteByPassengerId(passengerId);
        //存放返回的json值
        List<NewUserRoute> newUserRouteList=new ArrayList<NewUserRoute>();
        if(passengerRouteList==null||passengerRouteList.size()==0){
            ResponseUtil.ok(0);
        }
        //获取系统当前时间

        for(int i=0;i<passengerRouteList.size();i++){
            NewUserRoute newUserRoute=new NewUserRoute();
            PassengerRoute passengerRoute=passengerRouteList.get(i);
            route=routeService.findRouteById(passengerRoute.getRouteId());

            if((TimeUtil.getTimeByString(route.getEndsRecruit(),"yyyy-MM-dd").getTime())<(new Date().getTime())){
                newUserRoute.setRouteStatus(2);
            }else{
                newUserRoute.setRouteStatus(1);
            }
//

            newUserRoute.setpRId(passengerRoute.getpRId());
            newUserRoute.setStartSite(route.getStartSite());
            newUserRoute.setEndSite(route.getEndSite());
            newUserRoute.setPrice(route.getPrice());
            newUserRoute.setStartTime(route.getStartTime());
            newUserRoute.setRouteId(passengerRoute.getRouteId());


            newUserRoute.setCreatTime(TimeUtil.getTimeByType(new Date(),"yyyy-MM-dd HH:mm:ss"));

            newUserRouteList.add(newUserRoute);

        }
        return ResponseUtil.ok(newUserRouteList);
    }

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/21
     *@describe 删除个人信息
     */
    @RequestMapping(value = "/clearCollect",method = RequestMethod.POST)
    public Object clearCollect(@RequestBody String body){
        logger.info("删除个人信息");
        if(body==null||"".equals(body)){
            return  ResponseUtil.fail(500,"传入信息为空");
        }
        Integer pRId= JacksonUtil.parseInteger(body,"pRId");
        passengerRouteService.deletePassengerRouteByPrId(pRId);
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/21
     *@describe 添加个人信息
     */
    @RequestMapping(value = "/addCollect",method = RequestMethod.POST)
    public Object addCollect(@RequestBody String body,HttpServletRequest request){
        logger.info("添加个人信息");


        String token=request.getHeader(HeadersName.TOKEN);
        String jsonID=UserTokenManager.getUserId(token);
        if(JacksonUtil.parseString(jsonID,"user")!="乘客"){
            return ResponseUtil.fail401();
        }
        Integer passengerId=JacksonUtil.parseInteger(jsonID,"userId");
        if(passengerId==null||"".equals(passengerId)){
            return ResponseUtil.fail();
        }
        Integer routeId=JacksonUtil.parseInteger(body,"routeId");
        PassengerRoute passengerRoute=new PassengerRoute();
        passengerRoute.setCreatTime(new Date());
        passengerRoute.setPassengerId(passengerId);
        passengerRoute.setRouteId(routeId);
        passengerRoute.setStartStatus(0);
        passengerRouteService.addPassengerRoute(passengerRoute);
        return ResponseUtil.ok("添加成功");
    }
}