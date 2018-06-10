package com.wxbus.service;

import com.wxbus.daomain.Driver;

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
}
