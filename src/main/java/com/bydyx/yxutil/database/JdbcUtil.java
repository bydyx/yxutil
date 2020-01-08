package com.bydyx.yxutil.database;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.database.jdbc.JdbcFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:34
 */
public class JdbcUtil {

    public static List<JSONObject> doSelect(DataBaseConfig dataBaseConfig, String sql, List<Object> params) {
        try (
            Connection connection = JdbcFactory.getConnection(dataBaseConfig);
            PreparedStatement ptmt = JdbcFactory.getPreparedStatement(connection, sql, params);
            ResultSet resultSet = ptmt.executeQuery();
        ) {
            return JdbcFactory.analysisSqlQueryResult(resultSet);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}