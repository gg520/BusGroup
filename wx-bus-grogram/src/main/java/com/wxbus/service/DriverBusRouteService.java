package com.wxbus.service;

import com.wxbus.daomain.DriverBusRoute;

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
}
