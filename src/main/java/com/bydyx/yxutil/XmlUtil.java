package com.bydyx.yxutil;

/**
 * @author bydyx
 * @date 2019/10/31 8:46
 */
public class XmlUtil {

    public static String valueToXmlValue(String value) {
        return "<![CDATA[" + value + "]]>";
    }

    public static String keyToStartLabel(String key) {
        return "<" + key + ">";
    }

    public static String keyToEndLabel(String key) {
        return "</" + key + ">";
    }
}