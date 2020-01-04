package com.bydyx.yxutil.collections;

import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.json.JsonUtil;
import com.bydyx.yxutil.reflex.ClassUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bydyx
 * @date 2019/10/18 14:25
 */
public class MapUtil {

    public static String toGetParam(Map<String, Object> params) {
        return  params.keySet()
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

    /**
     * 对象转map
     *
     * @date 2019/7/25 10:42
     * @author FigureFragrance
     */
    public static Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (null == obj) {
            return map;
        }
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (ClassUtil.isBaseType(field.getType())) {
                    map.put(field.getName(), field.get(obj));
                } else {
                    Object fieldValue = field.get(obj);
                    if (fieldValue != null && !fieldValue.equals(obj)) {
                        Map<String, Object> cMap = obj2Map(fieldValue);
                        map.put(field.getName(), cMap);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("obj2Map");
        }
        return map;
    }
}