package java.com.wxbus.service.Impl;

import com.wxbus.dao.PassengerMapper;
import com.wxbus.daomain.Passenger;
import com.wxbus.service.CheckServier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by g1154 on 2018/5/22.
 */
@Service
public class CheckServiceImpl implements CheckServier {

    @Autowired
    private PassengerMapper passengerMapper;
    @Override
    public Passenger getPassengerInfo(Integer id) {

        return passengerMapper.selectByPrimaryKey(id);
    }
}
