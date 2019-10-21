package com.bydyx.util;

/**
 * @author qiang.feng
 * @date 2019/10/21 11:09
 */
public enum IntegerEnum {
    INTEGER_MAX_LENGTH(10);
    int integer;

    IntegerEnum(int integer) {
        this.integer = integer;
    }

    public int value() {
        return integer;
    }
}