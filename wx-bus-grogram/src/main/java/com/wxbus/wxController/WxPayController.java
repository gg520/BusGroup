package com.wxbus.wxController;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.wxbus.annotation.LoginUser;
import com.wxbus.daomain.PassengerRoute;
import com.wxbus.service.HeadersName;
import com.wxbus.service.PassengerRouteService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.UUIDUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wxbus.util.ResponseUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付
 * Created by g1154 on 2018/6/12.
 */
@RestController
@RequestMapping("weixin/pay")
public class WxPayController {


    private static final Log logger= LogFactory.getLog(WxPayController.class);

    @Autowired
    private WxPayService wxService;

    @Autowired
    private PassengerRouteService passengerRouteService;

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
    public Object payPrepay(@RequestBody String body, HttpServletRequest request) {

        String tokenInfo= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));

        if(tokenInfo==null||"".equals(tokenInfo)){
            return ResponseUtil.fail401();
        }else{
            if(!"乘客".equals(JacksonUtil.parseString(tokenInfo,"user"))){
                return ResponseUtil.fail302();
            }
        }
        /**
         * routeId: this.data.routeid,
         payMoney: this.data.totalmoney,//支付的总钱数
         reserveDay: this.data.countday,//订购的总天数
         reserveDays: this.data.selectdays,//订购的日期列表
         */
        //查询验证
//暂时没有企业号，无法测试注销

//        PassengerRoute passengerRoute=new PassengerRoute();
//        passengerRoute.setCreatTime(new Date());
//        passengerRoute.setPassengerId(JacksonUtil.parseInteger(tokenInfo,"userId"));
//        passengerRoute.setPayMoney(Double.valueOf(JacksonUtil.parseString(body,"payMoney")));
//        passengerRoute.setReserveDay(JacksonUtil.parseInteger(body,"reserveDay"));
//        passengerRoute.setReserveDays(JacksonUtil.parseString(body,"reserveDays"));
//        passengerRoute.setStartStatus(2);
//
//        logger.warn("支付");
//        passengerRouteService.addPassengerRoute(passengerRoute);
//
//
//        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest=new WxPayUnifiedOrderRequest();
//
//        WxPayUnifiedOrderResult wxPayUnifiedOrderResult=null;
//        try {
//            wxPayUnifiedOrderResult = wxService.unifiedOrder(wxPayUnifiedOrderRequest);
//        } catch (WxPayException e) {
//            e.printStackTrace();
//            return ResponseUtil.fail(403, "支付失败");
//        }

        Map map=new HashMap();
        /**
         *  wx.requestPayment({
         'timeStamp': timestamp,//时间戳
         'nonceStr': nonceStr,//随机数 大小写字母
         'package': 'prepay_id=' + prepayId,//支付订单
         'signType': 'MD5',//编码方式
         'paySign': paySign,//编码后的标识
         'success':function(res){
         // 保留当前页面，跳转到应用内某个页面，使用wx.nevigeteBack可以返回原页面
         wx.navigateTo({
         url: '../pay/pay'
         })
         },
         'fail':function(res){
         console.log(res.errMsg)
         }
         })
         */

//        map.put("nonceStr", UUIDUtil.getUUID());
//        map.put("package",wxPayUnifiedOrderResult.getPrepayId());
//        map.put("signType",wxPayUnifiedOrderResult.getTradeType());
//        map.put("paySign",wxPayUnifiedOrderResult.getSign());
//        map.put("id",id);
        map.put("nonceStr", UUIDUtil.getUUID());
        map.put("package","prePayId");
        map.put("signType","MD5");
        map.put("paySign",UUIDUtil.getUUID());
        map.put("id",1);
        return ResponseUtil.ok(map);

    }
}
