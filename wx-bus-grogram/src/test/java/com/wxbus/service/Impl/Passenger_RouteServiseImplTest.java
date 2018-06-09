package com.wxbus.service.Impl;

import com.wxbus.WxBusGrogramApplicationTests;
import com.wxbus.service.Passenger_RouteServise;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class Passenger_RouteServiseImplTest extends WxBusGrogramApplicationTests{
    @Autowired
    private Passenger_RouteServise passenger_routeServise;

    @Test
    public void findPassengerCountByRouteId() {
        Integer count=passenger_routeServise.findPassengerCountByRouteId(2);
        System.out.println(count);
    }
}