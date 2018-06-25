package com.wxbus.service;

import com.wxbus.daomain.Station;

import java.util.List;

public interface StationService {
    /**
     *@type interface
     *@parameter  [stationName]
     *@back  java.util.List<com.wxbus.daomain.Station>
     *@author  如花
     *@creattime 2018/5/17
     *@describe 根据名称状态模糊查找所有站点
     */
    List<Station> findStationByName(String stationName);
    /**
     *@type interface
     *@parameter
     *@back  java.util.List<com.wxbus.daomain.Station>
     *@author  如花
     *@creattime 2018/5/27
     *@describe 通过站点状态查找所有站点
     */
    List<Station> findAllStation();
    /**
     *@type interface
     *@parameter  [stationId]
     *@back  com.wxbus.daomain.Station
     *@author  如花
     *@creattime 2018/6/10
     *@describe 通过id查站点
     */
    Station findStationById(Integer stationId);
    /**
     *@type interface
     *@parameter  [startNum, num, status]
     *@back  java.util.List<com.wxbus.daomain.Station>
     *@author  如花
     *@creattime 2018/6/11
     *@describe 按状态查找站点信息
     */
    List<Station> findStationByStatus(Integer startNum,Integer num,Integer status);
    /**
     *@type interface
     *@parameter  [station]
     *@back  void
     *@author  如花
     *@creattime 2018/6/12
     *@describe 添加站点
     */
    void addStation(Station station);
    /**
     *@type interface
     *@parameter  [station]
     *@back  void
     *@author  如花
     *@creattime 2018/6/22
     *@describe 站点更新
     */
    void updateStation(Station station);


}
