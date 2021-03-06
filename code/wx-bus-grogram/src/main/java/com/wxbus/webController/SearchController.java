package com.wxbus.webController;


import com.wxbus.daomain.Route;

import com.wxbus.service.RouteService;

import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by g1154 on 2018/5/22.
 */
@RestController
@RequestMapping("/web/examline")
public class SearchController {

    private final Log logger = LogFactory.getLog(com.wxbus.wxController.LoginController.class);
    @Autowired
    private RouteService routeService;

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 待审核线路
     */
    @RequestMapping(value = "/toWaitCheck" ,method = RequestMethod.POST)
    public Object waitCheck(@RequestBody String body){
        logger.info("待审核线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/

        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=0;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
            +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  routeList;
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
    public Object runCheck(@RequestBody String body){
        logger.info("审核中线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/

        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=1;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  routeList;
    }
    @RequestMapping(value = "toNotPass" ,method = RequestMethod.POST)
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/31
     *@describe 未通过线路
     */
    public Object notPass(@RequestBody String body){
        logger.info("审核未通过线路查询");
        /*startNum	int	分页起始位置
        num	int	分页数据量*/

        Integer startNum= JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        if(startNum==null||num==null){
            return ResponseUtil.fail();
        }
        Integer status=2;
        List<Route> routeList= routeService.findRouteByStatus(status,startNum,num);
        for(int i=0;i<routeList.size();i++){

            routeList.get(i).setStartCoord("\"x\":"+routeList.get(i).getStartCoord().split(",")[0]
                    +",\"y\":"+routeList.get(i).getStartCoord().split(",")[1]);
        }
        return  routeList;
    }

}
