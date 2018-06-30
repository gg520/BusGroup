package com.wxbus.wxController;


import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.Route;
import com.wxbus.service.*;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Demon
 * @date: 2018/6/29
 * @time: 19:31
 * Description:
 */
@RestController
@RequestMapping(value = "/weixin/task")
public class WxTaskController {
    private final Log logger= LogFactory.getLog(WxTaskController.class.getName());
    @Autowired
    private RouteService routeService;
    @Autowired
    private DriverBusRouteService driverBusRouteService;
    @Autowired
    private DriverService driverService;
    @PostMapping(value = "getDriverTask")
    /**
     *@type method
     *@parameter  [body, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/29
     *@describe 获取司机任务
     */
    private Object getDriverTask(@RequestBody String body, HttpServletRequest request){
        logger.info("获取司机任务");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver= driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return  ResponseUtil.fail(500,"未找到相关司机");
        }
        String driverId=driver.getDriverId();
        DriverBusRoute driverBusRoute=driverBusRouteService.findInfoByDriverIdOutTime(driverId);
        //若为不空即管理员为该司机分配过车辆
        if(driverBusRoute!=null){
            List<Map> mapList=new ArrayList<>();
            Integer routeId=driverBusRoute.getRouteId();
            Route route=routeService.findRouteById(routeId);
            Map<Object,Object> map=new HashMap<>();
            map.put("startSite",route.getStartSite());
            map.put("endSite",route.getEndSite());
            map.put("busId",driverBusRoute.getBusId());
            map.put("routeId",routeId);
            map.put("startTime",route.getStartTime());
            map.put("endTime",route.getEndTime());
            mapList.add(map);
            Map<Object,Object> map1=new HashMap<>();
            map1.put("tasks",mapList);
            return ResponseUtil.ok(map1);
        }else{
            driverBusRoute=driverBusRouteService.findInfoByDriverIsNullAndNotOutTime();
            if(driverBusRoute==null){
                return ResponseUtil.fail(-1,"没有可领任务");
            }
        }
        //否则返回司机可领任务
        Integer startNum=JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        List<DriverBusRoute> list=driverBusRouteService.findDriverTask(startNum,num);
        if(list==null||list.size()<=0){
            return  ResponseUtil.fail(500,"没有可领任务");
        }
        List list1=new ArrayList();
        for(int i=0;i<list.size();i++){
            Route route=routeService.findRouteById(list.get(i).getRouteId());
            Map<Object,Object> map=new HashMap<>();
            map.put("startSite",route.getStartSite());
            map.put("endSite",route.getEndSite());
            map.put("busId",driverBusRoute.getBusId());
            map.put("routeId",list.get(i).getRouteId());
            map.put("startTime",route.getStartTime());
            map.put("endTime",route.getEndTime());
            list1.add(map);
        }
        Map<Object,Object> map=new HashMap<>();
        map.put("tasks",list1);
        return ResponseUtil.ok(map);
    }

}