package com.bydyx.yxutil.string.exception;

/**
 * @author qiang.feng
 * @date 2019/11/6 9:12
 */
public class JwtException extends Exception {
    public JwtException(String message) {
        super(message);
    }

    public JwtException(Throwable cause) {
        super(cause);
    }
}
