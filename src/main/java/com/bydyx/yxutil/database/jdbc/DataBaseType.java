package com.bydyx.yxutil.database.jdbc;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:35
 */
public enum DataBaseType {
    MYSQL,
    PGSQL,
    ;

    public DataBaseConfig getConfig(String dataBaseUrl, String dataBaseName, String userName, String passWord) {
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        dataBaseConfig.setDataBaseUrl(dataBaseUrl);
        dataBaseConfig.setDataBaseName(dataBaseName);
        dataBaseConfig.setUserName(userName);
        dataBaseConfig.setPassWord(passWord);
        switch (this) {
            case MYSQL:
                return getMySqlConfig(dataBaseConfig);
            case PGSQL:
                return getPgSqlConfig(dataBaseConfig);
            default:
                throw new RuntimeException("不可能");
        }
    }

    private DataBaseConfig getPgSqlConfig(DataBaseConfig dataBaseConfig) {
        dataBaseConfig.setDriver("org.postgresql.Driver");
        dataBaseConfig.setJdbcUrlPrefix("jdbc:postgresql");
        return dataBaseConfig;
    }

    private DataBaseConfig getMySqlConfig(DataBaseConfig dataBaseConfig) {
        throw new RuntimeException("暂未实现");
    }
}