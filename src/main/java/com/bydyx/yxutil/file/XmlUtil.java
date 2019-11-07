package com.bydyx.yxutil.file;

import com.bydyx.yxutil.reflex.ClassUtil;
import com.bydyx.yxutil.json.JsonUtil;

import java.util.Map;

/**
 * @author bydyx
 * @date 2019/10/31 8:46
 */
public class XmlUtil {

    public static String parseXml(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("<xml>");
        map.forEach((key, value) -> mapElementToXmlLabel(sb, key, value));
        return sb.append("</xml>").toString();
    }

    private static void mapElementToXmlLabel(StringBuilder sb, String key, Object value) {
        sb.append(XmlUtil.keyToStartLabel(key));
        if (ClassUtil.isBaseType(value)) {
            sb.append(XmlUtil.valueToXmlValue(value.toString()));
        } else {
            JsonUtil.objToJsonObj(value).forEach((s, o) -> mapElementToXmlLabel(sb, s, o));
        }
        sb.append(XmlUtil.keyToEndLabel(key));
    }

    private static String valueToXmlValue(String value) {
        return "<![CDATA[" + value + "]]>";
    }

    private static String keyToStartLabel(String key) {
        return "<" + key + ">";
    }

    private static String keyToEndLabel(String key) {
        return "</" + key + ">";
    }
}