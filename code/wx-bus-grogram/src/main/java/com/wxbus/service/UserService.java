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
    /**
     *@type interface
     *@parameter  [mobile, password]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/5/25
     *@describe 根据手机号密码进行登陆
     */
    Passenger findUserByMoPaw(String mobile,String password);

}
