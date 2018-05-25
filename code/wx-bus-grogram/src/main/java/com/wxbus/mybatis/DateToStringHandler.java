package com.wxbus.mybatis;

import com.wxbus.util.TimeUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by g1154 on 2018/4/25.
 */
/*
    映射文件的配置
    <columnOverride column="regTime" javaType="java.util.Date" typeHandler="com.wxbus.mybatis.DateToStringHandler"/>
    或
    <resultMap id="userResultMap" type="对应要映射的类">
        <result typeHandler="com.wxbus.mybatis.DateToStringHandler" column="regTime" javaType="java.util.Date"
                jdbcType="VARCHAR"
                property="regTime"/>
    </resultMap>
 */
@MappedJdbcTypes({JdbcType.VARCHAR})
@MappedTypes({Date.class})
public class DateToStringHandler extends BaseTypeHandler<Date> {

    /**
     *
     * @param preparedStatement 仓库
     * @param i 识别标识
     * @param date 转换的传入类型
     * @param jdbcType  与preparedStatement调用的方法set有关，设置成为什么就是什么，对应mapping的映射文件配置
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, TimeUtil.getTimeByType(date,null));
    }

    /**
     *
     * @param resultSet 获取的数据库的值
     * @param s 标识量
     * @return 返回与设置相同
     * @throws SQLException
     */
    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String date=resultSet.getString(s);
        return TimeUtil.getTimeByString(date,null);

    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return TimeUtil.getTimeByString(resultSet.getString(i),null);
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return TimeUtil.getTimeByString(callableStatement.getString(i),null);
    }


}
