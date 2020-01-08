package com.bydyx.yxutil.database;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.database.entity.Table;
import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.database.jdbc.DataBaseType;
import com.bydyx.yxutil.string.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:07
 */
public class TableUtil {
    public static void main(String[] args) {
        DataBaseConfig dataBaseConfig = DataBaseConfig.pgSqlConfig("sdm","postgres","hnhsoft2828","192.168.100.181");
        dataBaseConfig.setSchema("alc_teln");
        new TableUtil().getTable(dataBaseConfig, "");
    }

    public Table getTable(DataBaseConfig dataBaseConfig, String tableName) {
        String sql =
            "SELECT table_schema,table_name,column_name,udt_name\n" +
            "FROM information_schema.columns t" +
            "WHERE table_name = ?";
        if (!StringUtil.isBlank(dataBaseConfig.getSchema())) {
            sql += " AND table_schema = ?";
        }
        List<Object> list = new ArrayList(1);
        list.add(tableName);
        List<JSONObject> result = JdbcUtil.doSelect(dataBaseConfig, sql, list);

        result.forEach(System.out::println);
        return null;
    }
}