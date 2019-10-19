package com.yuxiang.util.time.entity;

/**
 * @author qiang.feng
 * @date 2019/10/18 16:54
 */
public enum  TimeFormat {
    yyyyMMddHHmmssSS("yyyyMMddHHmmssSS"),
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    HyyyyMMddHHmmss("yyyy-MM-dd HH:mm:ss"),
    XyyyyMMddHHmmss("yyyy/MM/dd HH:mm:ss"),
    KyyyyMMddHHmmss("yyyy MM dd HH:mm:ss");

    String format;

    TimeFormat(String format) {
        this.format = format;
    }
    public String getFormat(){
        return format;
    }
}
