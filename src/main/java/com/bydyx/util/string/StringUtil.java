package com.bydyx.util.string;

import com.bydyx.util.IntegerEnum;
import com.bydyx.util.string.exception.StringNotIntegerException;

import java.util.UUID;

/**
 * @author qiang.feng
 * @date 2019/10/18 16:45
 */
public class StringUtil {
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static Integer toInteger(String str) {
        try {
            if (!StringUtil.isInteger(str)) {
                throw new StringNotIntegerException();
            }
            return Integer.parseInt(str);
        } catch (Exception e) {
            throw new StringNotIntegerException(e);
        }
    }

    public static boolean isInteger(String str) {
        if (!RegularUtil.isInteger(str)) {
            return false;
        }
        if (str.length() > IntegerEnum.INTEGER_MAX_LENGTH.value()) {
            return false;
        }
        return true;
    }

    public static String randomStr32() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static String objsToStr(Object... objs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (final Object obj : objs) {
            stringBuilder.append(obj);
        }
        return stringBuilder.toString();
    }
}
