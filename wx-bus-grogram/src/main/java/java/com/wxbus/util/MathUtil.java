package java.com.wxbus.util;

/**
 * @author: Demon
 * @date: 2018/5/27
 * @time: 20:56
 * Description:
 */
public class MathUtil {
    //赤道半径(单位km)
    private static final  double EARTH_RADIUS = 6378.137;
    /**
     *@type method
     *@parameter
     *@back
     *@author  如花
     *@creattime 2018/5/27
     *@describe 转化为弧度
     */
    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
    /**
     *@type method
     *@parameter
     *@back
     *@author  如花
     *@creattime 2018/5/27
     *@describe  两个字符串数组计算两点距离（数组中分别存经纬度）
     */
    public static double getDiatance(String[] strings1,String[] strings2){
        //lon1 lat1 第一点的经度,纬度
        //lon2 lat2 第二点的经度,纬度
        double lat1=Double.parseDouble(strings1[1]);
        double lat2=Double.parseDouble(strings2[1]);
        double lon1=Double.parseDouble(strings1[0]);
        double lon2=Double.parseDouble(strings2[0]);
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
}