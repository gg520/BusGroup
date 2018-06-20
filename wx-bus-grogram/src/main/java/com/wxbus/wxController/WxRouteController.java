package com.wxbus.wxController;

import com.wxbus.daomain.NewRoute;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.Station;
import com.wxbus.service.*;
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
import java.util.*;

/**
 * 实现线路的controller
 * Created by g1154 on 2018/5/6.
 */
@RestController
@RequestMapping(value = "/weixin/route")
public class WxRouteController {
    @Autowired
    private  StationService stationService;
    @Autowired
    private DriverBusRouteService driverBusRouteService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private PassengerRouteService passenger_routeServise;
    @Autowired
    private DriverService driverService;
    @Autowired
    private BusService busService;


    private final Log logger= LogFactory.getLog(WxRouteController.class.getName());

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

        if(str==null||"".equals(str)){
            return ResponseUtil.fail401();
        }

        Integer id=JacksonUtil.parseInteger(str,"userId");
        Route route =new Route();
        route.setStartSite(JacksonUtil.parseString(body,"startsite"));
        route.setEndSite(JacksonUtil.parseString(body,"endsite"));
        route.setCreatUser(String.valueOf(id));
        route.setCreatTime(new Date());
        route.setStartTime(JacksonUtil.parseString(body,"starttime"));
        route.setEndTime(JacksonUtil.parseString(body,"endtime"));
        route.setRouteStatus(0);
        route.setRunTime(JacksonUtil.parseString(body,"period"));
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
     *@describe 根据线路id获取线路详细信息包括车辆司机等
     */
    @RequestMapping(value="/routeInfo",method = {RequestMethod.POST})
    public Object routeInfo(@RequestBody  String body){
        Integer routeId =JacksonUtil.parseInteger(body,"routeId");
        logger.info("获取线路的详细信息:"+routeId);
        if(routeId==null||"".equals(routeId)){
            return ResponseUtil.fail();

        }
        else{
            String driverId=driverBusRouteService.findInfoByRouteId(routeId).getDriverId();
            String busId=driverBusRouteService.findInfoByRouteId(routeId).getBusId();
            Route route=routeService.findRouteById(routeId);
            String driverName=driverService.findDriverById(driverId).getDriverName();
            String driverMobile=driverService.findDriverById(driverId).getDriverMobile();
            Integer busNum=busService.findBusById(busId).getBusNum();
            String stationId=route.getStationId();
            Map<Object,Object> map1=new HashMap<Object,Object>();
            Map<Object,Object> map2=new HashMap<Object,Object>();
            String[] newStationId=stationId.split(",");
            for(int i=0;i<newStationId.length;i++){

                Integer id= Integer.parseInt(newStationId[i]);
                Station station=stationService.findStationById(id);
                map2.put("stationId",station.getStationId());
                map2.put("stationName",station.getStationName());

            }


            List mapList = new ArrayList<>();
            map1.put("routeId",route.getRouteId());
            map1.put("startSite",route.getStartSite());
            map1.put("endSite",route.getEndSite());
            map1.put("startTime",route.getStartTime());
            map1.put("endTime",route.getEndTime());
            map1.put("price",route.getPrice());
            map1.put("recruitTime",route.getRecruitTime());
            map1.put("busId",busId);
            map1.put("busNum",busNum);
            map1.put("driverName",driverName);
            map1.put("driverMobile",driverMobile);
            mapList.add(map2);
            map1.put("station",mapList);

            /*NewRoute newRoute =new NewRoute(route);
            Integer passengerCount=passenger_routeServise.findPassengerCountByRouteId(routeId);
            newRoute.setPassengerCount(passengerCount);*/
            return ResponseUtil.ok(map1);







        }




    }

    /**
     * 获取正在招募的羡慕的信息，分页，每次获取10个
     * @param body
     *
     *
     * @return 除了线路信息外还必须添加该线路已经订购的数量
     */
    @RequestMapping(value = "/routePlant",method = {RequestMethod.POST})
    public Object routePlant(@RequestBody String body){
        Integer passengerCount;

        logger.info("招募路线查询：" +body);
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        Integer routeStatus=3;
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer time=JacksonUtil.parseInteger(body,"time");
        List<Route> routeList = routeService.findRouteByStatus(routeStatus,startNum,num,time);
        List<NewRoute> newRouteList=new ArrayList<NewRoute>();
        for(int i=0;i<routeList.size();i++){
            NewRoute newRoute=new NewRoute(routeList.get(i));
            passengerCount=passenger_routeServise.findPassengerCountByRouteId(routeList.get(i).getRouteId());
            newRoute.setPassengerCount(passengerCount);
            newRouteList.add(newRoute);


        }

        //将List 里面的数据转换成map


        return ResponseUtil.ok(newRouteList);
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
        Integer passengerCount;

        logger.info("运行路线查询：" +body);
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        Integer time=JacksonUtil.parseInteger(body,"time");
        Integer routeStatus=5;
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList = routeService.findRouteByStatus(routeStatus,startNum,num,time);
        List<NewRoute> newRouteList=new ArrayList<NewRoute>();
        for(int i=0;i<routeList.size();i++){
            NewRoute newRoute=new NewRoute(routeList.get(i));
            passengerCount=passenger_routeServise.findPassengerCountByRouteId(routeList.get(i).getRouteId());
            newRoute.setPassengerCount(passengerCount);
            newRouteList.add(newRoute);


        }
        return ResponseUtil.ok(newRouteList);
    }
    @RequestMapping(value = "/queryAllReoute",method = {RequestMethod.POST})
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/8
     *@describe 分页返回全部路线
     */
    public Object queryAllReoute(@RequestBody String body){
        Integer passengerCount;

        logger.info("全部路线查询：" +body);
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        Integer time=JacksonUtil.parseInteger(body,"time");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList = routeService.findAllRoute(startNum,num,time);
        List<NewRoute> newRouteList=new ArrayList<NewRoute>();
        for(int i=0;i<routeList.size();i++) {
            NewRoute newRoute = new NewRoute(routeList.get(i));
            passengerCount = passenger_routeServise.findPassengerCountByRouteId(routeList.get(i).getRouteId());
            newRoute.setPassengerCount(passengerCount);
            newRouteList.add(newRoute);
        }

        return ResponseUtil.ok(newRouteList);
    }

}
