package com.bydyx.yxutil.string;

import com.alibaba.fastjson.JSON;
import com.bydyx.yxutil.IntegerEnum;
import com.bydyx.yxutil.function.Excuter;
import com.bydyx.yxutil.string.exception.StringNotIntegerException;

import java.util.UUID;

/**
 * @author qiang.feng
 * @date 2019/10/18 16:45
 */
public class StringUtil {

    public static String parseStr(Object object){
        return JSON.toJSONString(object);
    }
    public static void isBlank(String str, Excuter excuter) {
        if (isBlank(str)) {
            excuter.excute();
        }
    }
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static Integer toInteger(String str) {
        try {
            if (StringUtil.isInteger(str)) {
                return Integer.parseInt(str);
            }
            throw new StringNotIntegerException();
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
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").toUpperCase();
    }

    public static String objsToStr(Object... objs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (final Object obj : objs) {
            stringBuilder.append(obj);
        }
        return stringBuilder.toString();
    }
}
