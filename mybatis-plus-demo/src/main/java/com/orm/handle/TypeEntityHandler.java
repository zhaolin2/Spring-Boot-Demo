package com.orm.handle;

import com.orm.entity.TypeEntity;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * 将单列的值转换为一个对象
 */
@MappedTypes(TypeEntity.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TypeEntityHandler extends BaseTypeHandler<TypeEntity> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, TypeEntity parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public TypeEntity getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public TypeEntity getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public TypeEntity getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
