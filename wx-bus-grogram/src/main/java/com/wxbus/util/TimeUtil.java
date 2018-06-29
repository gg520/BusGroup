package com.wxbus.util;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by g1154 on 2018/4/25.
 */
public class TimeUtil {

    /**
     * 将日期转换成指定的日期格式
     * @param date
     * @param type
     * @return
     */
    public static String getTimeByType(Date date,String type){
        if(date==null)
            return null;
        if(type==null||type.length()<=0){
            type="yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(type);
        return simpleDateFormat.format(date);
    }

    /**
     * 将字符串转换成日期
     * @param date
     * @param type
     * @return
     */
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

    public static long getDifDay(Date start,Date ends){
        long endLong=ends.getTime();
        long startLong=start.getTime();
//        System.out.println("endLong:"+endLong+"  startLong:"+startLong);
//        System.out.println("差："+(endLong-startLong));
//        System.out.println((endLong-startLong)/(1000*60*60*24));
        return (endLong-startLong)/(1000*60*60*24);
    }

    /**
     * 获取下一天的时间
     * @param date
     * @return
     */
    public static Date getNextDate(Date date){
        return new Date(date.getTime()+(1000*60*60*24));
    }
    public static void main(String[] args) {

        //测试文件：./路径的数据4
        System.out.println(getTimeByType(getNextDate(new Date()),"yyyy-MM-dd HH:mm:ss"));

    }


}
