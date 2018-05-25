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
    public List<Station> findStationByName(String stationName) throws  Exception{
        //定义新的字符串用于存放转换后的站点名称
        stationName = ChangeUtil.changeString(stationName);
        StationExample stationExample=new StationExample();
        stationExample.or().andStationNameLike("%"+stationName);
        log.info("根据名称查找站点");
        return stationMapper.selectByExample(stationExample);
    }
}