package com.wxbus.service;

import com.wxbus.daomain.Passenger;
import com.wxbus.pojo.UserInfo;

/**
 * Created by g1154 on 2018/4/14.
 */
public interface UserService {

    UserInfo getInfo(Integer userId);

    Passenger findById(Integer userId);

    Passenger queryByOid(String openId);

    void add(Passenger user);

    void update(Passenger user);

}
