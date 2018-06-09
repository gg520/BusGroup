package com.wxbus.service;

/**
 * @author: Demon
 * @date: 2018/6/8
 * @time: 19:50
 * Description:
 */
public interface Passenger_RouteServise {
    /**
     *@type interface
     *@parameter  [routeId]
     *@back  java.lang.Integer
     *@author  如花
     *@creattime 2018/6/8
     *@describe 通过线路id查找乘客数量
     */
    Integer findPassengerCountByRouteId(Integer routeId);
}