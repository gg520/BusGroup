package com.wxbus.service.Impl;

import com.wxbus.dao.DriverBusRouteMapper;
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
    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Resource
    DriverBusRouteMapper driverBusRouteMapper;
    @Override
    public DriverBusRoute findInfoByRouteId(Integer routeId) {
        if(routeId==null||routeId==0)
            return new DriverBusRoute();
        log.info("线路id查找详细信息");
        DriverBusRouteExample driverBusRouteExample=new DriverBusRouteExample();
        driverBusRouteExample.or().andRouteIdEqualTo(routeId);

        List<DriverBusRoute> list=driverBusRouteMapper.selectByExample(driverBusRouteExample);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return new DriverBusRoute();
    }
}
