package com.bydyx.yxutil.reflex;

import com.bydyx.yxutil.param.ParamUtil;

import java.lang.reflect.Field;

/**
 * @author bydyx
 * @date 2019/10/21 20:54
 */
public class FieldUtil {

    public static Field[] getAllField(Object target){
        ParamUtil.checkObjIsNull(target);
        Class<?> clazz = target.getClass();
        return clazz.getDeclaredFields();
    }

    public static Object getValue(Field field, Object target) {
        try {
            field.setAccessible(true);
            return field.get(target);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
