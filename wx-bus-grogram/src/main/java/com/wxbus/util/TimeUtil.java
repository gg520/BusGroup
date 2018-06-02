package com.wxbus.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by g1154 on 2018/4/25.
 */
public class TimeUtil {

    public static String getTimeByType(Date date,String type){
        if(date==null)
            return null;
        if(type==null||type.length()<=0){
            type="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }

    public static Date getTimeByString(String date,String type){
        if(date==null)
            return null;
        if(type==null||type.length()<=0){
            type="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(type);

        try {
            return simpleDateFormat.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
