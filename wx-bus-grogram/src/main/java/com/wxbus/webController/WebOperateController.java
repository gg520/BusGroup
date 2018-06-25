package com.wxbus.webController;

import com.wxbus.daomain.Bus;
import com.wxbus.daomain.Driver;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.Station;
import com.wxbus.service.BusService;
import com.wxbus.service.DriverService;
import com.wxbus.service.RouteService;
import com.wxbus.service.StationService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Demon
 * @date: 2018/6/22
 * @time: 19:25
 * Description:各种操作
 */
@Controller
@RequestMapping(value = "/web/operate")
public class WebOperateController {
    @Autowired
    private RouteService routeService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private BusService busService;
    @Autowired
    private StationService stationService;
    private final Log logger= LogFactory.getLog(WebOperateController.class);
    @RequestMapping(value = "stationoperate",method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 站点操作(停用，启用)
     */
    @ResponseBody
    public Object stationOperate(@RequestBody String body){
        logger.info("站点操作停用，启用");
        Integer stationId= JacksonUtil.parseInteger(body,"stationId");
        Integer stationStatus=JacksonUtil.parseInteger(body,"stationStatus");
        if(stationId==null||stationStatus==null){
            return ResponseUtil.fail(500,"传入数据不能为空");
        }
        Station station=stationService.findStationById(stationId);
        if(station==null){
            return ResponseUtil.fail(500,"未找到相关站点");
        }
        station.setStationStatus(stationStatus);
        stationService.updateStation(station);
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 汽车操作(停用，启用)
     */
    @RequestMapping(value = "/busoperate",method = RequestMethod.POST)
    @ResponseBody
    public Object busOperate(@RequestBody String body){
        logger.info("汽车操作停用，启用");
        String busId= JacksonUtil.parseString(body,"busId");
        Integer busStatus=JacksonUtil.parseInteger(body,"busStatus");
        if(busId==null||busStatus==null){
            return ResponseUtil.fail(500,"传入数据不能为空");
        }
        Bus bus=busService.findBusById(busId);
        if(bus==null){
            return ResponseUtil.fail(500,"未找到相关车辆");
        }
        bus.setBusStatus(busStatus);
        busService.updatebus(bus);
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 司机操作(停用，启用)
     */
    @RequestMapping(value = "driveroperate",method = RequestMethod.POST)
    @ResponseBody
    public Object driverOperate(@RequestBody String body){
        logger.info("司机操作停用，启用");
        String driverId= JacksonUtil.parseString(body,"driverId");
        Integer driverStatus=JacksonUtil.parseInteger(body,"driverStatus");
        if(driverId==null||driverStatus==null){
            return ResponseUtil.fail(500,"传入数据不能为空");
        }
        Driver driver=driverService.findDriverById(driverId);
        if(driver==null){
            return ResponseUtil.fail(500,"未找到相关司机");
        }
        driver.setDriverStatus(driverStatus);
        driverService.updateDrivate(driver);
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 线路审核操作(通过，未通过)
     */
    @RequestMapping(value = "lineoperate",method = RequestMethod.POST)
    @ResponseBody
    public Object lineOperate(@RequestBody String body){
        logger.info("线路审核操作(通过，未通过)");
        Integer routeId= JacksonUtil.parseInteger(body,"routeId");
        Integer routeStatus=JacksonUtil.parseInteger(body,"routeStatus");
        if(routeId==null||routeStatus==null){
            return ResponseUtil.fail(500,"传入数据不能为空");
        }
        Route route=routeService.findRouteById(routeId);
        if(route==null){
            return ResponseUtil.fail(500,"未找到相关线路");
        }
        route.setRouteStatus(routeStatus);
        routeService.updateRoute(route);
        return ResponseUtil.ok();
    }
}