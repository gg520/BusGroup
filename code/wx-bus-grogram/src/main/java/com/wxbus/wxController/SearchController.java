package com.wxbus.wxController;

import com.github.pagehelper.PageHelper;
import com.wxbus.daomain.NewStation;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.Station;
import com.wxbus.service.RouteService;
import com.wxbus.service.StationService;
import com.wxbus.util.MathUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.SortUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by g1154 on 2018/5/22.
 */
@RestController
@RequestMapping("/weixin/search")
public class SearchController {


    @Autowired
    private StationService stationService;

    @Autowired
    private RouteService routeService;
    private final Log logger = LogFactory.getLog(LoginController.class);

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
     *@describe 招募线路搜索
     */
    @RequestMapping(value = "/routePlant",method={RequestMethod.POST})
    public Object routePlant(@RequestBody String startCoord,String endSite,Integer startNum,Integer num){
        logger.info("查找招募线路");
        //起始站点名称
        String startSite=null;
        List<Station> stationList = stationService.findAllStation();
        List<NewStation> newStationList=new ArrayList<NewStation>();
        String [] stringsCoord=startCoord.split(",");
        SortUtil.stationSort(stationList,newStationList,stringsCoord);

        if(newStationList.get(0)!=null){
            startSite=newStationList.get(0).getStationName();
        }
        //起始点终止点不为空查询数据库
        if(startSite==null||endSite==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList=routeService.findRouteByStartEnd(startSite,endSite, startNum, num);
        return ResponseUtil.ok(routeList);


    }

    /**
     *@type method
     *@parameter  [startCoord, endSite, startNum, num]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/5/28
     *@describe 运行线路搜索
     */
    @RequestMapping(value = "/routeRun",method={RequestMethod.POST})
    public Object routeRun(@RequestBody String startCoord,String endSite,Integer startNum,Integer num){
        logger.info("查找已开线路");
        //起始站点名称
        String startSite=null;
        List<Station> stationList = stationService.findAllStation();
        List<NewStation> newStationList=new ArrayList<NewStation>();
        String [] stringsCoord=startCoord.split(",");
        SortUtil.stationSort(stationList,newStationList,stringsCoord);


        if(newStationList.get(0)!=null){
            startSite=newStationList.get(0).getStationName();
        }
        //起始点终止点不为空查询数据库
        if(startSite==null||endSite==null){
            return ResponseUtil.fail();
        }
        List<Route> routeList=routeService.findOpenRouteByStartEnd(startSite,endSite, startNum, num);
        return ResponseUtil.ok(routeList);


    }

    @PostMapping("/site")
    public Object searchStation(@RequestBody String site){

        return null;
    }
}
