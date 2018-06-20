package com.wxbus.wxController;

import com.sun.javafx.collections.MappingChange;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerRoute;
import com.wxbus.daomain.Route;
import com.wxbus.pojo.UserToken;
import com.wxbus.service.PassengerRouteService;
import com.wxbus.service.RouteService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: Demon
 * @date: 2018/6/20
 * @time: 19:43
 * Description: 订单
 */
@RestController
@RequestMapping("/weixin/order")
public class WxOrderController {
    private final Log logger = LogFactory.getLog(WxOrderController.class);
    private static Map<String, UserToken> tokenMap = new HashMap<>();
    @Autowired
    private PassengerRouteService passengerRouteService;
    @Autowired
    private RouteService routeService;
    @GetMapping(value = "orderList")
    /**
     *@type method
     *@parameter  [body, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/20
     *@describe  获取用户所有订单信息
     */
    public Object orderList(@RequestBody String body , HttpServletRequest request){
        logger.info("获取用户所有订单信息");
        //获取线路信息
        Route route=new Route();
        Integer showType= JacksonUtil.parseInteger(body,"showType");
        UserToken userToken=tokenMap.get(request.getHeader("X-Bus-Token"));
        Integer passengerId=userToken.getUserId();
        if(passengerId==null||"".equals(passengerId)){
            return ResponseUtil.fail();
        }
        List<PassengerRoute> passengerRouteList=passengerRouteService.findBoughtRouteByPassengerId(passengerId);
        //存放返回的json值
        List<Map> mapList=new ArrayList<Map>();
        if(passengerRouteList==null||passengerRouteList.size()==0){
            ResponseUtil.ok(0);
        }
        for(Iterator iterator=passengerRouteList.iterator();iterator.hasNext();){
            Map<Object,Object> newMap=new HashMap<Object, Object>();
            PassengerRoute passengerRoute=(PassengerRoute) iterator.next();
            if(showType==0){
                route=routeService.findRouteById(passengerRoute.getRouteId());
            }

        }
        return ResponseUtil.ok();
    }
}