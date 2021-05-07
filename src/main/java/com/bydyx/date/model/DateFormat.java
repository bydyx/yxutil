package com.bydyx.date.model;

public enum DateFormat {
    HyyyyMMdd_HHmmss("yyyy-MM-dd HH:mm:ss"),
    yyyyMMddHHmmss("yyyyMMddHHmmss");

    String format;

    DateFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }
}
