package com.bydyx.yxutil.database.jdbc;

import lombok.Data;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:36
 */
@Data
public class DataBaseConfig {
    String driver;
    String dataBaseName;
    String userName;
    String passWord;
    String dataBaseUrl;
    String jdbcUrlPrefix;

    public String getUrl() {
        return dataBaseUrl + ":" + dataBaseUrl + "/" + dataBaseName + "?useUnicode=true&characterEncoding=utf-8";
    }
}