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
    private final Log log= LogFactory.getLog(BusServiceImpl.class.getName());
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
        if (busId == null || "".equals(busId)){
            return new Bus();
        }

        BusExample busExample = new BusExample();
        busExample.or().andBusIdEqualTo(busId);
        List<Bus> list = busMapper.selectByExample(busExample);
        if (list != null && list.size() > 0)
        {return list.get(0);}
        return new Bus();
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

    @Override
    /**
     *@type method
     *@parameter  [bus]
     *@back  void
     *@author  如花
     *@creattime 2018/6/11
     *@describe 添加汽车信息
     */
    public void addBus(Bus bus) {
        log.info("添加汽车信息");
        busMapper.insertSelective(bus);

    }

    @Override
    /**
     *@type method
     *@parameter  [bus]
     *@back  void
     *@author  如花
     *@creattime 2018/6/22
     *@describe 更新汽车信息
     */
    public void updatebus(Bus bus) {
        log.info("更新汽车信息");
        busMapper.updateByPrimaryKeySelective(bus);
    }

    @Override
    public Bus findByBudPI(String pi) {

        BusExample example=new BusExample();
        example.or().andBusIdEqualTo(pi);

        List<Bus> busList=busMapper.selectByExample(example);
        if(busList!=null&&busList.size()>0){
            return busList.get(0);
        }
        return null;
    }


}