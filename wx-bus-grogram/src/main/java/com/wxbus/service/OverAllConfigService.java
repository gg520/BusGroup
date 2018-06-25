package com.wxbus.service;

import com.wxbus.daomain.OverallConfig;

import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/22
 * @time: 11:27
 * Description:运行周期接口
 */
public interface OverAllConfigService {
    /**
     *@type interface
     *@parameter  [startNum, num]
     *@back  java.util.List<com.wxbus.daomain.OverallConfig>
     *@author  如花
     *@creattime 2018/6/22
     *@describe 查找全部运行周期
     */
    List<OverallConfig> findAllOverallConfig(Integer startNum,Integer num);

}