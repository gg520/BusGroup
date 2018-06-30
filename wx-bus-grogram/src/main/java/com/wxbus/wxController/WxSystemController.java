package com.wxbus.wxController;


import com.wxbus.util.ResponseUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by g1154 on 2018/6/29.
 *
 * @page： com.wxbus.wxController
 * @Description:
 */
@RestController
@RequestMapping("/weixin/system")
public class WxSystemController {


    private final Log logger= LogFactory.getLog(WxSystemController.class.getName());

    @PostMapping(value = "getNotice")
    /**
     *@type method
     *@parameter  []
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/27
     *@describe 获取系统通知公告文件内容
     */
    public Object getNotice(){
        String temp;
        List list=new ArrayList();
        try {
            File file= ResourceUtils.getFile("classpath:dataFile/NOTICE.txt");
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            logger.info("按行读取文件");
            while((temp=bufferedReader.readLine())!=null){
//                temp=temp.replaceAll("\\\\","");
                list.add(temp);
            }
            bufferedReader.close();
        }catch (IOException ie){
            ie.printStackTrace();
        }
        return  ResponseUtil.ok(list.toString().replaceAll("\\\\",""));

//        Body = {"errno":0,"data":[{"routeId":4,"creatUser":1,"creatTime":"2018-05-10T01:03:02.000+0000","routeStatus":5,"runTime":"7","startSite":"新政","startCoord":"10,20","endSite":"龙湖","endCoord":"60,70","stationId":"1,2,3","startTime":"09:11","endTime":"09:03","price":3.0,"recruitNum":10,"passengerCount":1}],"errmsg":"成功"}
    }
}

