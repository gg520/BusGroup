package java.com.wxbus.service;

import com.wxbus.daomain.Passenger;

/**
 * Created by g1154 on 2018/5/22.
 */
public interface CheckServier {

    /**
     * 根據用戶的主鍵獲取用戶信息
     * @param id
     * @return
     */
    Passenger getPassengerInfo(Integer id);
}
