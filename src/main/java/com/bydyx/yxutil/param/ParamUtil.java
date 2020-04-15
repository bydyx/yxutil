package com.bydyx.yxutil.param;

import com.bydyx.yxutil.annotation.AnnotationUtil;
import com.bydyx.yxutil.param.annotation.CanNull;
import com.bydyx.yxutil.param.annotation.NotNull;
import com.bydyx.yxutil.param.exception.FieldNullException;
import com.bydyx.yxutil.param.exception.ParamWrongException;
import com.bydyx.yxutil.reflex.FieldUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author bydyx
 * @date 2019/10/21 9:38
 */
public class ParamUtil {

    /**
     * 检查时字段默认加@CanNull的检查
     *
     * @param target
     */
    public static void objFieldsCanNull(Object target) {
        Field[] fields = FieldUtil.getAllField(target);
        for (final Field field : fields) {
            ParamUtil.checkFieldCanNull(target, field);
        }
    }

    //检查时字段默认加@NotNull的检查
    public static void objFieldsNotNull(Object target) {
        Field[] fields = FieldUtil.getAllField(target);
        for (final Field field : fields) {
            ParamUtil.checkFieldNotNull(target, field);
        }
    }

    private static void checkFieldCanNull(Object target, Field field) {
        if (AnnotationUtil.fieldHasAnnotation(field, NotNull.class)) {
            ParamUtil.checkFieldValueNotNull(field, target);
        }
    }

    private static void checkFieldNotNull(Object target, Field field) {
        if (!AnnotationUtil.fieldHasAnnotation(field, CanNull.class)) {
            ParamUtil.checkFieldValueNotNull(field, target);
        }
    }

    private static void checkFieldValueNotNull(Field field, Object target) {
        Object value = FieldUtil.getValue(field, target);
        if (value == null) {
            throw new FieldNullException(field.getName());
        }
    }

    public static void checkListItemNull(List list) {
        list.stream().forEach(ParamUtil::checkObjNotNull);
    }

    public static void checkObjIsNull(Object... objs) {
        for (final Object obj : objs) {
            checkObjNotNull(obj);
        }
    }

    public static void checkObjNotNull(Object obj) {
        if (obj == null) {
            throw new ParamWrongException();
        }
    }

    public static Object dynamicParameterFirst(Object... objects) {
        if (objects.length == 0) {
            Object object = objects[0];
            return object;
        }
        throw new ParamWrongException("动态参数列表为空!");
    }
}