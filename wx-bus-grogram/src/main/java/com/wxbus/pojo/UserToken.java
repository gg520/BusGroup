package com.wxbus.pojo;

import java.time.LocalDateTime;

public class UserToken {
    private Integer userId;
    private String token;
    private LocalDateTime expireTime;
    private LocalDateTime updateTime;

    private String user;//用户权限，乘客或者司机

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
