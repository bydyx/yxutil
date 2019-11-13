package com.bydyx.yxutil.pay.exception;

/**
 * @author qiang.feng
 * @date 2019/11/13 15:27
 */
public class PayRTException extends RuntimeException{
    public PayRTException(String message) {
        super(message);
    }

    public PayRTException(Throwable cause) {
        super(cause);
    }
}
