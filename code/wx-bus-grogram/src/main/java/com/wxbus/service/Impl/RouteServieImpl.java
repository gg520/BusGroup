package com.wxbus.service.Impl;

import com.wxbus.dao.RouteMapper;
import com.wxbus.daomain.Route;
import com.wxbus.daomain.RouteExample;
import com.wxbus.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: 如花
 * \* @date: 2018/5/16
 * \* @time: 19:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:线路接口实现类
 * \
 */
@Service
public class RouteServieImpl implements RouteService {
    @Resource
    private RouteMapper routeMapper;
    @Override
    /**
     *@type method
     *@parameter  [start, end]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/16
     *@describe 起始点终点路过站点模糊坐标查找线路
     */

    public Route findRouteByStartEnd(String start, String station, String end) {
        RouteExample routeExample=new RouteExample();
        return null;
    }


    @Override
    /**
     *@type method
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/16
     *@describe 添加线路
     */
    public void addRoute(Route route) {
        routeMapper.insertSelective(route);
    }

    @Override
    /**
     *@type method
     *@parameter
     *@back
     *@author  如花
     *@creattime 2018/5/16
     *@describe 删除线路
     */
    public void deleteRouteById(Integer Routed) {
       routeMapper.deleteByPrimaryKey(Routed);
    }

    @Override
    /**
     *@type method
     *@parameter  [route]
     *@back  void
     *@author  如花
     *@creattime 2018/5/17
     *@describe 更新线路
     */
    public void updateRoute(Route route) {
        routeMapper.updateByPrimaryKeySelective(route);
        
    }

    @Override
    /**
     *@type method
     *@parameter  [RouteId]
     *@back  com.wxbus.daomain.Route
     *@author  如花
     *@creattime 2018/5/23
     *@describe 通过id查询线路的详细信息
     */
    public Route findRouteById(Integer RouteId) {
        return routeMapper.selectByPrimaryKey(RouteId);
    }
}

