package com.wxbus.service;

import com.wxbus.daomain.Bus;

import java.util.List;

public interface BusService {
    /**
     *@type interface
     *@parameter  [busId]
     *@back  com.wxbus.daomain.Bus
     *@author  如花
     *@creattime 2018/6/10
     *@describe 通过id查找汽车信息
     */
    Bus findBusById(String busId);
    /**
     *@type interface
     *@parameter  [status, startNum, num]
     *@back  java.util.List<com.wxbus.daomain.Bus>
     *@author  如花
     *@creattime 2018/6/10
     *@describe 汽车状态查找汽车
     */
    List<Bus> findBusByStatus(Integer status,Integer startNum,Integer num);
}
