package com.wxbus.dao;

import com.wxbus.daomain.PassengerRoute;
import com.wxbus.daomain.PassengerRouteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PassengerRouteMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int countByExample(PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int deleteByExample(PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer pRId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int insert(PassengerRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int insertSelective(PassengerRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    List<PassengerRoute> selectByExampleWithBLOBs(PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    List<PassengerRoute> selectByExample(PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    PassengerRoute selectByPrimaryKey(Integer pRId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") PassengerRoute record, @Param("example") PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByExampleWithBLOBs(@Param("record") PassengerRoute record, @Param("example") PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") PassengerRoute record, @Param("example") PassengerRouteExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(PassengerRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(PassengerRoute record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table passenger_route
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(PassengerRoute record);
}