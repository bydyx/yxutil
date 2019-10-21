package com.bydyx.util.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author bydyx
 * @date 2019/10/18 14:48
 */
public class JsonUtil {
    public static JSONObject objToJsonObj(Object obj) {
        String str = JSONObject.toJSONString(obj);
        return JSON.parseObject(str);
    }

}
