package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.WxBusGrogramApplication;
import com.wxbus.dao.DriverBusRouteMapper;
import com.wxbus.dao.RouteMapper;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.DriverBusRouteExample;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.RouteExample;
import com.wxbus.service.RouteService;
import com.wxbus.util.ChangeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: 如花
 * \* @date: 2018/5/16
 * \* @time: 19:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:线路接口实现类
 * \
 */
@Service
public class RouteServiceImpl implements RouteService {
    @Resource
    private RouteMapper routeMapper;
    @Resource
    private DriverBusRouteMapper driverBusRouteMapper;
    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Override
    /**
     *@type method
     *@parameter  [startCoord, endSite]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/27
     *@describe 起始名称终点站点名称查找招募线路
     */

    public List<Route>  findRouteByStartEnd(String startSite, String endSite,Integer startNum,Integer num,Integer time) {
        PageHelper.startPage(startNum, num);
        RouteExample routeExample=new RouteExample();
        routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(3);
        if(time!=null&&time!=0){
            routeExample.clear();
            if(time==1){
                routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(3).andStartTimeLessThan("12:00");//上午
            }else if (time==2){
                routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(3).andStartTimeGreaterThan("12:00");//下午
            }
        }
        log.info("起始坐标终点站点名称查找线路");
        return routeMapper.selectByExample(routeExample);


    }

    @Override
    /**
     *@type method
     *@parameter  [startSite, endSite]
     *@back  java.util.List<com.wxbus.daomain.Route>
     *@author  如花
     *@creattime 2018/5/28
     *@describe 起始点终点搜索已开线路
     */
    public List<Route> findOpenRouteByStartEnd(String startSite, String endSite,Integer startNum,Integer num,Integer time) {
        PageHelper.startPage(startNum, num);
        RouteExample routeExample=new RouteExample();
        routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(5);
        if(time!=null&&time!=0){
            routeExample.clear();
            if(time==1){
                routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(5).andStartTimeLessThan("12:00");//上午
            }else if (time==2){
                routeExample.or().andStartSiteEqualTo(startSite).andEndSiteEqualTo(endSite).andRouteStatusEqualTo(5).andStartTimeGreaterThan("12:00");//下午
            }
        }
        log.info("起始坐标终点站点名称查找已开线路");
        return routeMapper.selectByExample(routeExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/16
     *@describe 添加线路
     */
    public void addRoute(Route route) {

        routeMapper.insertSelective(route);
    }

    @Override
    /**
     *@type method
     *@parameter
     *@back
     *@author  如花
     *@creattime 2018/5/16
     *@describe 删除线路
     */
    public void deleteRouteById(Integer routed) {
       routeMapper.deleteByPrimaryKey(routed);
    }

    @Override
    /**
     *@type method
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/17
     *@describe 更新线路
     */
    public void updateRoute(Route route) {
        routeMapper.updateByPrimaryKeySelective(route);
        
    }

    @Override
    /**
     *@type method
     *@parameter  [RouteId]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/23
     *@describe 通过id查询线路的详细信息
     */
    public Route findRouteById(Integer routeId) {

        return routeMapper.selectByPrimaryKey(routeId);
    }

    @Override
    /**
     *@type method
     *@parameter  [routeStatus]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/26
     *@describe 根据线路状态查询线路信息
     */
    public List<Route> findRouteByStatus(Integer routeStatus,Integer startNum,Integer num,Integer time) {
        PageHelper.startPage(startNum, num);
        RouteExample routeExample=new RouteExample();
        routeExample.or().andRouteStatusEqualTo(routeStatus);
        if(time!=null&&time!=0){
            routeExample.clear();
            if(time==1){
                routeExample.or().andRouteStatusEqualTo(routeStatus).andStartTimeLessThan("12:00");//上午
            }else if (time==2){
                routeExample.or().andRouteStatusEqualTo(routeStatus).andStartTimeGreaterThan("12:00");//下午
            }
        }
        routeExample.setOrderByClause("recruit_time asc");
        return routeMapper.selectByExample(routeExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [startNum, num, time]
     *@back  java.util.List<com.wxbus.daomain.Route>
     *@author  如花
     *@creattime 2018/6/8
     *@describe 查找全部线路
     */
    public List<Route> findAllRoute(Integer startNum, Integer num, Integer time) {
        PageHelper.startPage(startNum, num);
        RouteExample routeExample=new RouteExample();
        if(time!=null&&time!=0){
            routeExample.clear();
            if(time==1){
                //上午
                routeExample.or().andStartTimeLessThan("12:00");
            }else if (time==2){
                //下午
                routeExample.or().andStartTimeGreaterThan("12:00");
            }
        }
        routeExample.setOrderByClause("recruit_time asc");
        return routeMapper.selectByExample(routeExample);
    }
}

