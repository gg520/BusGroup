package com.wxbus.service.Impl;

import com.wxbus.dao.PassengerRouteMapper;
import com.wxbus.daomain.PassengerRoute;
import com.wxbus.daomain.PassengerRouteExample;
import com.wxbus.service.PassengerRouteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/8
 * @time: 19:54
 * Description:
 */
@Service
public class PassengerRouteServiceImpl implements PassengerRouteService {
    @Resource
    private PassengerRouteMapper passengerRouteMapper;
    private final Log log= LogFactory.getLog(PassengerRouteServiceImpl.class.getName());
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

    @Override
    /**
     *@type method
     *@parameter  [id]
     *@back  java.lang.Integer
     *@author  如花
     *@creattime 2018/6/20
     *@describe 通过乘客的id查找该乘客订单的信息
     */
    public List<PassengerRoute> findBoughtRouteByPassengerId(Integer  passengerId) {
        log.info("通过乘客的id查找该乘客订单的信息");
        PassengerRouteExample passengerRouteExample=new PassengerRouteExample();
        passengerRouteExample.or().andPassengerIdEqualTo(passengerId).andStartStatusEqualTo(1);
        passengerRouteExample.setOrderByClause("creat_time");

        return passengerRouteMapper.selectByExample(passengerRouteExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [passengerId]
     *@back  java.util.List<com.wxbus.daomain.PassengerRoute>
     *@author  如花
     *@creattime 2018/6/21
     *@describe 通过乘客的id查找该乘客收藏的信息
     */
    public List<PassengerRoute> findCollectionRouteByPassengerId(Integer passengerId) {
        log.info("通过乘客的id查找该乘客收藏的信息");
        PassengerRouteExample passengerRouteExample=new PassengerRouteExample();
        passengerRouteExample.or().andPassengerIdEqualTo(passengerId).andStartStatusEqualTo(0);
        passengerRouteExample.setOrderByClause("creat_time");
        return passengerRouteMapper.selectByExample(passengerRouteExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [pRId]
     *@back  void
     *@author  如花
     *@creattime 2018/6/21
     *@describe 根据收藏id删除收藏信息
     */
    public void deletePassengerRouteByPrId(Integer pRId) {
        log.info("根据收藏id删除收藏信息");
        PassengerRouteExample passengerRouteExample=new PassengerRouteExample();
        passengerRouteExample.or().andPRIdEqualTo(pRId).andStartStatusEqualTo(0);
        passengerRouteMapper.deleteByExample(passengerRouteExample);

    }

    @Override
    /**
     *@type method
     *@parameter  [passengerRoute]
     *@back  void
     *@author  如花
     *@creattime 2018/6/21
     *@describe 添加个人收藏
     */
    public void addPassengerRoute(PassengerRoute passengerRoute) {
        log.info("添加个人收藏");
        passengerRouteMapper.insertSelective(passengerRoute);

    }
}