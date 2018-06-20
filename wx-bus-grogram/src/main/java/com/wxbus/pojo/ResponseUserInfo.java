package com.wxbus.pojo;

/**
 * Created by g1154 on 2018/6/10.
 */
public class ResponseUserInfo extends UserInfo{
    //添加字段
    //手机号码
    private String mobile;

    //公民身份证件号码
    private String citizenship;

    //家庭详细地址
    private String address;

    private String birthday;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserInfo(UserInfo userInfo){
        this.setAvatarUrl(userInfo.getAvatarUrl()!=null?userInfo.getAvatarUrl():"");
        this.setCity(userInfo.getCity()!=null?userInfo.getCity():"");
        this.setCountry(userInfo.getCountry()!=null?userInfo.getCountry():"");

        this.setLanguage(userInfo.getLanguage()!=null?userInfo.getLanguage():"");
        this.setNickName(userInfo.getNickName()!=null?userInfo.getNickName():"");
        this.setProvince(userInfo.getProvince()!=null?userInfo.getProvince():"");
        this.setGender(userInfo.getGender()!=0?userInfo.getGender():0);
    }

    @Override
    public String toString() {
        return "ResponseUserInfo{" +
                "mobile='" + mobile + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
