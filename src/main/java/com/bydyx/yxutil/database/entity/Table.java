package com.bydyx.yxutil.database.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author qiang.feng
 * @date 2020/1/8 16:15
 */
@Data
public class Table {
   String name;
   List<Column> columnList;

}