package com.wxbus.wxController;

import com.sun.javafx.collections.MappingChange;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerRoute;
import com.wxbus.daomain.Route;
import com.wxbus.pojo.NewUserRoute;
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
        List<NewUserRoute> newUserRouteList=new ArrayList<NewUserRoute>();
        if(passengerRouteList==null||passengerRouteList.size()==0){
            ResponseUtil.ok(0);
        }
        //获取系统当前时间
        Date date=new Date();


        for(int i=0;i<passengerRouteList.size();i++){
            NewUserRoute newUserRoute=new NewUserRoute();
            PassengerRoute passengerRoute=passengerRouteList.get(i);
            route=routeService.findRouteById(passengerRoute.getRouteId());
            if (passengerRoute.getEndTime().getTime()<date.getTime()){
                newUserRoute.setRouteStatus(1);
            }
            else {
                newUserRoute.setRouteStatus(0);
            }
            newUserRoute.setOrderNumber(passengerRoute.getOrderNumber());
            newUserRoute.setReserveDay(passengerRoute.getReserveDays());
            newUserRoute.setCreatUser(route.getCreatUser());
            newUserRoute.setStartSite(route.getStartSite());
            newUserRoute.setEndSite(route.getEndSite());
            newUserRoute.setPrice(route.getPrice());
            newUserRoute.setStartTime(route.getStartTime());
            newUserRoute.setPayMoney(passengerRoute.getPayMoney());
            newUserRoute.setRouteId(passengerRoute.getRouteId());
            newUserRouteList.add(newUserRoute);
        }
        if(showType==0){
            return  ResponseUtil.ok(newUserRouteList);
        }
        else if(showType==1){
            List<NewUserRoute> newUserRouteList1=new ArrayList<NewUserRoute>();
            for(Iterator iterator=newUserRouteList.iterator();iterator.hasNext();){
                NewUserRoute newUserRoute1=(NewUserRoute) iterator.next();
                if(newUserRoute1.getRouteStatus()==1){
                    newUserRouteList1.add(newUserRoute1);
                }
            }
            return ResponseUtil.ok(newUserRouteList1);
        }
        else if(showType==2){
            List<NewUserRoute> newUserRouteList1=new ArrayList<NewUserRoute>();
            for(Iterator iterator=newUserRouteList.iterator();iterator.hasNext();){
                NewUserRoute newUserRoute1=(NewUserRoute) iterator.next();
                if(newUserRoute1.getRouteStatus()==2){
                    newUserRouteList1.add(newUserRoute1);
                }
            }
            return ResponseUtil.ok(newUserRouteList1);
        }
        else if(showType==3){
            List<NewUserRoute> newUserRouteList1=new ArrayList<NewUserRoute>();
            for(Iterator iterator=newUserRouteList.iterator();iterator.hasNext();){
                NewUserRoute newUserRoute1=(NewUserRoute) iterator.next();
                if(newUserRoute1.getCreatUser().equals(passengerId)){
                    newUserRouteList1.add(newUserRoute1);
                }
            }
            return ResponseUtil.ok(newUserRouteList1);
        }
        return ResponseUtil.fail(500,"没有相关信息f");
    }
}