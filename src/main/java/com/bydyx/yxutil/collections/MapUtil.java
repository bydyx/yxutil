package com.bydyx.yxutil.collections;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.XmlUtil;
import com.bydyx.yxutil.reflex.ClassUtil;
import com.bydyx.yxutil.string.JsonUtil;

import java.util.Map;

/**
 * @author bydyx
 * @date 2019/10/18 14:25
 */
public class MapUtil {

    public static String mapToXmlStr(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("<xml>");
        map.forEach((key, value) -> mapElementToXmlLabel(sb, key, value));
        return sb.append("</xml>").toString();
    }

    public static void mapElementToXmlLabel(StringBuilder sb, String key, Object value) {
        sb.append(XmlUtil.keyToStartLabel(key));
        if (ClassUtil.isBaseType(value)) {
            String valueStr = value.toString();
            sb.append(XmlUtil.valueToXmlValue(valueStr));
        } else {
            JSONObject jsonObject = JsonUtil.objToJsonObj(value);
            jsonObject.forEach((s, o) -> mapElementToXmlLabel(sb, s, o));
        }
        sb.append(XmlUtil.keyToEndLabel(key));
    }
}