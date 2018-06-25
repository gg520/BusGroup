package com.wxbus.service.Impl;

import com.wxbus.dao.HelperMapper;
import com.wxbus.dao.MyHelperMapper;
import com.wxbus.daomain.Helper;
import com.wxbus.daomain.HelperExample;
import com.wxbus.service.HelperService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/24
 * @time: 16:09
 * Description:
 */
@Service
public class HelperServiceImpl implements HelperService{
    private final Log log= LogFactory.getLog(HelperServiceImpl.class.getName());
    @Resource
    MyHelperMapper myHelperMapper;
    @Override
    /**
     *@type method
     *@parameter  []
     *@back  java.util.List<com.wxbus.daomain.Helper>
     *@author  如花
     *@creattime 2018/6/24
     *@describe 随机读取6条数据
     */
    public List<Helper> randomSearchHelper() {
        log.info("随机读取6条数据");
        return myHelperMapper.listHelper();
    }
}