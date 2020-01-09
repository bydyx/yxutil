package com.bydyx.yxutil.database.jdbc;

import com.bydyx.yxutil.string.StringUtil;
import lombok.Getter;

import java.util.Date;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:35
 */
@Getter
public enum DataBaseType {
    PGSQL(5432, "org.postgresql.Driver", "jdbc:postgresql"),
    MYSQL(3306, "com.mysql.cj.jdbc.Driver", "jdbc:mysql:jdbc:mysql"),
    ;
    Integer port;
    String driverUrl;
    String jdbcUrlPrefix;

    DataBaseType(Integer port, String driverUrl, String jdbcUrlPrefix) {
        this.port = port;
        this.driverUrl = driverUrl;
        this.jdbcUrlPrefix = jdbcUrlPrefix;
    }

    public Class dataTypeMap(String dbType) {
        switch (this) {
            case MYSQL:
                return null;
            case PGSQL:
                return getPgSqlDataTypeMap(dbType);
            default:
                return null;
        }
    }

    public Class getPgSqlDataTypeMap(String dbType) {
        if (StringUtil.indexOfOr(dbType, "int")) {
            return Integer.class;
        }
        if (StringUtil.indexOfOr(dbType, "float", "numeric")) {
            return Double.class;
        }
        if (StringUtil.indexOfOr(dbType, "varchar", "text")) {
            return String.class;
        }
        if (StringUtil.indexOfOr(dbType, "timestamp", "date")) {
            return Date.class;
        }
        throw new RuntimeException("出现未捕获的数据类型:" + dbType);
    }
}