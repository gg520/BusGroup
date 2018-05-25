package com.wxbus.config;

import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.github.binarywang.wxpay.config.WxPayConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信数据的配置
 */
@Configuration
public class WeixinConfig {
    public static final String WX_AppId = "wxcafa41f5d5ff69b0";
    public static final String WX_Secret = "475bd4f487892ff68ac72b98c77f323d";
    public static final String WX_Token = "";
    public static final String WX_AesKey = "";
    public static final String WX_MsgDataFormat = "JSON";


    public static final String WX_MchId = "";
    public static final String WX_MchKey = "";
    public static final String WX_KeyPath = "";

    @Bean
    public WxMaConfig wxMaConfig() {
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(WX_AppId);
        config.setSecret(WX_Secret);
        config.setToken(WX_Token);
        config.setAesKey(WX_AesKey);
        config.setMsgDataFormat(WX_MsgDataFormat);

        return config;
    }


    @Bean
    public WxPayConfig wxPayConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(WX_AppId);
        payConfig.setMchId(WX_MchId);
        payConfig.setMchKey(WX_MchKey);
        payConfig.setSubAppId(null);
        payConfig.setSubMchId(null);
        payConfig.setKeyPath(WX_KeyPath);

        return payConfig;
    }
}