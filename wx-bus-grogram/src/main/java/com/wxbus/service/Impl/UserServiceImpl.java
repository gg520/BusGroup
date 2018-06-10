package com.wxbus.service.Impl;


import com.wxbus.dao.DriverMapper;
import com.wxbus.dao.PassengerMapper;
import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverExample;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerExample;
import com.wxbus.pojo.UserInfo;
import com.wxbus.service.UserService;
import com.wxbus.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by g1154 on 2018/4/14.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private PassengerMapper passengerMapper;
    @Resource
    private DriverMapper driverMapper;

    private final Log log= LogFactory.getLog(UserServiceImpl.class.getName());
    @Override
    public UserInfo getInfo(Integer userId) {
        Passenger passenger= passengerMapper.selectByPrimaryKey(userId);
        Assert.state(passenger!=null,"用户不存在");
        UserInfo userInfo=new UserInfo();
        userInfo.setNickName(passenger.getPassengerNickname());//获取昵称
        userInfo.setAvatarUrl(passenger.getPassengerAvater());//获取头像
        log.info("获取用户的信息");
        return userInfo;
    }
    @Override
    public Passenger findById(Integer userId) {

        log.info("查找用户，根据id");

        return passengerMapper.selectByPrimaryKey(userId);
    }

    /**
     * 获取openId用户信息
     * @param openId
     * @return
     */
    @Override
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
    @Override
    public void add(Passenger user) {
        log.info("添加用户");
        passengerMapper.insertSelective(user);
    }

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void update(Passenger user) {
        log.info("更新用户");
        passengerMapper.updateByPrimaryKeySelective(user);
    }
    @Override
    /**
     *@type method
     *@parameter  [mobile, password]
     *@back  com.wxbus.daomain.Passenger
     *@author  如花
     *@creattime 2018/5/25
     *@describe 账号密码登录接口实现
     */
    public String findUserByMoPaw(String username,String password) {
        //查询司机账号信息
        DriverExample driverExample=new DriverExample();
        driverExample.or().andDriverIdEqualTo(username).andDriverPasswordEqualTo(password);
        List<Driver> list=driverMapper.selectByExample(driverExample);

        Map map=new HashMap();
        UserInfo userInfo=new UserInfo();

//        userInfo.setCity("Henan");
//        userInfo.setCountry("China");
//        userInfo.setLanguage("zh_CN");
//        userInfo.setProvince("22");
        if(null==list||list.size()<=0){//不是司机账号
            PassengerExample passengerExample=new PassengerExample();
            passengerExample.or().andPassengerMobileEqualTo(username).
                    andPassengerPasswordEqualTo(password);
            List<Passenger> passengerList=passengerMapper.selectByExample(passengerExample);
            if(passengerList!=null&&passengerList.size()>0){

                userInfo.setAvatarUrl(passengerList.get(0).getPassengerAvater());

                userInfo.setGender((byte) (passengerList.get(0).getPassengerGender().equals("男")?'1':'2'));
                userInfo.setNickName(passengerList.get(0).getPassengerNickname());

                log.info("乘客登陆成功");
                map.put("userId",passengerList.get(0).getId());
                map.put("userInfo",userInfo);
                map.put("Connect_Platform","Weixin_Passenger");
                return JsonUtil.stringify(map);
            }else{
                log.info("账号或密码错误");
                return null;
            }
        }else{

            //司机设置信息
            userInfo.setAvatarUrl(list.get(0).getDriverAvater());
            userInfo.setCity(list.get(0).getDriverAddress());
            userInfo.setGender((byte) (list.get(0).getDriverGender().equals("男")?'1':'2'));
            userInfo.setNickName(list.get(0).getDriverName());


            log.info("司机登录成功");
            map.put("userId",list.get(0).getDriverNum());

            map.put("userInfo",userInfo);
            map.put("Connect_Platform","Weixin_Driver");

            return JsonUtil.stringify(map);
        }
    }

    @Override
    /**
     *@type method
     *@parameter  [passenger]
     *@back  void
     *@author  如花
     *@creattime 2018/5/26
     *@describe 用户登陆接口实现
     */
    public void addPassenger(Passenger passenger) {
        log.info("添加乘客");
        passengerMapper.insertSelective(passenger);
    }

    @Override
    /**
     *@type method
     *@parameter  [passenger]
     *@back  void
     *@author  如花
     *@creattime 2018/5/26
     *@describe
     */
    public void updatePassenger(Passenger passenger) {
        log.info("更新用户信息");
        PassengerExample passengerExample=new PassengerExample();
        passengerExample.or().andPassengerMobileEqualTo(passenger.getPassengerMobile()).andPassengerPasswordEqualTo(passenger.getPassengerPassword());
        passengerMapper.updateByExampleSelective(passenger,passengerExample);

    }
}
