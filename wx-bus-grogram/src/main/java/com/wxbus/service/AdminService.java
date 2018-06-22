package com.wxbus.service;

import com.wxbus.daomain.Admin;

import java.util.List;

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
    /**
     * @author KS
     * @msg 用于登录时放入session的管理员信息
     */
    Admin admin(String username, String password);

    /**
     *@type interface
     *@parameter  []
     *@back  java.util.List<com.wxbus.daomain.Admin>
     *@author  如花
     *@creattime 2018/6/13
     *@describe 查找所有管理员
     */
    List<Admin> findAllAdmin(Integer startNum,Integer num);}
