package com.yuxiang.util.string;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @author qiang.feng
 * @date 2019/10/18 16:45
 */
public class StringUtil {
    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否为int类型
     */
    private static Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

    public static boolean isInteger(String str) {
        return pattern.matcher(str).matches();
    }

    /**
     * 生成随机字符串
     */
    public static String randomStr32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
