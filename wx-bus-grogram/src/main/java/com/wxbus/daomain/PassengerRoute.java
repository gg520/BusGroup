package com.wxbus.daomain;

import java.util.Date;

public class PassengerRoute {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.p_r_id
     *
     * @mbggenerated
     */
    private Integer pRId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.order_number
     *
     * @mbggenerated
     */
    private String orderNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.passenger_id
     *
     * @mbggenerated
     */
    private String passengerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.route_id
     *
     * @mbggenerated
     */
    private Integer routeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.reserve_day
     *
     * @mbggenerated
     */
    private Integer reserveDay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.pay_money
     *
     * @mbggenerated
     */
    private String payMoney;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.creat_time
     *
     * @mbggenerated
     */
    private Date creatTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.end_time
     *
     * @mbggenerated
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.start_status
     *
     * @mbggenerated
     */
    private Integer startStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column passenger_route.reserve_days
     *
     * @mbggenerated
     */
    private String reserveDays;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.p_r_id
     *
     * @return the value of passenger_route.p_r_id
     *
     * @mbggenerated
     */
    public Integer getpRId() {
        return pRId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.p_r_id
     *
     * @param pRId the value for passenger_route.p_r_id
     *
     * @mbggenerated
     */
    public void setpRId(Integer pRId) {
        this.pRId = pRId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.order_number
     *
     * @return the value of passenger_route.order_number
     *
     * @mbggenerated
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.order_number
     *
     * @param orderNumber the value for passenger_route.order_number
     *
     * @mbggenerated
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.passenger_id
     *
     * @return the value of passenger_route.passenger_id
     *
     * @mbggenerated
     */
    public String getPassengerId() {
        return passengerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.passenger_id
     *
     * @param passengerId the value for passenger_route.passenger_id
     *
     * @mbggenerated
     */
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId == null ? null : passengerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.route_id
     *
     * @return the value of passenger_route.route_id
     *
     * @mbggenerated
     */
    public Integer getRouteId() {
        return routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.route_id
     *
     * @param routeId the value for passenger_route.route_id
     *
     * @mbggenerated
     */
    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.reserve_day
     *
     * @return the value of passenger_route.reserve_day
     *
     * @mbggenerated
     */
    public Integer getReserveDay() {
        return reserveDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.reserve_day
     *
     * @param reserveDay the value for passenger_route.reserve_day
     *
     * @mbggenerated
     */
    public void setReserveDay(Integer reserveDay) {
        this.reserveDay = reserveDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.pay_money
     *
     * @return the value of passenger_route.pay_money
     *
     * @mbggenerated
     */
    public String getPayMoney() {
        return payMoney;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.pay_money
     *
     * @param payMoney the value for passenger_route.pay_money
     *
     * @mbggenerated
     */
    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney == null ? null : payMoney.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.creat_time
     *
     * @return the value of passenger_route.creat_time
     *
     * @mbggenerated
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.creat_time
     *
     * @param creatTime the value for passenger_route.creat_time
     *
     * @mbggenerated
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.end_time
     *
     * @return the value of passenger_route.end_time
     *
     * @mbggenerated
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.end_time
     *
     * @param endTime the value for passenger_route.end_time
     *
     * @mbggenerated
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.start_status
     *
     * @return the value of passenger_route.start_status
     *
     * @mbggenerated
     */
    public Integer getStartStatus() {
        return startStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.start_status
     *
     * @param startStatus the value for passenger_route.start_status
     *
     * @mbggenerated
     */
    public void setStartStatus(Integer startStatus) {
        this.startStatus = startStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column passenger_route.reserve_days
     *
     * @return the value of passenger_route.reserve_days
     *
     * @mbggenerated
     */
    public String getReserveDays() {
        return reserveDays;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column passenger_route.reserve_days
     *
     * @param reserveDays the value for passenger_route.reserve_days
     *
     * @mbggenerated
     */
    public void setReserveDays(String reserveDays) {
        this.reserveDays = reserveDays == null ? null : reserveDays.trim();
    }
}