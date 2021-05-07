package com.bydyx.exception;

/**
 * @date 2020/8/11 10:28
 */
public class UtilException extends RuntimeException {
    public UtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public UtilException(Throwable cause) {
        super(cause);
    }

    public UtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UtilException(String message) {
        super(message);
    }
}