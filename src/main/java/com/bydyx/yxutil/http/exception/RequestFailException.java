package com.bydyx.yxutil.http.exception;


/**
 * 请求第三方接口失败的异常
 *
 * @author FigureFragrance
 * @date 2019/7/5 16:58
 */
public class RequestFailException extends RuntimeException {

    public RequestFailException() {
    }
    public RequestFailException(String msg) {
        super(msg);
    }
    public RequestFailException(Exception exception) {
        super(exception);
    }
}
