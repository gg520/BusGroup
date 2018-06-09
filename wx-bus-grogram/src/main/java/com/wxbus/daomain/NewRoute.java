package com.wxbus.daomain;

/**
 * @author: Demon
 * @date: 2018/6/8
 * @time: 21:07
 * Description:
 */
public class NewRoute extends Route {
    public NewRoute(Route route) {
        this.setCreatTime(route.getCreatTime());
        this.setCreatUser(route.getCreatUser());
        this.setEndCoord(route.getEndCoord());
        this.setEndSite(route.getEndSite());
        this.setPrice(route.getPrice());
        this.setEndTime(route.getEndTime());
        this.setRecruitNum(route.getRecruitNum());
        this.setRouteId(route.getRouteId());
        this.setRecruitTime(route.getRecruitTime());
        this.setRouteStatus(route.getRouteStatus());
        this.setRunTime(route.getRunTime());
        this.setStartCoord(route.getStartCoord());
        this.setStartSite(route.getStartSite());
        this.setStartTime(route.getStartTime());
        this.setStationId(route.getStationId());

    }

    private Integer passengerCount;
    public Integer getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(Integer passengerCount) {
        this.passengerCount = passengerCount;
    }



}