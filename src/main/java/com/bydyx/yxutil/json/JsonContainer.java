package com.bydyx.yxutil.json;

import com.alibaba.fastjson.JSONObject;

/**
 * @author bydyx
 * @date 2019/10/30 11:24
 */
public class JsonContainer {
    JSONObject data = new JSONObject();

    public static JSONObject createSingleContainer(String key, Object value){
        JsonContainer jsonContainer = new JsonContainer(key, value);
        return jsonContainer.data;
    }

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