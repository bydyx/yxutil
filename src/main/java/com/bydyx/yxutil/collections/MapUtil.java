package com.bydyx.yxutil.collections;

import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.json.JsonUtil;
import com.bydyx.yxutil.reflex.ClassUtil;

import java.util.Map;

/**
 * @author bydyx
 * @date 2019/10/18 14:25
 */
public class MapUtil {

    public static String toGetParam(Map<String, Object> params) {
        return "?" + params.keySet()
                           .stream()
                           .map(key -> key + "=" + params.get(key))
                           .reduce((c, p) -> c + "&" + p)
                           .orElse("");
    }

    public static String mapToXml(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder("<xml>");
        map.forEach((key, value) -> mapElementToXmlLabel(sb, key, value));
        return sb.append("</xml>").toString();
    }

    private static void mapElementToXmlLabel(StringBuilder sb, String key, Object value) {
        sb.append(XmlUtil.createStartLabel(key));
        if (ClassUtil.isBaseType(value)) {
            sb.append(XmlUtil.createXmlContent(value.toString()));
        } else {
            //如果是对象类型则转成json递归
            JsonUtil.objToJsonObj(value).forEach((s, o) -> mapElementToXmlLabel(sb, s, o));
        }
        sb.append(XmlUtil.createEndLabel(key));
    }

}