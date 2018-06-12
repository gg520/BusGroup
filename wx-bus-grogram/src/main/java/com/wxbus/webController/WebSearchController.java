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
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by g1154 on 2018/5/22.
 */
@Controller
@RequestMapping("/web/search")
public class WebSearchController {

    private final Log logger = LogFactory.getLog(WebSearchController.class);
    @Autowired
    private DriverService driverService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private BusService busService;
    @Autowired
    private StationService stationService;

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 待审核线路
     */
    @RequestMapping(value = "/waitcheckroute" ,method = RequestMethod.POST)
    @ResponseBody
    public Object waitCheckRoute(@RequestBody String body){
        logger.info("待审核线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=0;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num,0);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("x:"+routeList.get(i).getStartCoord().split(",")[0]
            +",y:"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  ResponseUtil.ok(routeList);
    }


    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 审核中线路
     */
    @RequestMapping(value = "/runcheckroute" ,method = RequestMethod.POST)
    @ResponseBody
    public Object runCheckRoute(@RequestBody String body){
        logger.info("审核中线路查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=1;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num,0);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  ResponseUtil.ok(routeList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 运行中线路
     */
    @RequestMapping(value = "/runingroute" ,method = RequestMethod.POST)
    @ResponseBody
    public Object runingRoute(@RequestBody String body){
        logger.info("正运行中线路查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=5;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num,0);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  ResponseUtil.ok(routeList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 过期线路
     */
    @RequestMapping(value = "/overdueroute" ,method = RequestMethod.POST)
    @ResponseBody
    public Object overDueRoute(@RequestBody String body){
        logger.info("过期线路查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=6;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num,0);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  ResponseUtil.ok(routeList);
    }

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 未通过线路
     */
    @RequestMapping(value = "/notpassroute" ,method = RequestMethod.POST)
    @ResponseBody
    public Object notPassoute(@RequestBody String body){
        logger.info("审核未通过线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/

        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=2;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num,0);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  ResponseUtil.ok(routeList);
    }

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/10
     *@describe 停用汽车查询
     */
    @RequestMapping(value = "/findNotUseBus" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findNotUseBus(@RequestBody String body){
        logger.info("停用汽车查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Bus> busList= busService.findBusByStatus(1,startNum,num);
        return  ResponseUtil.ok(busList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/10
     *@describe 可用汽车查询
     */
    @RequestMapping(value = "/findCanUseBus" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findCanUseBus(@RequestBody String body){
        logger.info("可用汽车查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Bus> busList= busService.findBusByStatus(0,startNum,num);
        return  ResponseUtil.ok(busList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/10
     *@describe 占用汽车查询
     */
    @RequestMapping(value = "/findUsingBus" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findUsingBus(@RequestBody String body){
        logger.info("占用汽车查询");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Bus> busList= busService.findBusByStatus(2,startNum,num);
        return  ResponseUtil.ok(busList);
    }

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 查找可用站点
     */
    @RequestMapping(value = "/searchusingstation" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findUsingStation(@RequestBody String body){
        logger.info("查找可用站点");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Station> stationList=stationService.findStationByStatus(startNum,num,0);
        return ResponseUtil.ok(stationList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 查找停用站点
     */
    @RequestMapping(value = "/searchstopstation" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findStopStation(@RequestBody String body){
        logger.info("查找停用站点");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Station> stationList=stationService.findStationByStatus(startNum,num,1);
        return ResponseUtil.ok(stationList);
    }

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 查找待审核司机
     */
    @RequestMapping(value = "/searchwaitcheckdriver" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findWaitCheckDriver(@RequestBody String body){
        logger.info("查找待审核司机");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Driver> driverList=driverService.findDriverByStatus(startNum,num,1);
        return ResponseUtil.ok(driverList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 查找可用司机
     */
    @RequestMapping(value = "/searchcanusedriver" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findCanUseDriver(@RequestBody String body){
        logger.info("查找可用司机");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Driver> driverList=driverService.findDriverByStatus(startNum,num,0);
        return ResponseUtil.ok(driverList);
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/11
     *@describe 查找停用司机
     */
    @RequestMapping(value = "/searchstopusedriver" ,method = RequestMethod.POST)
    @ResponseBody
    public Object findStopUseDriver(@RequestBody String body){
        logger.info("查找停用司机");
        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num= JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        List<Driver> driverList=driverService.findDriverByStatus(startNum,num,2);
        return ResponseUtil.ok(driverList);
    }





}
