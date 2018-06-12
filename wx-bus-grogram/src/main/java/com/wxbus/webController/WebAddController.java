package com.wxbus.webController;

import com.wxbus.daomain.Bus;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.Station;
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
    private RouteService routeService;
    @Autowired
    private BusService busService;
    @Autowired
    private StationService stationService;
    private final Log logger = LogFactory.getLog(WebAddController.class);
    @RequestMapping(value = "/toaddbus")
    public String toAddBus() {

        return "addbus";

    }

    @RequestMapping(value = "/toadddriver")
    public String toAddDriver() {
        return "adddriver";
    }

    @RequestMapping(value = "/toaddline")
    public String toAddLine() {
        return "addline";
    }

    @RequestMapping(value = "/toaddsite")
    public String toAddSite() {
        return "addsite";
    }

    @RequestMapping("/toaddstaff")
    public String toAddStaff() {
        return "addstaff";
    }

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
    @RequestMapping(value = "/addroute",method = {RequestMethod.POST})
    @ResponseBody
    public Object addRoute(@RequestBody Route route, HttpServletRequest request){
        if(route ==null){
            return ResponseUtil.fail();
        }
        String creatUser=String.valueOf(request.getSession().getAttribute("user"));
        route.setCreatUser(creatUser);
        route.setCreatTime(new Date());
        routeService.addRoute(route);
        logger.info("招募路线查询");

        return ResponseUtil.ok();
    }



}