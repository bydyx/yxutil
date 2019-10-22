package com.bydyx.yxutil.http.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求所用参数的dto
 *
 * @author FigureFragrance
 * @date 2019/7/1 10:43
 */
@Data
public class RequestParams {

    String url;
    Method method;
    Map<String, Object> data = new HashMap<>();

    boolean isEncode = false;
    boolean isXml = false;
    String xmlStr;

    public void addData(String key, String value) {
        data.put(key, value);
    }

    RequestParams() {
    }

    RequestParams(String url, Method method) {
        this.url = url;
        this.method = method;
    }

    public static RequestParams getRequestParams(String url, Method method) {
        return new RequestParams(url, method);
    }

    public static RequestParams getRequestParams(String url, Method method, Map<String, Object> data) {
        RequestParams requestParams = getRequestParams(url, method);
        requestParams.setData(data);
        return requestParams;
    }

    public static RequestParams getXmlRequestParams(String url, Method method, String xmlStr) {
        RequestParams requestParams = getXmlRequestParams(url, method);
        requestParams.setXmlStr(xmlStr);
        return requestParams;
    }

    public static RequestParams getXmlRequestParams(String url, Method method) {
        RequestParams requestParams = new RequestParams(url, method);
        requestParams.setXml(true);
        return requestParams;
    }

    /**
     * 返回wxXml格式的RequestParams
     */
    public static RequestParams getWxXmlRequestParams(String url, String xmlStr) {
        return getXmlRequestParams(url, Method.POST, xmlStr);
    }

    /**
     * 返回SimApi格式的RequestParams
     */
    public static RequestParams getSimApiRequestParams(String url, Map<String, Object> data) {
        RequestParams requestParams = getSimApiRequestParams(url);
        requestParams.setData(data);
        return requestParams;
    }

    /**
     * 返回SimApi格式的RequestParams
     */
    public static RequestParams getSimApiRequestParams(String url) {
        RequestParams requestParams = new RequestParams(url, Method.POST);
        requestParams.setEncode(true);
        return requestParams;
    }
}
