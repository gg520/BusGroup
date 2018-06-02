package java.com.wxbus.service;

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

}
