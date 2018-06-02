package com.wxbus.wxController;

import com.wxbus.daomain.Passenger;
import com.wxbus.service.CheckServier;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.QRcodeUtil;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by g1154 on 2018/5/22.
 */
@Controller
@RequestMapping("/weixin/qrcode")
public class QRcodeController {

    private Log logger= LogFactory.getLog(QRcodeController.class);

    @Autowired
    private CheckServier checkServier;

    @GetMapping(value = "getQRcode")
    public void getQRcode(HttpServletRequest request, HttpServletResponse response){
        //獲取用戶信息
        if("Weixin_Passenger".equals(request.getHeader(HeadersName.CONN_USER))){//是不是乘客的请求
            String token=request.getHeader(HeadersName.TOKEN);
            //获取信息

            String userInfo = UserTokenManager.getUserId(token);
            String user= JacksonUtil.parseString(userInfo,"user");
            Integer id= JacksonUtil.parseInteger(userInfo,"userId");
            if((user!=null&&!"".equals(user))&&user.equals("乘客")){
                //数据库中获取数据
                Passenger passenger=checkServier.getPassengerInfo(id);
                if(passenger.getPassengerCitizenship()!=null&&!"".equals(passenger.getPassengerCitizenship())
                        &&passenger.getPassengerMobile()!=null&&!"".equals(passenger.getPassengerMobile())){

                    logger.info("实名验证成功");

                    //将用户的ID生成到二维码信息中
                    String info="passenger:"+id;
                    QRcodeUtil qrcodeUtil=new QRcodeUtil();

                    try {
                        qrcodeUtil.getTwoDimension(info,response,300);
                    }catch (Exception e){
                        logger.error("生成二维码异常");
                        e.printStackTrace();
                    }

                }else{
                    logger.info("实名验证结果：未认证");
                    response.setStatus(402);//未登录
                    response.setContentType("text/html;charset=utf-8");
                    PrintWriter out=null;
                    try{
                        out=response.getWriter();
                        out.println(ResponseUtil.fail(-1,"未实名验证"));
                    }catch (Exception e){
                        e.printStackTrace();
                    }finally {
                        out.close();
                    }
                }

            }

        }
    }
}
