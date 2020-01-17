package com.bydyx.yxutil.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.string.StringUtil;

/**
 * @author bydyx
 * @date 2019/10/18 14:48
 */
public class JsonUtil {

    public static String  toJSONString(Object object){
        return JSON.toJSONString(object);
    }

    public static String parseJsonStr(Object object) {
        return JSON.toJSONString(object);
    }

    public static JSONObject objToJsonObj(Object obj) {
        String str = JSON.toJSONString(obj);
        return JSON.parseObject(str);
    }

    public static <T> T strToObj(String str, Class<T> clazz) {
        JSONObject jsonObject = JSON.parseObject(str);
        if (StringUtil.isBlank(str)) {
            return null;
        }
        return JSON.toJavaObject(jsonObject, clazz);
    }
}
