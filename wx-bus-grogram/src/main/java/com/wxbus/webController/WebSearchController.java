package com.wxbus.webController;


import com.wxbus.daomain.Bus;
import com.wxbus.daomain.Route;
import com.wxbus.service.BusService;
import com.wxbus.service.RouteService;
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
@RequestMapping("/web/examline")
public class WebSearchController {

    private final Log logger = LogFactory.getLog(com.wxbus.wxController.WxLoginController.class);
    @Autowired
    private RouteService routeService;
    @Autowired
    private BusService busService;

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 待审核线路
     */
    @RequestMapping(value = "/toWaitCheck" ,method = RequestMethod.POST)
    @ResponseBody
    public Object waitCheck(@RequestBody String body){
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

    @RequestMapping(value = "/toRunCheck" ,method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 审核中线路
     */
    @ResponseBody
    public Object runCheck(@RequestBody String body){
        logger.info("审核中线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/

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
    @RequestMapping(value = "/toNotPass" ,method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 未通过线路
     */
    @ResponseBody
    public Object notPass(@RequestBody String body){
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



}
