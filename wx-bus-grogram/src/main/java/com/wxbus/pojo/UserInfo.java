package com.wxbus.pojo;

public class UserInfo {
    //昵称
    private String nickName;
    //头像连接
    private String avatarUrl;
    //城市
    private String country;
    //地方，省份
    private String province;
    //城市：
    private String city;
    //语言
    private String language;

    //性别
    private Byte gender;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getLanguage() {
        return language;
    }

    public Byte getGender() {
        return gender;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nickName='" + nickName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", language='" + language + '\'' +
                ", gender=" + gender +
                '}';
    }
}
