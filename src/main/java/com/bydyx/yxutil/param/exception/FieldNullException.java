package com.bydyx.yxutil.param.exception;

/**
 * @author qiang.feng
 * @date 2019/10/21 9:39
 */
public class FieldNullException extends RuntimeException {
    public FieldNullException(String fieldName) {
        super("参数为null:" + fieldName);
    }
}
