package com.bydyx.yxutil.database.jdbc;

import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/8 15:50
 */
public class JdbcFactory {

    public static Connection getConnection(DataBaseConfig config) throws SQLException, ClassNotFoundException {
        Class.forName(config.getDriver());
        return DriverManager.getConnection(config.getUrl(), config.getUserName(), config.getPassWord());
    }


    public static List<JSONObject> analysisSqlQueryResult(ResultSet resultSet) throws SQLException {
        List<JSONObject> list = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            JSONObject rowData =  getRowMap(resultSet,metaData);
            list.add(rowData);
        }
        return list;
    }

    private static JSONObject getRowMap(ResultSet resultSet,ResultSetMetaData metaData) throws SQLException {
        int columnCount = metaData.getColumnCount();
        JSONObject rowData = new JSONObject();
        for (int i = 1; i <= columnCount; i++) {
            rowData.put(metaData.getColumnName(i), resultSet.getObject(i));
        }
        return rowData;
    }

    public static PreparedStatement getPreparedStatement(Connection connection, String sql, List<Object> params) throws SQLException {
        PreparedStatement ptmt = connection.prepareStatement(sql);
        setSqlParams(ptmt, params);
        return ptmt;
    }

    private static void setSqlParams(PreparedStatement ptmt, List<Object> params) throws SQLException {
        for (int i = 1, length = params.size(); i <= length; i++) {
            setSqlParam(ptmt, i, params.get(i - 1));
        }
    }

    private static void setSqlParam(PreparedStatement ptmt, int index, Object param) throws SQLException {
        ptmt.setObject(index, param);
//        String paramClassType = param.getClass()
//                                     .getSimpleName();
//        switch (paramClassType) {
//            case "String":
//                ptmt.setString(index, (String) param);
//                break;
//            case "Integer":
//                ptmt.setInt(index, (Integer) param);
//                break;
//            case "Boolean":
//                ptmt.setBoolean(index, (Boolean) param);
//                break;
//        }
    }
}
