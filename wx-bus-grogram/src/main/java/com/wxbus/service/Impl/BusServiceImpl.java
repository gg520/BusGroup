package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.dao.BusMapper;
import com.wxbus.daomain.Bus;
import com.wxbus.daomain.BusExample;
import com.wxbus.service.BusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/10
 * @time: 19:36
 * Description:
 */
@Service
public class BusServiceImpl implements BusService{
    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Resource
    private BusMapper busMapper;
    @Override
    /**
     *@type method
     *@parameter  [busId]
     *@back  com.wxbus.daomain.Bus
     *@author  如花
     *@creattime 2018/6/10
     *@describe 通过id查找汽车信息
     */
    public Bus findBusById(String busId) {
        BusExample busExample=new BusExample();
        busExample.or().andBusIdEqualTo(busId);
        List<Bus> list=busMapper.selectByExample(busExample);


        return list.get(0);
    }

    @Override
    /**
     *@type method
     *@parameter  [status, startNum, num]
     *@back  java.util.List<com.wxbus.daomain.Bus>
     *@author  如花
     *@creattime 2018/6/10
     *@describe 根据状态查找汽车信息
     */
    public List<Bus> findBusByStatus(Integer status,Integer startNum,Integer num) {
        log.info("根据状态查找汽车信息");
        PageHelper.startPage(startNum,num);
        BusExample busExample=new BusExample();
        busExample.or().andBusStatusEqualTo(status);
        return busMapper.selectByExample(busExample);
    }
}