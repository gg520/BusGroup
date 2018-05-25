package com.wxbus.dao;

import com.wxbus.daomain.Driver;
import com.wxbus.daomain.DriverExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriverMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int countByExample(DriverExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int deleteByExample(DriverExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String driverId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int insert(Driver record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int insertSelective(Driver record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    List<Driver> selectByExample(DriverExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    Driver selectByPrimaryKey(String driverId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Driver record, @Param("example") DriverExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Driver record, @Param("example") DriverExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Driver record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table driver
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Driver record);
}