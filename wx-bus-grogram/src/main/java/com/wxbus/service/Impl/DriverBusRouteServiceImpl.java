package com.wxbus.service.Impl;

import com.wxbus.dao.DriverBusRouteMapper;
import com.wxbus.daomain.Bus;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.DriverBusRouteExample;
import com.wxbus.service.DriverBusRouteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DriverBusRouteServiceImpl implements DriverBusRouteService{
    private final Log log= LogFactory.getLog(DriverBusRouteServiceImpl.class.getName());
    @Resource
    DriverBusRouteMapper driverBusRouteMapper;
    @Override
    public DriverBusRoute findInfoByRouteId(Integer routeId) {
        if(routeId==null||routeId==0) {
            return new DriverBusRoute();
        }
        log.info("线路id查找详细信息");
        DriverBusRouteExample driverBusRouteExample=new DriverBusRouteExample();
        driverBusRouteExample.or().andRouteIdEqualTo(routeId);

        List<DriverBusRoute> list=driverBusRouteMapper.selectByExample(driverBusRouteExample);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return new DriverBusRoute();
    }

    @Override
    public List<DriverBusRoute> findInfoByDriverId(String dreverId) {
        log.info("根据司机id查找所绑定车");
        DriverBusRouteExample driverBusRouteExample=new DriverBusRouteExample();
        driverBusRouteExample.or().andDriverIdEqualTo(dreverId);
        List<DriverBusRoute> driverBusRouteList=driverBusRouteMapper.selectByExample(driverBusRouteExample);
        return  driverBusRouteList;
    }

    @Override
    public void addDriverBusRoute(DriverBusRoute driverBusRoute) {
        log.info("添加司机车辆绑定信息");
        driverBusRouteMapper.insertSelective(driverBusRoute);
    }

    @Override
    public void updateDriverBusRoute(DriverBusRoute driverBusRoute) {
        log.info("更新信息");
        driverBusRouteMapper.updateByPrimaryKeySelective(driverBusRoute);
    }
}
