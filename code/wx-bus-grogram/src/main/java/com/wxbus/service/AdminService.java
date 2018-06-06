package com.wxbus.service;

import com.wxbus.daomain.Admin;

public interface AdminService {
    /**
     *@type interface
     *@parameter  [passenger]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/5/26
     *@describe 查找用户
     */
    String findUserByNamePaw(String username, String password);
    /**
     *@type interface
     *@parameter  [admin]
     *@back  void
     *@author  如花
     *@creattime 2018/6/2
     *@describe 添加管理员
     */
    void addAdmin(Admin admin);
}
