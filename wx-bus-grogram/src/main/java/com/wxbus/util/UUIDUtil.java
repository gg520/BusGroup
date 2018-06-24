package com.wxbus.util;

import java.util.Random;
import java.util.UUID;

/**
 * Created by g1154 on 2018/6/23.
 *
 * @page： com.wxbus.util
 * @Description:
 */
public class UUIDUtil {

    private final String numberStr="1234567890";

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 防止与手机号码冲突，11位的数字自动增加成为12位的数字串
     * @param length
     * @return
     */
    public static String getDriverId(int length){

        if(length<=0){
            return null;
        }
        if(length==11){
            length++;
        }
        StringBuilder stringBuilder=new StringBuilder();
        for(int index=0;index<length;index++){
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getDriverId(11));
    }
}
