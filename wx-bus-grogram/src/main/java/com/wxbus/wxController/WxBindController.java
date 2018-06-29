package com.wxbus.wxController;

import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.service.DriverBusRouteService;
import com.wxbus.service.DriverService;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.JsonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.webController.WebSearchController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/25
 * @time: 10:20
 * Description:绑定controller
 */
@RestController
@RequestMapping(value = "/weixin/bind")
public class WxBindController {
    @Resource
    private DriverService driverService;
    @Resource
    private DriverBusRouteService driverBusRouteService;
    private final Log logger = LogFactory.getLog(WxBindController.class);
    @PostMapping(value = "bindCar")
    /**
     *@type method
     *@parameter  [body, request]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/25
     *@describe 司机绑定车辆
     */
    public Object driverBindCar(@RequestBody String body, HttpServletRequest request){
        logger.info("司机绑定车辆");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver=driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return ResponseUtil.fail(500,"未找到相关司机信息");
        }
        String driverId=driver.getDriverId();
        String busId=JacksonUtil.parseString(body,"result");//获取ip

        //验证车辆是否可以绑定车辆
        if(!driverBusRouteService.checkDriverByDriverID(driverId,busId)){//司机登录账号，汽车牌号
            return ResponseUtil.fail(-1,"未领取任务");
        }else {
            //可以扫码，将状态设置成为1
            if(driverBusRouteService.setBindSuccess(driverId,busId)){
                return ResponseUtil.ok();
            }
        }
        return ResponseUtil.fail();
    }
}