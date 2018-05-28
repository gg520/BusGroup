package com.wxbus.util;

import com.wxbus.daomain.NewStation;
import com.wxbus.daomain.Station;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SortUtil {
    /**
     *@type method
     *@parameter
     *@back
     *@author  如花
     *@creattime 2018/5/28
     *@describe 将坐标与所有站点换算距离后存入新的NewStationList中
     */
    public  static  final List<NewStation> stationSort(List<Station> stationList,List<NewStation> newStationList,String [] stringsCoord){
        for(int i=0;i<stationList.size();i++){
            String [] stringsStationCoord=stationList.get(i).getStationCoord().split(",");
            //求出两点间的距离存入列表中
            double distance= MathUtil.getDiatance(stringsCoord,stringsStationCoord);
            newStationList.get(i).setStationName(stationList.get(i).getStationName());
            newStationList.get(i).setStationId(stationList.get(i).getStationId());
            newStationList.get(i).setDistance(distance);
        }


        //按距离将newStationList进行升序排序
        Collections.sort(newStationList , new Comparator<NewStation>(){

            @Override
            public int compare(NewStation o1,NewStation o2) {
                //按照距离进行排序
                if(o1.getDistance() > o2.getDistance()){
                    return 1;
                }
                if(o1.getDistance() == o2.getDistance()){
                    return 0;
                }
                return -1;
            }
        });
        return newStationList;
    }
    public static final String goodsSort(String sort, String order){
        if(sort == null){
            return null;
        }

        String sortWithOrder = "";
        if(sort.equalsIgnoreCase("price")){
            sortWithOrder = "retail_price";
        }
        else if(sort.equalsIgnoreCase("default") || sort.equalsIgnoreCase("category")){
            return null;
        }
        else{
            return null;
        }

        if(order == null) {
            return sortWithOrder;
        }

        if(order.equalsIgnoreCase("asc")){
            sortWithOrder += " ASC";
        }
        else if(order.equalsIgnoreCase("DESC")){
            sortWithOrder += " DESC";
        }

        return sortWithOrder;
    }
}
