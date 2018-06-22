package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
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
    private final Log log= LogFactory.getLog(StationServiceImpl.class.getName());
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

    @Override
    /**
     *@type method
     *@parameter  [stationId]
     *@back  com.wxbus.daomain.Station
     *@author  如花
     *@creattime 2018/6/10
     *@describe 通过id查站点
     */
    public Station findStationById(Integer stationId) {
        if(stationId==null||stationId==0)
            return new Station();
        log.info("通过id查站点");
        return stationMapper.selectByPrimaryKey(stationId);
    }

    @Override
    /**
     *@type method
     *@parameter  [startNum, num, status]
     *@back  java.util.List<com.wxbus.daomain.Station>
     *@author  如花
     *@creattime 2018/6/11
     *@describe 按状态查找站点信息
     */
    public List<Station> findStationByStatus(Integer startNum, Integer num, Integer status) {
        log.info("按状态查找站点信息");
        PageHelper.startPage(startNum,num);
        StationExample stationExample=new StationExample();
        stationExample.or().andStationStatusEqualTo(status);
        return stationMapper.selectByExample(stationExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [station]
     *@back  void
     *@author  如花
     *@creattime 2018/6/12
     *@describe 添加站点信息
     */
    public void addStation(Station station) {
        log.info("添加站点信息");
        stationMapper.insertSelective(station);
    }

    @Override
    /**
     *@type method
     *@parameter  [station]
     *@back  void
     *@author  如花
     *@creattime 2018/6/22
     *@describe 更新站点
     */
    public void updateStation(Station station) {
        log.info("更新站点");
        stationMapper.updateByPrimaryKey(station);
    }
}