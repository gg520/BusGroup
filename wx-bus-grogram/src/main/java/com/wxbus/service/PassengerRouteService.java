package com.wxbus.service;

import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerRoute;

import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/8
 * @time: 19:50
 * Description:
 */
public interface PassengerRouteService {
    /**
     *@type interface
     *@parameter  [routeId]
     *@back  java.lang.Integer
     *@author  如花
     *@creattime 2018/6/8
     *@describe 通过线路id查找乘客数量
     */
    Integer findPassengerCountByRouteId(Integer routeId);
    /**
     *@type interface
     *@parameter  [id]
     *@back  java.lang.Integer
     *@author  如花
     *@creattime 2018/6/20
     *@describe 通过乘客的id查找该乘客订单的信息
     */
    List<PassengerRoute> findBoughtRouteByPassengerId(Integer passengerId);
    /**
     *@type interface
     *@parameter  [passengerId]
     *@back  java.util.List<com.wxbus.daomain.PassengerRoute>
     *@author  如花
     *@creattime 2018/6/21
     *@describe 通过乘客的id查找该乘客收藏的信息
     */
    List<PassengerRoute> findCollectionRouteByPassengerId(Integer passengerId);
    /**
     *@type interface
     *@parameter  [pRId]
     *@back  void
     *@author  如花
     *@creattime 2018/6/21
     *@describe 根据收藏id删除收藏信息
     */
    void deletePassengerRouteByPrId(Integer pRId);
    /**
     *@type interface
     *@parameter  [passengerRoute]
     *@back  void
     *@author  如花
     *@creattime 2018/6/21
     *@describe 添加个人收藏信息
     */
    void addPassengerRoute(PassengerRoute passengerRoute);
    /**
     *@type interface
     *@parameter  [passengerId, routeId]
     *@back  com.wxbus.daomain.PassengerRoute
     *@author  如花
     *@creattime 2018/6/25
     *@describe 查找乘客司机绑定的信息
     */
    PassengerRoute findByPassengerIdRouteId(Integer passengerId,Integer routeId);

}