package com.bydyx.yxutil.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author bydyx
 * @date 2019/10/18 14:48
 */
public class JsonUtil {

    public static String parseJsonStr(Object object){
        return JSON.toJSONString(object);
    }

    public static JSONObject objToJsonObj(Object obj) {
        String str = JSON.toJSONString(obj);
        return JSON.parseObject(str);
    }

    public static <T> T strToObj(String str, Class<T> clazz) {
        JSONObject jsonObject = JSON.parseObject(str);
        return JSON.toJavaObject(jsonObject, clazz);
    }
}
