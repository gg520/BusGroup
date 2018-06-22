package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.dao.DriverMapper;
import com.wxbus.daomain.Bus;
import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverExample;
import com.wxbus.service.DriverService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/10
 * @time: 19:21
 * Description:
 */
@Service
public class DriverServiceImpl implements DriverService{
    private final Log log= LogFactory.getLog(DriverServiceImpl.class.getName());
    @Resource
    DriverMapper driverMapper;
    @Override
    /**
     *@type method
     *@parameter  [driverId]
     *@back  com.wxbus.daomain.Driver
     *@author  如花
     *@creattime 2018/6/10
     *@describe 司机id查找司机信息
     */
    public Driver findDriverById(String driverId) {
        if(driverId==null||"".equals(driverId)){
            return new Driver();
        }
        DriverExample driverExample= new DriverExample();
        driverExample.or().andDriverIdEqualTo(driverId);
        List<Driver> list=driverMapper.selectByExample(driverExample);

        log.info("司机id查找司机信息");
        if(list!=null&&list.size()>0)
            return list.get(0);
        return new Driver();
    }

    @Override
    /**
     *@type method
     *@parameter  [startNum, num, Status]
     *@back  java.util.List<com.wxbus.daomain.Driver>
     *@author  如花
     *@creattime 2018/6/12
     *@describe 按状态查找司机信息
     */
    public List<Driver> findDriverByStatus(Integer startNum, Integer num, Integer status) {
        log.info("按状态查找司机信息");
        PageHelper.startPage(startNum,num);
        DriverExample driverExample=new DriverExample();
        driverExample.or().andDriverStatusEqualTo(status);

        return driverMapper.selectByExample(driverExample);
    }
}