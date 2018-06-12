package com.wxbus.wxController;

import com.wxbus.annotation.LoginUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxbus.util.ResponseUtil;

/**
 * 支付
 * Created by g1154 on 2018/6/12.
 */
@RestController
@RequestMapping("weixin/pay")
public class PayController {


    @RequestMapping("prepay")
    /**
     *
     * 功能描述: 支付
     *
     * @param: [userId:用户ID, orderId:订单ID]
     * @return: java.lang.Object
     * @auther: g1154
     * @date: 2018/6/12 15:53
     */
    public Object payPrepay(@LoginUser Integer userId, Integer orderId) {


        if(userId == null){
            return ResponseUtil.fail401();
        }
        if(orderId == null){
            return ResponseUtil.fail402();
        }

        return ResponseUtil.fail401();

    }
}
