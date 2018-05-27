package com.wxbus.service;

import com.wxbus.daomain.Route;

import java.util.List;

public interface RouteService {

    /**
     *@type interface
     *@parameter  [start, station, end]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/17
     *@describe 起始点终点坐标模糊查询线路查询
     */
    Route findRouteByStartEnd(String start, String station, String end);
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
     *@describe 通过状态查找线路信息
     */
    List<Route> findRouteByStatus(Integer routeStatus);



    
}
