package com.wxbus.service.Impl;

import com.wxbus.WxBusGrogramApplicationTests;
import com.wxbus.daomain.Station;
import com.wxbus.service.StationService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class StationServiceImplTest extends WxBusGrogramApplicationTests{
    @Autowired
    private StationService stationService;

    @Test
    public void findStationByName() {
    }

    @Test
    public void findAllStation() {
    }

    @Test
    public void findStationById() {
    }

    @Test
    public void findStationByStatus() {
    }

    @Test
    public void addStation() {
        Station station=new Station();
        station.setStationName("黄山");
        station.setStationCoord("12,45");
        station.setStationDescribe("这是一个神奇的地方");
        stationService.addStation(station);
    }
}