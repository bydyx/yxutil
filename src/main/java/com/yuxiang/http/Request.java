package com.yuxiang.http;

import com.yuxiang.http.exception.RequestFailException;

import java.util.Map;


public interface Request {

    void setUrl(String url);

    void setParams(Map<String, Object> params);

    void addParam(String key, Object param);

    void setEncode(boolean b);

    Map<String,Object> getParams();

    String execute() throws RequestFailException;
}
