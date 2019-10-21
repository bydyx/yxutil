package com.bydyx.util.string;


import com.alibaba.fastjson.JSONObject;
import com.bydyx.util.reflex.ClassUtil;

import java.util.Map;

/**
 * @author bydyx
 * @date 2019/10/18 14:27
 */
public class ObjToStrUtil {
    public static String mapToXmlStr(Map map) {
        StringBuilder sb = new StringBuilder("<xml>");
        map.forEach((key, value) -> mapElementToXmlLabel(sb, key, value));
        return sb.append("</xml>").toString();
    }

    private static void mapElementToXmlLabel(StringBuilder sb, Object key, Object value) {
        sb.append("<").append(key).append(">");
        if (ClassUtil.isBaseType(value)) {
            sb.append("<![CDATA[").append(value).append("]]>");
        } else {
            JSONObject jsonObject = JsonUtil.objToJsonObj(value);
            jsonObject.forEach((s, o) -> mapElementToXmlLabel(sb, s, o));
        }
        sb.append("</").append(key).append(">");
    }
}