package com.wxbus.util;

import java.security.MessageDigest;

/**
 * Created by g1154 on 2018/5/27.
 */
public class MD5Util {

    public static String toMD5(String plainText) {
        if(plainText==null||"".equals(plainText))
            return null;
        try {
            //生成实现指定摘要算法的 MessageDigest 对象。
            MessageDigest md = MessageDigest.getInstance("MD5");
            //使用指定的字节数组更新摘要。
            md.update(plainText.getBytes());
            //通过执行诸如填充之类的最终操作完成哈希计算。
            byte b[] = md.digest();
            //生成具体的md5密码到buf数组
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            // System.out.println("32位: " + buf.toString());
            // System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
        }
        catch (Exception e) {
            System.err.println("md5加密出现异常！！");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(toMD5("123456"));
    }
}
