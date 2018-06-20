package com.wxbus.service;

import com.wxbus.daomain.Passenger;

/**
 * Created by g1154 on 2018/5/22.
 */
public interface CheckServier {

    /**
     * 根據用戶的主鍵獲取用戶信息
     * @param id
     * @return
     */
    Passenger getPassengerInfo(Integer id);
    /**
     *@type interface
     *@parameter  [password]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/6/20
     *@describe 根据密码查找乘客信息
     */
    Passenger findPassangerByPassword(String password);
}
