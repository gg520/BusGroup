package java.com.wxbus.daomain;

import java.util.Date;

public class Passenger {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_citizenship
     *
     * @mbggenerated
     */
    private String passengerCitizenship;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_nickname
     *
     * @mbggenerated
     */
    private String passengerNickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_name
     *
     * @mbggenerated
     */
    private String passengerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_password
     *
     * @mbggenerated
     */
    private String passengerPassword;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_gender
     *
     * @mbggenerated
     */
    private String passengerGender;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_birthday
     *
     * @mbggenerated
     */
    private Date passengerBirthday;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.last_login_time
     *
     * @mbggenerated
     */
    private Date lastLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.last_login_ip
     *
     * @mbggenerated
     */
    private String lastLoginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_mobile
     *
     * @mbggenerated
     */
    private String passengerMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_avater
     *
     * @mbggenerated
     */
    private String passengerAvater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.weixin_openid
     *
     * @mbggenerated
     */
    private String weixinOpenid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.passenger_status
     *
     * @mbggenerated
     */
    private Integer passengerStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.fist_login_time
     *
     * @mbggenerated
     */
    private Date fistLoginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.fist_login_ip
     *
     * @mbggenerated
     */
    private String fistLoginIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger.deleted
     *
     * @mbggenerated
     */
    private Integer deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.id
     *
     * @return the value of passenger.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.id
     *
     * @param id the value for passenger.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_citizenship
     *
     * @return the value of passenger.passenger_citizenship
     *
     * @mbggenerated
     */
    public String getPassengerCitizenship() {
        return passengerCitizenship;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_citizenship
     *
     * @param passengerCitizenship the value for passenger.passenger_citizenship
     *
     * @mbggenerated
     */
    public void setPassengerCitizenship(String passengerCitizenship) {
        this.passengerCitizenship = passengerCitizenship == null ? null : passengerCitizenship.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_nickname
     *
     * @return the value of passenger.passenger_nickname
     *
     * @mbggenerated
     */
    public String getPassengerNickname() {
        return passengerNickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_nickname
     *
     * @param passengerNickname the value for passenger.passenger_nickname
     *
     * @mbggenerated
     */
    public void setPassengerNickname(String passengerNickname) {
        this.passengerNickname = passengerNickname == null ? null : passengerNickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_name
     *
     * @return the value of passenger.passenger_name
     *
     * @mbggenerated
     */
    public String getPassengerName() {
        return passengerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_name
     *
     * @param passengerName the value for passenger.passenger_name
     *
     * @mbggenerated
     */
    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName == null ? null : passengerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_password
     *
     * @return the value of passenger.passenger_password
     *
     * @mbggenerated
     */
    public String getPassengerPassword() {
        return passengerPassword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_password
     *
     * @param passengerPassword the value for passenger.passenger_password
     *
     * @mbggenerated
     */
    public void setPassengerPassword(String passengerPassword) {
        this.passengerPassword = passengerPassword == null ? null : passengerPassword.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_gender
     *
     * @return the value of passenger.passenger_gender
     *
     * @mbggenerated
     */
    public String getPassengerGender() {
        return passengerGender;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_gender
     *
     * @param passengerGender the value for passenger.passenger_gender
     *
     * @mbggenerated
     */
    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender == null ? null : passengerGender.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_birthday
     *
     * @return the value of passenger.passenger_birthday
     *
     * @mbggenerated
     */
    public Date getPassengerBirthday() {
        return passengerBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_birthday
     *
     * @param passengerBirthday the value for passenger.passenger_birthday
     *
     * @mbggenerated
     */
    public void setPassengerBirthday(Date passengerBirthday) {
        this.passengerBirthday = passengerBirthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.last_login_time
     *
     * @return the value of passenger.last_login_time
     *
     * @mbggenerated
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.last_login_time
     *
     * @param lastLoginTime the value for passenger.last_login_time
     *
     * @mbggenerated
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.last_login_ip
     *
     * @return the value of passenger.last_login_ip
     *
     * @mbggenerated
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.last_login_ip
     *
     * @param lastLoginIp the value for passenger.last_login_ip
     *
     * @mbggenerated
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_mobile
     *
     * @return the value of passenger.passenger_mobile
     *
     * @mbggenerated
     */
    public String getPassengerMobile() {
        return passengerMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_mobile
     *
     * @param passengerMobile the value for passenger.passenger_mobile
     *
     * @mbggenerated
     */
    public void setPassengerMobile(String passengerMobile) {
        this.passengerMobile = passengerMobile == null ? null : passengerMobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_avater
     *
     * @return the value of passenger.passenger_avater
     *
     * @mbggenerated
     */
    public String getPassengerAvater() {
        return passengerAvater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_avater
     *
     * @param passengerAvater the value for passenger.passenger_avater
     *
     * @mbggenerated
     */
    public void setPassengerAvater(String passengerAvater) {
        this.passengerAvater = passengerAvater == null ? null : passengerAvater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.weixin_openid
     *
     * @return the value of passenger.weixin_openid
     *
     * @mbggenerated
     */
    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.weixin_openid
     *
     * @param weixinOpenid the value for passenger.weixin_openid
     *
     * @mbggenerated
     */
    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid == null ? null : weixinOpenid.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.passenger_status
     *
     * @return the value of passenger.passenger_status
     *
     * @mbggenerated
     */
    public Integer getPassengerStatus() {
        return passengerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.passenger_status
     *
     * @param passengerStatus the value for passenger.passenger_status
     *
     * @mbggenerated
     */
    public void setPassengerStatus(Integer passengerStatus) {
        this.passengerStatus = passengerStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.fist_login_time
     *
     * @return the value of passenger.fist_login_time
     *
     * @mbggenerated
     */
    public Date getFistLoginTime() {
        return fistLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.fist_login_time
     *
     * @param fistLoginTime the value for passenger.fist_login_time
     *
     * @mbggenerated
     */
    public void setFistLoginTime(Date fistLoginTime) {
        this.fistLoginTime = fistLoginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.fist_login_ip
     *
     * @return the value of passenger.fist_login_ip
     *
     * @mbggenerated
     */
    public String getFistLoginIp() {
        return fistLoginIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.fist_login_ip
     *
     * @param fistLoginIp the value for passenger.fist_login_ip
     *
     * @mbggenerated
     */
    public void setFistLoginIp(String fistLoginIp) {
        this.fistLoginIp = fistLoginIp == null ? null : fistLoginIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger.deleted
     *
     * @return the value of passenger.deleted
     *
     * @mbggenerated
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger.deleted
     *
     * @param deleted the value for passenger.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}