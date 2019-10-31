package com.bydyx.yxutil.string;

import com.alibaba.fastjson.JSONArray;
import com.bydyx.yxutil.string.exception.TextNotJsonArrayException;

import java.util.List;

/**
 * @author bydyx
 * @date 2019/10/18 15:55
 */
public class JsonArrayUtil {

    public static <T> List<T> arrayStrToList(String arrayStr, Class<T> clazz) {
        JSONArray jsonArray = strToJsonArray(arrayStr);
        return jsonArrayToList(jsonArray, clazz);
    }

    public static <T> List<T> jsonArrayToList(JSONArray jsonArray, Class<T> clazz) {
        return jsonArray.toJavaList(clazz);
    }

    public static JSONArray strToJsonArray(String arrayStr) {
        try {
            return JSONArray.parseArray(arrayStr);
        } catch (Exception e) {
            throw new TextNotJsonArrayException();
        }
    }

    public static JSONArray listToArrayStr(List<Object> list) {
        return JSONArray.parseArray(JSONArray.toJSONString(list));
    }
}
