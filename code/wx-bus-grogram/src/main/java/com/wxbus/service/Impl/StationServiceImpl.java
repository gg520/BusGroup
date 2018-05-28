package com.wxbus.service.Impl;

import com.wxbus.dao.StationMapper;
import com.wxbus.daomain.Station;
import com.wxbus.daomain.StationExample;
import com.wxbus.service.StationService;
import com.wxbus.util.ChangeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/5/17
 * @time: 19:02
 * Description:
 */
@Service
public class StationServiceImpl implements StationService {
    @Resource
    private StationMapper stationMapper;
    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Override
    public List<Station> findStationByName(String stationName){
        //定义新的字符串用于存放转换后的站点名称
        stationName = ChangeUtil.changeString(stationName);
        StationExample stationExample=new StationExample();
        stationExample.or().andStationNameLike("%"+stationName).andStationStatusEqualTo(0);
        log.info("根据状态名称模糊查找站点");
        return stationMapper.selectByExample(stationExample);
    }

    @Override
    /**
     *@type method
     *@parameter
     *@back  java.util.List<com.wxbus.daomain.Station>
     *@author  如花
     *@creattime 2018/5/27
     *@describe 查找所有可用线路（根据状态值查找0可用，1暂停）
     */
    public List<Station> findAllStation() {
        StationExample stationExample=new StationExample();
        stationExample.or().andStationStatusEqualTo(0);
        List<Station> list = stationMapper.selectByExample(stationExample);
        return list;
    }
}