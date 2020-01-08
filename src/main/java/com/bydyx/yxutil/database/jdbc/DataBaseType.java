package com.bydyx.yxutil.database.jdbc;

import lombok.Getter;

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
}