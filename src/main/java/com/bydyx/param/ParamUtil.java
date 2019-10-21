package com.bydyx.param;

import com.bydyx.annotation.AnnotationUtil;
import com.bydyx.param.annotation.CanNull;
import com.bydyx.param.exception.FieldNullException;
import com.bydyx.util.reflex.FieldUtil;

import java.lang.reflect.Field;

/**
 * @author bydyx
 * @date 2019/10/21 9:38
 */
public class ParamUtil {

    public static void objFieldsIsNull(Object target) {
        Field[] fields = FieldUtil.getAllField(target);
        for (final Field field : fields) {
            field.setAccessible(true);
            ParamUtil.checkFieldIsNull(target, field);
        }
    }

    private static void checkFieldIsNull(Object target, Field field) {
        if (!AnnotationUtil.fieldHasAnnotation(field, CanNull.class)) {
            Object value = FieldUtil.getValue(field, target);
            if (value == null) {
                throw new FieldNullException(field.getName());
            }
        }
    }

    public static void checkObjIsNull(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
    }
}