package com.wxbus.webController;

import com.wxbus.daomain.*;
import com.wxbus.service.*;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.wxController.WxLoginController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author: Demon
 * @date: 2018/5/31
 * @time: 20:29
 * Description:
 */
@Controller
@RequestMapping(value = "/web/add")
public class WebAddController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private BusService busService;
    @Autowired
    private StationService stationService;
    private final Log logger = LogFactory.getLog(WebAddController.class);

    /**
     *@type method
     *@parameter  [bus]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 添加汽车信息
     */
    @RequestMapping(value = "/addbus",method = {RequestMethod.POST})
    @ResponseBody
    public Object addBus(@RequestBody Bus bus) {
        logger.info("添加汽车信息");
        if(bus==null){
            return ResponseUtil.fail();

        }
        busService.addBus(bus);
        return ResponseUtil.ok();

    }
    /**
     *@type method
     *@parameter  [station]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 添加站点信息
     */
    @RequestMapping(value = "/addstation",method = {RequestMethod.POST})
    @ResponseBody
    public Object addStation(@RequestBody Station station) {

        logger.info("添加站点信息");
        if(station==null){
            return ResponseUtil.fail();

        }
        stationService.addStation(station);
        return ResponseUtil.ok();

    }
    /**
     *@type method
     *@parameter  [route, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 添加路线
     */
    @RequestMapping(value = "/addroute",method = {RequestMethod.POST})
    @ResponseBody
    public Object addRoute(@RequestBody Route route, HttpServletRequest request){
        if(route ==null){
            return ResponseUtil.fail();
        }
        Integer creatUser=Integer.parseInt(request.getSession().getAttribute("user").toString());
        route.setCreatUser(creatUser);
        route.setCreatTime(new Date());
        routeService.addRoute(route);
        logger.info("添加路线");

        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [route, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 由管理员添加线路
     */
    @RequestMapping(value = "/addline",method = {RequestMethod.POST})
    @ResponseBody
    public Object addLine(@RequestBody Route route){
        if(route==null){
            return ResponseUtil.fail();
        }
        route.setCreatUser(0);
        route.setCreatTime(new Date());
        route.setRouteStatus(1);
        routeService.addRoute(route);
        logger.info("由管理员添加线路");
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [driver]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 添加司机
     */
    @RequestMapping(value = "/addDriver",method = {RequestMethod.POST})
    @ResponseBody
    public Object addDriver(@RequestBody Driver driver){
        if(driver==null){
            return ResponseUtil.fail();
        }
        logger.info("添加司机");
        driverService.addDriver(driver);
        return ResponseUtil.ok();
    }
    /**
     *@type method
     *@parameter  [admin]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/22
     *@describe 添加管理员
     */
    @RequestMapping(value = "/addAdmin",method = {RequestMethod.POST})
    @ResponseBody
    public Object addAdmin(@RequestBody Admin admin){
        if(admin==null){
            return ResponseUtil.fail();
        }
        logger.info("添加司机");
        adminService.addAdmin(admin);
        return ResponseUtil.ok();
    }



}