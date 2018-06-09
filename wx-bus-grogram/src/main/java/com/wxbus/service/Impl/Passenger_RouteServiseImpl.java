package com.wxbus.service.Impl;

import com.wxbus.dao.PassengerRouteMapper;
import com.wxbus.daomain.PassengerRouteExample;
import com.wxbus.service.Passenger_RouteServise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.PublicKey;

/**
 * @author: Demon
 * @date: 2018/6/8
 * @time: 19:54
 * Description:
 */
@Service
public class Passenger_RouteServiseImpl implements Passenger_RouteServise {
    @Resource
    private PassengerRouteMapper passengerRouteMapper;
    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Override
    /**
     *@type method
     *@parameter  [RouteId]
     *@back  java.lang.Integer
     *@author  如花
     *@creattime 2018/6/8
     *@describe 通过线路id查找乘客数量
     */
    public Integer findPassengerCountByRouteId(Integer routeId) {
        log.info("线路id查找乘客数量");
        PassengerRouteExample passengerRouteExample=new PassengerRouteExample();
        passengerRouteExample.or().andRouteIdEqualTo(routeId);
        return passengerRouteMapper.countByExample(passengerRouteExample);

    }
}