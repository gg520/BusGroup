package com.wxbus.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
    public static String client(HttpServletRequest request) {
        String xff = request.getHeader("X-Bus-Token");
        if (xff == null) {
            xff = request.getRemoteAddr();
        }
        return xff;
    }
}
