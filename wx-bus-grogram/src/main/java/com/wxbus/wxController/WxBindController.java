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
        String busId=JacksonUtil.parseString(body,"result");
        List<DriverBusRoute> list=driverBusRouteService.findInfoByDriverId(driverId);
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDriverOutTime()==null){
                DriverBusRoute driverBusRoute=list.get(i);
                driverBusRoute.setDriverOutTime(new Date());
                //更新司机绑定车辆的解绑时间
                driverBusRouteService.updateDriverBusRoute(driverBusRoute);
            }
        }
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        driverBusRoute.setBusId(busId);
        driverBusRoute.setDriverId(driverId);
        driverBusRoute.setDirverTime(new Date());
        driverBusRouteService.addDriverBusRoute(driverBusRoute);
        return ResponseUtil.ok();
    }
}