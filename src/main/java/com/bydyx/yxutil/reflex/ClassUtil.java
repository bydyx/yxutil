package com.bydyx.yxutil.reflex;

/**
 * @author bydyx
 * @date 2019/10/18 15:03
 */
public class ClassUtil {

    // 是否为基本数据类型|lang下的基本类型的封装类型|字符串
    public static boolean isBaseType(Object obj) {
        if (obj == null) {
            return true;
        }
        return isBaseType(obj.getClass());
    }

    public static boolean isBaseType(Class clazz) {
        String typeName = clazz.getTypeName();
        return isLangPackage(typeName) || is8BaseType(typeName);
    }

    private static String langPackageFlag = ".lang.";

    public static boolean isLangPackage(String name) {
        if (name.indexOf(langPackageFlag) != -1) {
            return true;
        }
        return false;
    }

    private static boolean is8BaseType(String name) {
        switch (name) {
            case "short":
            case "int":
            case "long":
            case "float":
            case "double":
            case "boolean":
            case "byte":
            case "char":
                return true;
            default:
                return false;
        }
    }
}
