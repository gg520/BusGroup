package com.wxbus.service;

import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.Route;

import java.util.List;

public interface RouteService {
    /**
     *@type interface
     *@parameter  [startSite, endSite]
     *@back  java.util.List<com.wxbus.daomain.Route>
     *@author  如花
     *@creattime 2018/5/28
     *@describe 根据开始终点搜索已开线路
     */
    List<Route> findOpenRouteByStartEnd(String startSite, String endSite,Integer startNum,Integer num,Integer time);

    /**
     *@type interface
     *@parameter  [startCoord, endSite]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/27
     *@describe 开始结束站点查找招募线路
     */
    List<Route> findRouteByStartEnd(String startSite, String endSite,Integer startNum,Integer num,Integer time);
    /**
     *@type interface
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/16
     *@describe 线路添加
     */
    void addRoute(Route route);
    /**
     *@type interface
     *@parameter  [RouteId]
     *@back  void
     *@author  如花
     *@creattime 2018/5/17
     *@describe 根据id删除线路
     */
    void deleteRouteById(Integer RouteId);
    /**
     *@type interface
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/17
     *@describe
     */
    void updateRoute(Route route);
    /**
     *@type interface
     *@parameter  [RouteId]
     *@back  void
     *@author  如花
     *@creattime 2018/5/23
     *@describe 通过id获取线路的详细信息
     */
    Route findRouteById(Integer routeId);
    /**
     *@type interface
     *@parameter  [routeStatus]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/26
     *@describe 通过状态分页查找线路信息
     */
    List<Route> findRouteByStatus(Integer routeStatus,Integer startNum,Integer num,Integer time);
    /**
     *@type interface
     *@parameter  [startNum, num, time]
     *@back  java.util.List<com.wxbus.daomain.Route>
     *@author  如花
     *@creattime 2018/6/8
     *@describe 查找全部路线
     */
    List<Route> findAllRoute(Integer startNum,Integer num,Integer time);



    
}
