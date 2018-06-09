package com.wxbus.wxController;



import com.wxbus.daomain.NewRoute;
import com.wxbus.daomain.NewStation;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.Station;
import com.wxbus.service.Passenger_RouteServise;
import com.wxbus.service.RouteService;
import com.wxbus.service.StationService;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.SortUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by g1154 on 2018/5/22.
 */
@RestController
@RequestMapping("/weixin/search")
public class WxSearchController {
    @Autowired
    private Passenger_RouteServise passenger_routeServise;


    @Autowired
    private StationService stationService;

    @Autowired
    private RouteService routeService;
    private final Log logger = LogFactory.getLog(WxLoginController.class);

    /**
     *@type method
     *@parameter  [endSiteName]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/27
     *@describe 根据站点名称模糊查找站点
     */
    @RequestMapping(value="/site",method={RequestMethod.POST})
    public  Object site(@RequestBody String siteName){
        logger.info("查找站点");
        siteName= JacksonUtil.parseString(siteName,"site");
        if (!"".equals(siteName)){
            List<Station> stationList=stationService.findStationByName(siteName);
            return ResponseUtil.ok(stationList);

        }
        else{
            return ResponseUtil.fail();
        }

    }

    /**
     *@type method
     *@parameter  [startCoord, endSite, startNum, num]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/28
     *@describe 起始点终止点招募线路搜索
     */
    @RequestMapping(value = "/routePlant",method={RequestMethod.POST})
    public Object routePlant(@RequestBody String body){
        Integer passengerCount;

         String endSite=JacksonUtil.parseString(body,"endSite");
        Integer startNum=JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        String startCoord=JacksonUtil.parseString(body,"startCoord");
        logger.info("查找招募线路");
        //起始站点名称
        String startSite=null;

        Integer time=JacksonUtil.parseInteger(body,"time");

        //查找所有站点
        List<Station> stationList = stationService.findAllStation();
        String [] stringsCoord=startCoord.split(",");
        List<NewStation> newStationList=SortUtil.stationSort(stationList,stringsCoord);

        if(newStationList.get(0)!=null){
            startSite=newStationList.get(0).getStationName();
        }
        //起始点终止点不为空查询数据库
        if(startSite==null||endSite==null){
            return ResponseUtil.fail();
        }
        //查找线路
        List<Route> routeList=routeService.findRouteByStartEnd(startSite,endSite, startNum, num,time);
        List<NewRoute> newRouteList=new ArrayList<NewRoute>();
        for(int i=0;i<routeList.size();i++){
            NewRoute newRoute=new NewRoute(routeList.get(i));
            passengerCount=passenger_routeServise.findPassengerCountByRouteId(routeList.get(i).getRouteId());
            newRoute.setPassengerCount(passengerCount);
            newRouteList.add(newRoute);


        }

        return ResponseUtil.ok(newRouteList);


    }

    /**
     *@type method
     *@parameter  [startCoord, endSite, startNum, num]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/28
     *@describe 起始点终止点运行线路搜索
     */
    @RequestMapping(value = "/routeRun",method={RequestMethod.POST})
    public Object routeRun(@RequestBody String body){
        Integer passengerCount;
        logger.info("查找已开线路");

        String endSite=JacksonUtil.parseString(body,"endSite");
        Integer startNum=JacksonUtil.parseInteger(body,"startNum");
        Integer num=JacksonUtil.parseInteger(body,"num");
        String startCoord=JacksonUtil.parseString(body,"startCoord");
        //获取时间
        Integer time=JacksonUtil.parseInteger(body,"time");
        //起始站点名称
        String startSite=null;
        List<Station> stationList = stationService.findAllStation();

        String [] stringsCoord=startCoord.split(",");
        List<NewStation> newStationList=SortUtil.stationSort(stationList,stringsCoord);


        if(newStationList.get(0)!=null){
            startSite=newStationList.get(0).getStationName();
        }
        //起始点终止点不为空查询数据库
        if(startSite==null||endSite==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList=routeService.findOpenRouteByStartEnd(startSite,endSite, startNum, num,time);
        List<NewRoute> newRouteList=new ArrayList<NewRoute>();
        for(int i=0;i<routeList.size();i++){
            NewRoute newRoute=new NewRoute(routeList.get(i));
            passengerCount=passenger_routeServise.findPassengerCountByRouteId(routeList.get(i).getRouteId());
            newRoute.setPassengerCount(passengerCount);
            newRouteList.add(newRoute);


        }
        return ResponseUtil.ok(newRouteList);


    }


}
