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
     *@describe 
     */
    List<Station> findStationByName(String stationName) throws  Exception;
}
