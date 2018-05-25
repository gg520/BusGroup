package com.wxbus.service;

import com.wxbus.pojo.UserToken;
import com.wxbus.util.CharUtil;
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
    public static Integer getUserId(String token) {


        //测试
//        System.out.println("token数据："+tokenMap.size());


//        System.out.println("token:"+token);

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
        return userToken.getUserId();
    }


    public static UserToken generateToken(Integer id){
        UserToken userToken = null;

//        userToken = idMap.get(id);
//        if(userToken != null) {
//            tokenMap.remove(userToken.getToken());
//            idMap.remove(id);
//        }

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
        tokenMap.put(token, userToken);
        idMap.put(id, userToken);

//        System.out.println("生成的token："+token);
        return userToken;
    }
}
