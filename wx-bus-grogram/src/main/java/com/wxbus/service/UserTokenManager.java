package com.wxbus.service;

import com.wxbus.pojo.UserToken;
import com.wxbus.util.CharUtil;
import com.wxbus.util.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class UserTokenManager {
    private static Map<String, UserToken> tokenMap = new HashMap<>();
    private static Map<Integer, UserToken> idMap = new HashMap<>();

    //日志
    private static final Log logger= LogFactory.getLog(UserTokenManager.class.getName());

    /**
     * 根据token获取ID,和身份 在json中
     *  userId:用户主键ID
     *  user： 用户身份 “司机、乘客”
     * @param token
     * @return
     */
    public static String getUserId(String token) {

        UserToken userToken = tokenMap.get(token);
        if(userToken == null){
            logger.warn("未找到 token");
            return null;
        }
        if(userToken.getExpireTime().isBefore(LocalDateTime.now())){
            tokenMap.remove(token);
            idMap.remove(userToken.getUserId());
            logger.warn("过时的token");
            return null;
        }
        logger.info("验证token");
        Map map=new HashMap();
        map.put("userId",userToken.getUserId());
        map.put("user",userToken.getUser());
        return JsonUtil.stringify(map);
    }


    /**
     * 获取token
     * @param id
     * @param user 用户类型，司机，乘客
     * @return
     */
    public static UserToken generateToken(Integer id, String user){
        //检查是否已经登录了

        while (idMap.containsKey(id)){
            UserToken m=idMap.remove(id);

            System.out.println(m.getToken());
            tokenMap.remove(m.getToken());
        }
        System.out.println(idMap.size()+"   "+tokenMap.size());

        UserToken userToken = null;
        String token = CharUtil.getRandomString(32);//获取随机数
        while (tokenMap.containsKey(token)) {
            token = CharUtil.getRandomString(32);
        }

        LocalDateTime update = LocalDateTime.now();
        LocalDateTime expire = update.plusDays(1);

        userToken = new UserToken();
        userToken.setToken(token);
        userToken.setUpdateTime(update);
        userToken.setExpireTime(expire);
        userToken.setUserId(id);
        userToken.setUser(user);
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

//        System.out.println("生成的token："+token);
        return userToken;
    }
}
