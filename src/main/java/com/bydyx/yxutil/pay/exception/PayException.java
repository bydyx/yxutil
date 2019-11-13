package com.bydyx.yxutil.pay.exception;

/**
 * @author qiang.feng
 * @date 2019/11/13 15:27
 */
public class PayException extends Exception{
    public PayException(String message) {
        super(message);
    }

    public PayException(Throwable cause) {
        super(cause);
    }
}
