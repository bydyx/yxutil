package com.bydyx.yxutil.time.entity;

import java.util.Calendar;

/**
 * @author qiang.feng
 * @date 2019/11/5 17:13
 */
public enum TimeUnit {
    SECOND(Calendar.SECOND),
    MINUTE(Calendar.MINUTE),
    HOUR(Calendar.HOUR_OF_DAY),
    DATE(Calendar.DATE),
    MONTH(Calendar.MONTH),
    YEAR(Calendar.YEAR),
    ;
    TimeUnit(Integer value) {
        this.value = value;
    }
    Integer value;

    public Integer getValue() {
        return value;
    }
}