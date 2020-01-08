package com.bydyx.yxutil.database.entity;

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
}