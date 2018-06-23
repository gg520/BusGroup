package com.wxbus.pojo;

/**
 * @author: Demon
 * @date: 2018/6/21
 * @time: 8:52
 * Description:
 */
public class NewUserRoute {

    public Integer getpRId() {
        return pRId;
    }

    public void setpRId(Integer pRId) {
        this.pRId = pRId;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    //订单信息
    private Integer pRId;
    //创建时间
    private String creatTime;
    //创建人
    private Integer creatUser;

    public Integer getCreatUser() {
        return creatUser;
    }

    public void setCreatUser(Integer creatUser) {
        this.creatUser = creatUser;
    }

    //线路状态是否过期 1 未过期，2 过期   要求后台做出判断，这个值跟数据库中的数据不对应
    private Integer routeStatus;
    //'订单编号',
    private String orderNumber;
    //订购的天数
    private Integer  reserveDay;
    //"起始地点",
    private  String startSite;
    //'到达地点',
    private String endSite;
    //单价
    private Double price;
    //"出发时间",
    private String  startTime;
    //总价 reserveDay*price
    private Double payMoney;
    //线路ID
    private Integer routeId;

    public Integer getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(Integer routeStatus) {
        this.routeStatus = routeStatus;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getReserveDay() {
        return reserveDay;
    }

    public void setReserveDay(Integer reserveDay) {
        this.reserveDay = reserveDay;
    }

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite;
    }

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getRouteId() {
        return routeId;
    }

    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}