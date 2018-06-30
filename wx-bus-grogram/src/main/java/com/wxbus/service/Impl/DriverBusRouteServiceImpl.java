package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.dao.DriverBusRouteMapper;
import com.wxbus.daomain.Bus;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.DriverBusRouteExample;
import com.wxbus.service.DriverBusRouteService;
import com.wxbus.util.TimeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    public List<DriverBusRoute> findInfoByIdAndTime(String driverId, String driverTime) {
        DriverBusRouteExample example=new DriverBusRouteExample();
        Date date=TimeUtil.getTimeByString(driverTime,"yyyy-MM-dd");
        example.or().andDriverIdEqualTo(driverId).andDirverTimeBetween(date,TimeUtil.getNextDate(date));
        return driverBusRouteMapper.selectByExample(example);
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

    @Override
    public boolean checkDriverByDriverID(String driverId,String busId) {

        DriverBusRouteExample example=new DriverBusRouteExample();
        example.or().andDriverIdEqualTo(driverId).andDriverOutTimeIsNull();
        int i=driverBusRouteMapper.countByExample(example);
        if(i>=1){//未解绑
            example.clear();
            example.or().andDriverIdEqualTo(driverId).andDriverStatusEqualTo(0).andBusIdEqualTo(busId);//有数据
            i=driverBusRouteMapper.countByExample(example);
            if(i==1)
                return true;//未解绑，已经接取任务
        }
        return false;
    }

    @Override
    public boolean setBindSuccess(String driverId, String busId) {
        DriverBusRouteExample example=new DriverBusRouteExample();
        example.or().andBusIdEqualTo(busId).andDriverIdEqualTo(driverId).andDriverStatusEqualTo(0).andDriverOutTimeIsNull();
        //将数据添加
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        driverBusRoute.setDriverStatus(1);
        if(driverBusRouteMapper.updateByExampleSelective(driverBusRoute,example)==1)
            return true;

        return false;
    }

    public List<DriverBusRoute> findDriverTask(Integer startNum, Integer num) {
        PageHelper.startPage(startNum,num);
        log.info("分页查找司机可领任务");
        DriverBusRouteExample driverBusRouteExample=new DriverBusRouteExample();
        driverBusRouteExample.or().andDriverIdIsNull().andBusIdIsNotNull();
        return driverBusRouteMapper.selectByExample(driverBusRouteExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [driverId]
     *@back  com.wxbus.daomain.DriverBusRoute
     *@author  如花
     *@creattime 2018/6/29
     *@describe 查找管理员给司机分配线路或车辆，即司机解绑时间为空
     */
    public DriverBusRoute findInfoByDriverIdOutTime(String driverId) {
        log.info("查找管理员给司机分配线路车辆");
        DriverBusRouteExample driverBusRouteExample=new DriverBusRouteExample();
        driverBusRouteExample.or().andDriverIdEqualTo(driverId).andDriverOutTimeIsNull();
        List<DriverBusRoute > list=driverBusRouteMapper.selectByExample(driverBusRouteExample);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }

        return new DriverBusRoute();
    }
}
