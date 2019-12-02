package com.bydyx.yxutil.request;


public interface Request {

    String getUrl();

    Method getMethod();

    String getParam();

    void addParam(String key, Object value);

    Object getResultObj(String resultStr);
}
