package com.wxbus.service.Impl;

import com.wxbus.dao.PassengerMapper;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerExample;
import com.wxbus.service.CheckServier;
import com.wxbus.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by g1154 on 2018/5/22.
 */
@Service
public class CheckServiceImpl implements CheckServier{

    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public Passenger getPassengerInfo(Integer id) {

        return passengerMapper.selectByPrimaryKey(id);
    }
    @Override
    /**
     *@type method
     *@parameter  [password]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/6/20
     *@describe 根据密码查找乘客信息
     */
    public Passenger findPassangerByPassword(String password) {
        password= MD5Util.toMD5(password);
        PassengerExample passengerExample=new PassengerExample();
        passengerExample.or().andPassengerPasswordEqualTo(password);
        List<Passenger> passengerList=passengerMapper.selectByExample(passengerExample);
        if(passengerList.size()>0){
            return passengerList.get(0);
        }
        return null;
    }
}
