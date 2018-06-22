package com.wxbus.service;

import com.wxbus.daomain.Driver;

import java.util.List;

public interface DriverService {
    /**
     *@type interface
     *@parameter  [driverId]
     *@back  com.wxbus.daomain.Driver
     *@author  如花
     *@creattime 2018/6/10
     *@describe 司机id查找司机信息
     */
    Driver findDriverById(String driverId);
    /**
     *@type interface
     *@parameter  [startNum, num, Status]
     *@back  java.util.List<com.wxbus.daomain.Driver>
     *@author  如花
     *@creattime 2018/6/12
     *@describe 按状态查找司机信息
     */
    List<Driver> findDriverByStatus(Integer startNum,Integer num,Integer status);
    /**
     *@type interface
     *@parameter  [driver]
     *@back  void
     *@author  如花
     *@creattime 2018/6/22
     *@describe 添加司机
     */
    void addDriver(Driver driver);
    /**
     *@type interface
     *@parameter  [driver]
     *@back  void
     *@author  如花
     *@creattime 2018/6/22
     *@describe 更新司机
     */
    void updateDrivate(Driver driver);
}
