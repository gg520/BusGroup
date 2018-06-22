package com.wxbus.service.Impl;

import com.github.pagehelper.PageHelper;
import com.wxbus.dao.OverallConfigMapper;
import com.wxbus.daomain.OverallConfig;
import com.wxbus.daomain.OverallConfigExample;
import com.wxbus.service.OverAllConfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/22
 * @time: 11:30
 * Description:运行周期接口实现
 */
@Service
public class OverAllConfigServiceImpl implements OverAllConfigService{
    @Resource
    private OverallConfigMapper overallConfigMapper;
    private final Log log= LogFactory.getLog(OverAllConfigServiceImpl.class.getName());
    @Override
    /**
     *@type method
     *@parameter  [startNum, num]
     *@back  java.util.List<com.wxbus.daomain.OverallConfig>
     *@author  如花
     *@creattime 2018/6/22
     *@describe 查找全部运行周期
     */
    public List<OverallConfig> findAllOverallConfig(Integer startNum, Integer num) {
        log.info("查找全部运行周期");
        PageHelper.startPage(startNum,num);
        OverallConfigExample overallConfigExample=new OverallConfigExample();
        return overallConfigMapper.selectByExample(overallConfigExample);
    }
}