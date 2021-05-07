package com.bydyx.asserts.handler;

import com.bydyx.reflex.ReflexUtil;

/**
 * @author q.f
 * @date 2020/8/4 10:23
 */
public class AssertFactory {


    public static void throwException(AssertType assertType, String msg) throws RuntimeException {
        throw AssertFactory.createException(assertType, msg);
    }

    public static void throwException(AssertType assertType) throws RuntimeException {
        AssertFactory.throwException(assertType, "");
    }

    private static RuntimeException createException(AssertType assertType, String msg) {
        Class<? extends RuntimeException> clazz = AssertExceptionRegister.getException(assertType);
        RuntimeException exception = ReflexUtil.instance(clazz);
        ReflexUtil.setFieldVal(exception, "detailMessage", msg);
        return exception;
    }
}