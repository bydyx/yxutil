package com.bydyx.asserts.handler;

import com.bydyx.exception.UtilConfigException;
import com.bydyx.string.PrintUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author q.f
 * @date 2020/8/4 10:23
 */
public class AssertExceptionRegister {
    private static Map<AssertType, Class<? extends RuntimeException>> exceptionMap = new HashMap<>();

    public static void register(AssertType assertType, Class<? extends RuntimeException> exceptionClass) {
        exceptionMap.put(assertType, exceptionClass);
    }

    public static Class<? extends RuntimeException> getException(AssertType assertType) {
        Class<? extends RuntimeException> clazz = exceptionMap.get(assertType);
        if (Objects.nonNull(clazz)) {
            return clazz;
        }
        throw new UtilConfigException(PrintUtil.print("未设置断言类型:{}的异常!", assertType));
    }
}