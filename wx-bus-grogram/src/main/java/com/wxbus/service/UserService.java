package com.wxbus.service;

import com.wxbus.daomain.Driver;
import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.UserInfo;

/**
 * Created by g1154 on 2018/4/14.
 */
public interface UserService {

    UserInfo getInfo(Integer userId);

    Passenger findById(Integer userId);

    Passenger queryByOid(String openId);

    int add(Passenger user);

    void update(Passenger user);
    /**
     *@type interface
     *@parameter  [passenger]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/5/26
     *@describe 查找用户
     */
    String findUserByMoPaw(String username, String password);
    /**
     *@type interface
     *@parameter  [passenger]
     *@back  void
     *@author  如花
     *@creattime 2018/5/26
     *@describe 添加用户
     */
    void addPassenger(Passenger passenger);
    /**
     *@type interface
     *@parameter  [passenger]
     *@back  void
     *@author  如花
     *@creattime 2018/5/26
     *@describe 更新乘客信息
     */
    Integer updatePassenger(Passenger passenger);

    int addDriver(Driver driver);

    Driver findDriverById(String id);

    /**
     * 验证没有是否有重复的手机，身份证ID ， 驾驶证号码
     * @param driver
     * @return
     */
    boolean checkDriverByDriver(Driver driver);
}
