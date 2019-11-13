package com.bydyx.yxutil.file;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bydyx.yxutil.reflex.ClassUtil;
import org.dom4j.*;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author bydyx
 * @date 2019/10/31 8:46
 */
public class XmlUtil {

    public static JSONObject xmlStr2Json(String xmlStr) {
        try {
            xmlStr = xmlStr.replaceAll("\\\n", "");
            Element rootElement = DocumentHelper.parseText(xmlStr).getRootElement();
            return elementToJsonObject(rootElement);
        } catch (DocumentException e) {
            throw new RuntimeException("strToDocument异常:" + xmlStr);
        }
    }

    /**
     * 对象属性转xml标签
     * @param targer
     * @param field
     * @return
     */
    public static String toXmlLabel(Object targer, Field field) {
        String startLabel = XmlUtil.createStartLabel(field.getName());
        String content = ClassUtil.getFieldValue(field, targer).toString();

        String xmlContent = XmlUtil.createXmlContent(content);
        String endLabel = XmlUtil.createEndLabel(field.getName());
        return startLabel + xmlContent + endLabel;
    }

    /**
     * org.dom4j.Element转fastjson.JSONObject
     */
    public static JSONObject elementToJsonObject(Element node) {
        JSONObject result = new JSONObject();
        // 遍历当前节点的所有属性
        List<Attribute> listAttr = node.attributes();
        listAttr.forEach(attr -> result.put(attr.getName(), attr.getValue()));
        // 元素
        List<Element> listElement = node.elements();
        listElement.forEach(e -> {
            if (e.attributes().isEmpty() && e.elements().isEmpty()) {
                result.put(e.getName(), e.getTextTrim());
            } else {
                if (!result.containsKey(e.getName())) {
                    result.put(e.getName(), new JSONArray());
                }
                JSONObject jsonObject = elementToJsonObject(e);
                ((JSONArray) e).add(jsonObject);
            }
        });
        return result;
    }

    public static String createXmlContent(String content) {
        return "<![CDATA[" + content + "]]>";
    }

    public static String createStartLabel(String labelName) {
        return "<" + labelName + ">";
    }

    public static String createEndLabel(String labelName) {
        return "</" + labelName + ">";
    }
}