package com.wxbus.wxController;

import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverBusRoute;
import com.wxbus.daomain.Passenger;
import com.wxbus.daomain.PassengerRoute;
import com.wxbus.service.*;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.QRcodeUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by g1154 on 2018/5/5.
 */
@RestController
@RequestMapping(value = "/weixin/check")
public class WxCheckInfoController {

    private final Log logger= LogFactory.getLog(WxCheckInfoController.class.getName());



    @Autowired
    private CheckServier checkServier;
    @Autowired
    private DriverService driverService;
    @Autowired
    private DriverBusRouteService driverBusRouteService;
    @Autowired
    private PassengerRouteService passengerRouteService;

    @GetMapping("info")
    public Object info(HttpServletRequest request){

        if("Weixin_Passenger".equals(request.getHeader(HeadersName.CONN_USER))){//是不是乘客的请求
            String token=request.getHeader(HeadersName.TOKEN);
            //获取信息

            String body = UserTokenManager.getUserId(token);
            Integer id=Integer.valueOf(JacksonUtil.parseString(body, "userId"));
            //数据库中获取数据
            Passenger passenger=checkServier.getPassengerInfo(id);
            if(passenger.getPassengerCitizenship()!=null&&!"".equals(passenger.getPassengerCitizenship())
                    &&passenger.getPassengerMobile()!=null&&!"".equals(passenger.getPassengerMobile())){

                return ResponseUtil.ok("已完成實名認證");//驗證通過
            }

        }
        return ResponseUtil.fail(402,"未實名認證");
    }
    /**
     * 检查是否预定
     * @param body 人信息和车的信息
     * @return
     */
    @RequestMapping(value = "/passenger",method = {RequestMethod.POST})
    public Object passenger(@RequestBody String body){
        logger.info("验证人与车的关系");
//        FullUserInfo fullUserInfo = JacksonUtil.parseObject(body, "userInfo", FullUserInfo.class);
//        if(fullUserInfo == null){//是否有此人
//            return ResponseUtil.badArgument();
//        }
        return ResponseUtil.ok();//测试数据
    }

    /**
     * 生成二维码
     * @param response
     * @param request
     */
    @RequestMapping(value = "/getQrcode",method = {RequestMethod.POST})
    public void getQrcode(HttpServletResponse response, HttpServletRequest request){
        logger.info("生成二维码");
        QRcodeUtil qRcodeUtil=new QRcodeUtil();
        try {
            //测试连接
            qRcodeUtil.getTwoDimension("http://localhost:8888/check/passenger",response,200);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    

    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/20
     *@describe 
     */
    @RequestMapping(value = "/password",method =RequestMethod.POST)
    public Object checkPassword(@RequestBody String body,HttpServletRequest request){

        String token=request.getHeader(HeadersName.TOKEN);
        //获取信息

        String json = UserTokenManager.getUserId(token);
        Integer id=Integer.valueOf(JacksonUtil.parseString(json, "userId"));
        //数据库中获取数据
        Passenger pger=checkServier.getPassengerInfo(id);
        if(pger.getPassengerPassword()==null||"".equals(pger.getPassengerPassword())){
            logger.warn("密码未设置");
            return ResponseUtil.ok();
        }
        if(body==null||"".equals(body)){
            return  ResponseUtil.fail403();

        }
        else{

            String password=JacksonUtil.parseString(body,"password");
            Passenger passenger=checkServier.findPassangerByPassword(password);
            if(passenger==null){
                return ResponseUtil.fail();
            }
            return ResponseUtil.ok();
        }
    }


    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/26
     *@describe 检查乘客是否可以坐车（两种方法）
     */
    @RequestMapping(value = "/checkTicket",method =RequestMethod.POST)
    public Object checkTicket(@RequestBody String body,HttpServletRequest request){
        logger.info("司机检查乘客是否可以坐车");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if("乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail302();
        }
        Integer driverNum=JacksonUtil.parseInteger(json,"userId");
        Driver driver=driverService.findDriverByDriverNum(driverNum);
        if(driver==null){
            return ResponseUtil.fail(500,"未找到相关司机信息");
        }
        String driverId=driver.getDriverId();
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        List<DriverBusRoute> list=driverBusRouteService.findInfoByDriverId(driverId);
        if(list==null||list.size()<=0){
            return  ResponseUtil.fail(500,"未找到相关绑定信息");
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDriverOutTime()==null){
                driverBusRoute=list.get(i);
            }
        }
        Integer routeId=driverBusRoute.getRouteId();
        Integer passengerId=JacksonUtil.parseInteger(body,"passengerId");
        PassengerRoute passengerRoute=passengerRouteService.findByPassengerIdRouteId(passengerId,routeId);
        if(passengerRoute==null){
            return ResponseUtil.fail(500,"此乘客未绑定车辆");
        }
//        if(passengerRoute.getStartStatus()!=1){
//            return ResponseUtil.fail(500,"此乘客未支付");
//        }
        return ResponseUtil.ok("此乘客可以乘坐");
    }
    /**
     *@type method
     *@parameter  [body]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/27
     *@describe 验证乘客是否有坐车权限（两种方法）
     */
    @RequestMapping(value = "/checkPDB",method =RequestMethod.POST)
    public Object checkPDB(@RequestBody String body,HttpServletRequest request){
        logger.info("验证乘客是否有坐车权限");
        String json= UserTokenManager.getUserId(request.getHeader(HeadersName.TOKEN));
        if(!"乘客".equals(JacksonUtil.parseString(json,"user"))){
            return ResponseUtil.fail401();
        }
        Integer passengerId=JacksonUtil.parseInteger(json,"userId");
        String driverCitizenship=JacksonUtil.parseString(body,"driverCitizenship");
        Driver driver=driverService.findDriverByCitizenship(driverCitizenship);
        if(driver==null){
            return ResponseUtil.fail(500,"未找到相关司机信息");
        }
        String driverId=driver.getDriverId();
        DriverBusRoute driverBusRoute=new DriverBusRoute();
        List<DriverBusRoute> list=driverBusRouteService.findInfoByDriverId(driverId);
        if(list==null||list.size()<=0){
            return  ResponseUtil.fail(500,"未找到相关绑定信息");
        }
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDriverOutTime()==null){
                driverBusRoute=list.get(i);
            }
        }
        Integer routeId=driverBusRoute.getRouteId();
        PassengerRoute passengerRoute=passengerRouteService.findByPassengerIdRouteId(passengerId,routeId);
        if(passengerRoute==null){
            return ResponseUtil.fail(500,"此乘客未绑定车辆");
        }
        if(passengerRoute.getStartStatus()!=1){
            return ResponseUtil.fail(500,"此乘客未支付");
        }
        return ResponseUtil.ok("此乘客可以乘坐");
    }
}
