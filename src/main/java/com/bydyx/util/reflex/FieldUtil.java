package com.bydyx.util.reflex;

import com.bydyx.param.ParamUtil;

import java.lang.reflect.Field;

/**
 * @author bydyx
 * @date 2019/10/21 20:54
 */
public class FieldUtil {

    public static Field[] getAllField(Object target){
        ParamUtil.checkObjIsNull(target);
        return target.getClass().getDeclaredFields();
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
