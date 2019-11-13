package com.bydyx.yxutil.reflex;

import com.alibaba.fastjson.annotation.JSONField;
import com.bydyx.yxutil.annotation.AnnotationUtil;
import com.bydyx.yxutil.annotation.NoSerialize;
import com.bydyx.yxutil.reflex.exception.ReflexRTException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bydyx
 * @date 2019/10/18 15:03
 */
public class ClassUtil {

    public static void objFieldToRightObj(Object leftObj, Object rightObj) {
        Class<?> rightClass = rightObj.getClass();
        List<Field> rightFieldList = getAllField(rightClass);
        rightFieldList.forEach(field -> {
            try {
                Field leftField = getFieldByName(leftObj, field.getName());
                field.set(rightObj, leftField.get(leftObj));
            } catch (Exception e) {
            }
        });
    }

    public static Object getFieldValue(Field field, Object target) {
        try {
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new ReflexRTException(e);
        }
    }

    // 不区分大小写
    public static Field getFieldByName(Object target, String fieldName) {
        List<Field> fieldList = getAllField(target.getClass());
        final String fieldNameUp = fieldName.toUpperCase();
        return fieldList.stream()
                        .filter(field -> field.getName().toUpperCase().equals(fieldNameUp))
                        .findAny()
                        .orElseThrow(() -> new RuntimeException(target.getClass().getName() + "不含有字段:" + fieldName));
    }

    public static List<Field> getAllField(Class clazz) {
        Field[] fieldArray = clazz.getDeclaredFields();
        List<Field> fields = Arrays.asList(fieldArray);
        fields.forEach(field -> field.setAccessible(true));
        return fields;
    }

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

    //如果包名是java.lang.**则返回true
    public static boolean isLangPackage(String name) {
        return name.indexOf("java.lang.") == 0;
    }
}