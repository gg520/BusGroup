package com.wxbus.dao;

import com.wxbus.daomain.Helper;

import java.util.List;

public interface MyHelperMapper {
    /**
     *@type interface
     *@parameter  []
     *@back  java.util.List<com.wxbus.daomain.Helper>
     *@author  如花
     *@creattime 2018/6/24
     *@describe 随机查找helper数据
     */
    List <Helper> listHelper();
}
