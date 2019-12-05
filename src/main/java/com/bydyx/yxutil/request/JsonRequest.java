package com.bydyx.yxutil.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.collections.MapUtil;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qiang.feng
 * @date 2019/11/27 15:13
 */
@Data
public class JsonRequest implements Request {
    String url;
    Method method;
    Map<String, Object> params = new HashMap<>();
    ResultPro resultPro = (str) -> JSONObject.parseObject(str);

    @Override
    public Method getMethod() {
        if (method == null) {
            throw new RuntimeException("未设置请求方法!");
        }
        return method;
    }

    public JsonRequest() {
    }

    public JsonRequest(String url, Method method) {
        this.url = url;
        this.method = method;
    }

    public static JsonRequest get(String url) {
        return new JsonRequest(url, Method.GET);
    }

    public static JsonRequest post(String url) {
        return new JsonRequest(url, Method.POST);
    }

    @Override
    public String getParam() {
        if (getMethod().equals(Method.POST)) {
            return JSON.toJSONString(params);
        }
        return MapUtil.toGetParam(params);
    }

    public Object getParam(String key) {
        return params.get(key);
    }

    @Override
    public void addParam(String key, Object value) {
        params.put(key, value);
    }

    @Override
    public JSONObject getResultObj(String resultStr) {
        return (JSONObject) resultPro.getResultObj(resultStr);
    }
}
