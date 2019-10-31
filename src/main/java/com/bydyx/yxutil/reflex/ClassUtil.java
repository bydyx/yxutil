package com.bydyx.yxutil.reflex;

/**
 * @author bydyx
 * @date 2019/10/18 15:03
 */
public class ClassUtil {

    // 是否为基本数据类型 | lang下类型
    public static boolean isBaseType(Object obj) {
        if (obj == null) {
            return true;
        }
        return isBaseType(obj.getClass());
    }

    public static boolean isBaseType(Class clazz) {
        String typeName = clazz.getTypeName();
        return isLangPackage(typeName) || clazz.isPrimitive();
    }

    public static boolean isLangPackage(String name) {
        return name.indexOf("java.lang.") != 0;
    }
}