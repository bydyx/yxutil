package com.bydyx.yxutil.param.exception;

/**
 * @author qiang.feng
 * @date 2019/11/5 16:52
 */
public class ParamWrongException extends RuntimeException {
    public ParamWrongException() {
    }

    public ParamWrongException(Object o) {
        super("参数错误:" + o);
    }
    public ParamWrongException(String message) {
        super("参数错误:" +message);
    }

    public ParamWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamWrongException(Throwable cause) {
        super(cause);
    }

    public ParamWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
