package com.bydyx.exception;

/**
 * @date 2020/8/12 16:01
 */
public class ExceptionUtil {
    public static UtilException createUtilException(String msg) {
        return new UtilException(msg);
    }
}
