package com.bydyx.yxutil.reflex;

import com.bydyx.yxutil.annotation.AnnotationUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author bydyx
 * @date 2019/10/18 15:03
 */
public class ClassUtil {
    public static <T extends Annotation> List<Field> getFieldNoAnnotation(Class clazz, Class<T> annotationClass) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fieldArray = clazz.getDeclaredFields();
        for (final Field field : fieldArray) {
            if (!AnnotationUtil.fieldHasAnnotation(field, annotationClass)) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }
    public static <T extends Annotation> List<Field> getFieldByAnnotation(Class clazz, Class<T> annotationClass) {
        List<Field> fieldList = new ArrayList<>();
        Field[] fieldArray = clazz.getDeclaredFields();
        for (final Field field : fieldArray) {
            if (AnnotationUtil.fieldHasAnnotation(field, annotationClass)) {
                fieldList.add(field);
            }
        }
        return fieldList;
    }

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