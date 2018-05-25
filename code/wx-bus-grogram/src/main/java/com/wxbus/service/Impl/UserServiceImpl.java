package com.wxbus.service.Impl;


import com.wxbus.dao.PassengerMapper;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerExample;
import com.wxbus.pojo.UserInfo;
import com.wxbus.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by g1154 on 2018/4/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PassengerMapper passengerMapper;

    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());

    public UserInfo getInfo(Integer userId) {
        Passenger passenger= passengerMapper.selectByPrimaryKey(userId);
        Assert.state(passenger!=null,"用户不存在");
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName(passenger.getPassengerNickname());//获取昵称
        userInfo.setAvatarUrl(passenger.getPassengerAvater());//获取头像
        log.info("获取用户的信息");
        return userInfo;
    }

    public Passenger findById(Integer userId) {

        log.info("查找用户，根据id");

        return passengerMapper.selectByPrimaryKey(userId);
    }

    /**
     * 获取openId用户信息
     * @param openId
     * @return
     */
    public Passenger queryByOid(String openId) {
        PassengerExample example=new PassengerExample();
        example.or().andWeixinOpenidEqualTo(openId).andPassengerStatusEqualTo(0);//可用的
        List<Passenger> users=passengerMapper.selectByExample(example);
        if(users!=null&&users.size()>0){
            return users.get(0);
        }
        return null;
    }

    /**
     * 添加用户
     * @param user
     */
    public void add(Passenger user) {
        log.info("添加用户");
        passengerMapper.insertSelective(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void update(Passenger user) {
        log.info("更新用户");
        passengerMapper.updateByPrimaryKeySelective(user);
    }

}
