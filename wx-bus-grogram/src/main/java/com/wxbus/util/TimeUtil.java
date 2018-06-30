package com.wxbus.util;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    /**
     * 站点时间估测
     * 平均时间，
     * @param start
     * @param end
     * @param num 中间站点的个数加一
     * @return
     */
    public static List getTimeByNum(String start,String end,int num){

        if(start==null||end==null||"".equals(start)||"".equals(end)||num==0){
            return null;
        }
        String day=getTimeByType(new Date(),"yyyy-MM-dd");
        start=day+" "+start;
        end=day+" "+end;

        System.out.println(start);
        System.out.println(end);
        Date startDate=getTimeByString(start,"yyyy-MM-dd HH:mm");
        Date endDate=getTimeByString(end,"yyyy-MM-dd HH:mm");

        long dif=endDate.getTime()-startDate.getTime();

        long dids=dif/num;

        List<String> timeList=new ArrayList<>();

        for(int i=1;i<num;i++){
           timeList.add(getTimeByType(new Date(startDate.getTime()+dids*i),"HH:mm"));
        }

        System.out.println(startDate+"   "+endDate);



        return timeList;
    }

    public static void main(String[] args) {

//      getTimeByNum("07:00","10:00",2);
        System.out.println(getTimeByNum("08:00","12:00",3));
//        System.out.println(getTimeByType(new Date() , "HH:mm"));
//        System.out.println(getTimeByType(getNextDate(new Date()),"yyyy-MM-dd HH:mm:ss"));

    }


}
