package com.wxbus.service;

import com.wxbus.daomain.DriverBusRoute;

import java.util.List;

public interface DriverBusRouteService {
    /**
     *@type interface
     *@parameter  [routeId]
     *@back  com.wxbus.service.DriverBusRouteService
     *@author  如花
     *@creattime 2018/6/10
     *@describe 通过线路id查找详细信息
     */
    DriverBusRoute findInfoByRouteId(Integer routeId);
    /**
     *@type interface
     *@parameter  [dreverId]
     *@back  com.wxbus.daomain.DriverBusRoute
     *@author  如花
     *@creattime 2018/6/25
     *@describe 根据司机id查找所绑定车牌号
     */
    List<DriverBusRoute> findInfoByDriverId(String dreverId);
    /**
     *@type interface
     *@parameter  [driverBusRoute]
     *@back  void
     *@author  如花
     *@creattime 2018/6/25
     *@describe 添加司机车辆绑定信息
     */
    void addDriverBusRoute (DriverBusRoute driverBusRoute);
    /**
     *@type interface
     *@parameter  [driverBusRoute]
     *@back  void
     *@author  如花
     *@creattime 2018/6/27
     *@describe 更新信息
     */
    void updateDriverBusRoute(DriverBusRoute driverBusRoute);
}
