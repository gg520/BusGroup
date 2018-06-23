package com.wxbus.util;

import java.util.UUID;

/**
 * Created by g1154 on 2018/6/23.
 *
 * @page： com.wxbus.util
 * @Description:
 */
public class UUIDUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }
}
