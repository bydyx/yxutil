package com.bydyx.yxutil.database.entity;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.database.jdbc.DataBaseConfig;
import com.bydyx.yxutil.string.StringUtil;
import lombok.Data;

/**
 * @author qiang.feng
 * @date 2020/1/8 16:17
 */
@Data
public class Column {
    String name;
    String dbType;
    Class javaType;
    String entityName;

    public Column(JSONObject jsonObject, DataBaseConfig dataBaseConfig) {
        dbType = jsonObject.getString("dbtype");
        name = jsonObject.getString("columnname");
        entityName = StringUtil.clearUnderline(name);
        javaType = dataBaseConfig.dataTypeMap(dbType);
    }

}