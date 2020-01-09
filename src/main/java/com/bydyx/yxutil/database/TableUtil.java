package com.bydyx.yxutil.database;

import com.bydyx.yxutil.database.entity.Column;
import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.string.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiang.feng
 * @date 2020/1/8 11:07
 */
public class TableUtil {

    public List<Column> getTableColumn(DataBaseConfig dataBaseConfig, String tableName) {
        String sql =
        "SELECT column_name columnname,udt_name dbtype " +
        "FROM information_schema.columns t " +
        "WHERE table_name = ?";
        List<Object> list = new ArrayList(2);
        list.add(tableName);
        if (!StringUtil.isBlank(dataBaseConfig.getSchema())) {
            sql += " AND table_schema = ?";
            list.add(dataBaseConfig.getSchema());
        }
        return JdbcUtil.doSelect(dataBaseConfig, sql, list)
                       .stream()
                       .map(jsonObject -> new Column(jsonObject,dataBaseConfig))
                       .collect(Collectors.toList());
    }
}