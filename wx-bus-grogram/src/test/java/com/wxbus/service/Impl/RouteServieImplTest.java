package com.wxbus.service.Impl;


import com.google.gson.Gson;
import com.wxbus.WxBusGrogramApplicationTests;

import com.wxbus.daomain.Route;
import com.wxbus.service.RouteService;


import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.junit.Assert.*;
/**
 *@type method
 *@parameter
 *@back
 *@author  如花
 *@creattime 2018/6/6
 *@describe RouteServieImpl测试类
 */
public class RouteServieImplTest extends WxBusGrogramApplicationTests{
    @Autowired
    private RouteService routeService;

    @Test
    public void findRouteByStartEnd() throws Exception{

        List<Route> routeList=routeService.findRouteByStartEnd("新郑","龙湖",0,1,0);
        System.out.println(routeList.toString());
    }
    @Test
    public void finRouteByStatus() throws Exception{
        Gson gson=new Gson();

        List<Route> routeList=routeService.findRouteByStatus(0,0,1,0);

        for (int i=0;i<routeList.size();i++)
            System.out.println(gson.toJson(routeList.get(i)));

    }
}