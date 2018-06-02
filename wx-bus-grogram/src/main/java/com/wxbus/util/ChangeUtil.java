package com.wxbus.util;


/**
 * @author: Demon
 * @date: 2018/5/17
 * @time: 19:12
 * Description:字符串转换用于模糊匹配
 */
public class ChangeUtil {
    public static String changeString(String string) {
        String regex = "(.{1})";
        string = string.replaceAll(regex, "$1%");
        return string;
    }


}