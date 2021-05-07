package com.bydyx.exception;

/**
 * @date 2020/8/10 15:29
 */
public class UtilParamException extends UtilException {

    public UtilParamException() {
        super("参数错误!");
    }

    public UtilParamException(Throwable cause) {
        super(cause);
    }

    public UtilParamException(String message) {
        super(message);
    }

}