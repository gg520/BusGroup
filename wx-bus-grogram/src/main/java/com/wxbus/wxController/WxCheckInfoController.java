package com.wxbus.wxController;

import com.wxbus.daomain.Passenger;
import com.wxbus.service.CheckServier;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserService;
import com.wxbus.service.UserTokenManager;
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

/**
 * Created by g1154 on 2018/5/5.
 */
@RestController
@RequestMapping(value = "/weixin/check")
public class WxCheckInfoController {

    private final Log logger= LogFactory.getLog(WxCheckInfoController.class.getName());



    @Autowired
    private CheckServier checkServier;

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

}
