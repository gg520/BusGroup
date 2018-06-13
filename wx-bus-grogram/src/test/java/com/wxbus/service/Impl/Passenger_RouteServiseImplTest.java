package com.wxbus.service.Impl;

import com.wxbus.WxBusGrogramApplicationTests;
import com.wxbus.service.PassengerRouteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Passenger_RouteServiseImplTest extends WxBusGrogramApplicationTests{
    @Autowired
    private PassengerRouteService passenger_routeServise;

    @Test
    public void findPassengerCountByRouteId() {
        Integer count=passenger_routeServise.findPassengerCountByRouteId(2);
        System.out.println(count);
    }
}