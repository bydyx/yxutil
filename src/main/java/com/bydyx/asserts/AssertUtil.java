package com.bydyx.asserts;


import com.bydyx.asserts.handler.AssertFactory;
import com.bydyx.asserts.handler.AssertType;

import java.util.Objects;


/**
 * @author qiang.feng
 * @date 2020/5/12 10:19
 */
public class AssertUtil {

    public static <T> T notNull(T param) {
        if (Objects.isNull(param)) {
            AssertFactory.throwException(AssertType.NOT_NULL);
        }
        return param;
    }

    public static void notNull(Object... params) {
        for (Object param : params) {
            if (Objects.isNull(param)) {
                AssertFactory.throwException(AssertType.NOT_NULL);
            }
        }
    }

    public static boolean notNullBoolean(Object... params) {
        try {
            notNull(params);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}