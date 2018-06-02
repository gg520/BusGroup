package java.com.wxbus.dao;

import com.wxbus.daomain.Bus;
import com.wxbus.daomain.BusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int countByExample(BusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int deleteByExample(BusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer busNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int insert(Bus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int insertSelective(Bus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    List<Bus> selectByExample(BusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    Bus selectByPrimaryKey(Integer busNum);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Bus record, @Param("example") BusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Bus record, @Param("example") BusExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Bus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bus
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Bus record);
}