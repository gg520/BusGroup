package com.wxbus.wxController;

import com.wxbus.daomain.*;
import com.wxbus.service.*;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.TimeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Created by g1154 on 2018/6/29.
 *
 * @page： com.wxbus.wxController
 * @Description:
 */
@RestController
@RequestMapping("/weixin/driver")
public class WxDriverController {


    @Autowired
    private DriverService driverService;
    @Autowired
    private PassengerRouteService passenger_routeServise;
    @Autowired
    private CheckServier checkServier;
    @Autowired
    private DriverBusRouteService driverBusRouteService;
    @Autowired
    private StationService stationService;
    @Autowired
    private BusService busService;
    @Autowired
    private RouteService routeService;

    private final static Log logger=LogFactory.getLog(WxDriverController.class);


    @PostMapping(value = "findBusInfo")
    public Object findBusInfo(@RequestBody String body, HttpServletRequest request){
        logger.info("司机查找车辆绑定信息");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver=driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return ResponseUtil.fail(500,"未找到相关司机信息");
        }
        String driverId=driver.getDriverId();
        List<DriverBusRoute > list=driverBusRouteService.findInfoByDriverId(driverId);
        if(list==null||list.size()<=0){
            return  ResponseUtil.fail(500,"未找到相关绑定信息");
        }
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDriverOutTime()==null){
                driverBusRoute=list.get(i);
            }
        }
        Integer routeId=driverBusRoute.getRouteId();
        String busId=driverBusRoute.getBusId();
        Route route=routeService.findRouteById(routeId);
        if(route==null){
            return ResponseUtil.fail(500,"未找到相关线路");
        }
        Bus bus=busService.findBusById(busId);
        if(bus==null){
            return ResponseUtil.fail(500,"未找到相关车辆");
        }
        Map<Object,Object> map=new HashMap<Object, Object>();
        map.put("busId",bus.getBusId());
        map.put("engineNum",bus.getEngineNum());
        map.put("characters",bus.getCharacters());
        map.put("registrationDate",bus.getRegistrationDate());
        map.put("oppenDate",bus.getOppenDate());
        map.put("busOwner",bus.getBusOwner());
        map.put("seating",bus.getSeating());
        map.put("busStatus",bus.getBusStatus());
        map.put("model",bus.getModel());
        map.put("road",routeId+"号线("+route.getStartSite()+","+route.getEndSite()+")");
        map.put("busType",bus.getBusType());
        return ResponseUtil.ok(map);
    }
    /**
     *@type method
     *@parameter  [request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/26
     *@describe 查看司机的历史任务记录
     */
    @PostMapping(value = "findTask")
    public Object findTask(HttpServletRequest request){
        logger.info("查看司机的历史任务记录");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver= driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return  ResponseUtil.fail(500,"未找到相关司机");
        }
        List<DriverBusRoute> list1=driverBusRouteService.findInfoByDriverId(driver.getDriverId());
        List<DriverBusRoute> list2=new ArrayList<>();
        if(list1==null){
            return ResponseUtil.fail(500,"不存在历史信息");
        }
        for(int i=0;i<list1.size();i++){
            if(list1.get(i).getDriverOutTime()!=null){
                list2.add(list1.get(i));
            }
        }
        List<Map> routeList=new ArrayList<>();

        //将对应线路放入routeList
        for(int i=0;i<list2.size();i++){
            Map map=new HashMap();
            Route route=routeService.findRouteById(list2.get(i).getRouteId());
            map.put("creatTime",list2.get(i).getDirverTime());
            map.put("route",route);
            routeList.add(map);
        }
        return ResponseUtil.ok(routeList);
    }
    /**
     *@type method
     *@parameter  [request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/26
     *@describe 查看司机的某天历史任务记录
     */
    @PostMapping(value = "findTaskByTime")
    public Object findTaskByTime(@RequestBody String body, HttpServletRequest request){
        logger.info("查看司机的某天历史任务记录");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver= driverService.findDriverByDriverNum(driverNum);
        String timeString =JacksonUtil.parseString(body,"time");
        //时间转为date格式
//        Date date= TimeUtil.getTimeByString(timeString,"yyyy-MM-dd HH:mm:ss");
//        Calendar calendar=Calendar.getInstance();
//        System.out.println(date);
        if(driver==null){
            return  ResponseUtil.fail(500,"未找到相关司机");
        }
        List<DriverBusRoute> list1=driverBusRouteService.findInfoByIdAndTime(driver.getDriverId(),timeString);
        List<DriverBusRoute> list2=new ArrayList<>();
        if(list1==null){
            return ResponseUtil.fail(500,"不存在历史信息");
        }
        for(int i=0;i<list1.size();i++){
            if(list1.get(i).getDriverOutTime()!=null){
                list2.add(list1.get(i));
            }
        }
        List<Map> routeList=new ArrayList<>();

        //将对应线路放入routeList
        for(int i=0;i<list2.size();i++){
            Map map=new HashMap();
            Route route=routeService.findRouteById(list2.get(i).getRouteId());
            map.put("creatTime",list2.get(i).getDirverTime());
            map.put("route",route);
            routeList.add(map);
        }
        return ResponseUtil.ok(routeList);
//

//        List<Route> routeList=new ArrayList<>();
//        //将对应线路放入routeList
//        for(int i=0;i<list2.size();i++){
//            Route route=routeService.findRouteById(list2.get(i).getRouteId());
//            routeList.add(route);
//        }
//        return ResponseUtil.ok(routeList);
    }
    @PostMapping(value = "findCurrentRoad")
    /**
     *@type method
     *@parameter  [request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/27
     *@describe 查找指定司机的当前任务
     */
    public Object findCurrentRoad(HttpServletRequest request){
        logger.info("查找司机的当前任务");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver= driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return  ResponseUtil.fail(500,"未找到相关司机");
        }
        List<DriverBusRoute> list=driverBusRouteService.findInfoByDriverId(driver.getDriverId());
        if(list==null||list.size()<=0){
            return  ResponseUtil.fail(500,"不存在相关司机信息");
        }
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDriverOutTime()==null){
                driverBusRoute=list.get(i);
            }
        }
        Integer routeId=driverBusRoute.getRouteId();
        Route route=routeService.findRouteById(routeId);
        if(route==null){
            return ResponseUtil.fail();
        }
        Map<Object,Object> map1=new HashMap<>();
        Map<Object,Object> map2=new HashMap<>();
        String stationId=route.getStationId();
        List mapList=new ArrayList<>();
        if(stationId!=null&&"".equals(stationId)){
            for(String staId:stationId.split(",")){
                Integer id= Integer.parseInt(staId);
                Station station=stationService.findStationById(id);
                map2.put("stationid",station.getStationId());
                map2.put("stationname",station.getStationName());
            }
        }
        map1.put("startSite",route.getStartSite());
        map1.put("endSite",route.getEndSite());
        map1.put("startTime",route.getStartTime());
        map1.put("endTime",route.getEndTime());
        map1.put("busId",driverBusRoute.getBusId());
        if(map2.size()>0){
            mapList.add(map2);
            map1.put("site",mapList);
        }
        return ResponseUtil.ok(map1);
    }

    //获取所有任务

}
