package com.incaier.integration.platform.config;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.incaier.integration.platform.util.AesUtil;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据加密/解密
 * @param <T>
 */
@Component
public class CustomTypeHandler<T> extends BaseTypeHandler<T> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, AesUtil.encrypt((String)parameter));
    }
    @Override
    public T getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        //有一些可能是空字符
        return StringUtils.isBlank(columnValue) ? (T)columnValue : (T)AesUtil.decryptDb(columnValue);
    }

    @Override
    public T getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return StringUtils.isBlank(columnValue) ? (T)columnValue : (T)AesUtil.decryptDb(columnValue);
    }

    @Override
    public T getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return StringUtils.isBlank(columnValue) ? (T)columnValue : (T)AesUtil.decryptDb(columnValue);
    }

}
