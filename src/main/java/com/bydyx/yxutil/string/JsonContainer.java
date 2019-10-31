package com.bydyx.yxutil.string;

import com.alibaba.fastjson.JSONObject;

/**
 * @author qiang.feng
 * @date 2019/10/30 11:24
 */
public class JsonContainer {
    JSONObject data = new JSONObject();

    public JsonContainer(String key, Object value) {
        data.put(key, value);
    }
    public void add(String key, Object value){
        data.put(key, value);
    }

    public JSONObject getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toJSONString();
    }
}
