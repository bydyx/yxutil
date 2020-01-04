package com.bydyx.yxutil.math;

import com.bydyx.yxutil.string.RegexUtil;

/**
 * @author qiang.feng
 * @date 2019/12/26 16:30
 */
public class DoubleUtil {

    public static Double parseDouble(String doubleStr) {
        boolean number = RegexUtil.isNumber(doubleStr);
        if (number) {
            return Double.parseDouble(doubleStr);
        }
        throw new IllegalArgumentException("非法参数:" + doubleStr);
    }

}