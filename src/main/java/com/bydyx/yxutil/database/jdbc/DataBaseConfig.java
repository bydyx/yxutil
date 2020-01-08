package com.bydyx.yxutil.database.jdbc;

import lombok.Data;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:36
 */
@Data
public class DataBaseConfig {
    String dataBaseName;
    String userName;
    String passWord;
    String dataBaseUrl;
    String schema;
    Integer port;
    DataBaseType dataBaseType;

    public DataBaseConfig mySqlConfig(String dataBaseName, String userName, String passWord, String dataBaseUrl) {
        return new DataBaseConfig(DataBaseType.MYSQL, dataBaseName, userName, passWord, dataBaseUrl);
    }
    public static DataBaseConfig pgSqlConfig(String dataBaseName, String userName, String passWord, String dataBaseUrl){
        return new DataBaseConfig(DataBaseType.PGSQL, dataBaseName, userName, passWord, dataBaseUrl);
    }

    private DataBaseConfig(DataBaseType dataBaseType, String dataBaseName, String userName, String passWord, String dataBaseUrl) {
        this.dataBaseName = dataBaseName;
        this.userName = userName;
        this.passWord = passWord;
        this.dataBaseUrl = dataBaseUrl;
        this.dataBaseType = dataBaseType;
    }

    public String getUrl() {
        return dataBaseType.jdbcUrlPrefix + "://" + dataBaseUrl + ":" + port + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf-8";
    }

    public String getDriver() {
        return dataBaseType.getDriverUrl();
    }
}