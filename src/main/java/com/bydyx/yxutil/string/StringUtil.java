package com.bydyx.yxutil.string;

import com.bydyx.yxutil.Dictionaries;
import com.bydyx.yxutil.function.Excuter;
import com.bydyx.yxutil.math.IntegerUtil;

import java.util.UUID;

/**
 * @author bydyx
 * @date 2019/10/18 16:45
 */
public class StringUtil {

    private final static char[] baseNumLetter = {
    '1', '2', '3', '4', '5', '6', '7', '8', '9',
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
    'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
    'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    public static String createVerifyCode() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int random = IntegerUtil.random(0, baseNumLetter.length);
            sb.append(baseNumLetter[random]);
        }
        return sb.toString();
    }

    public static void isBlank(String str, Excuter excuter) {
        if (isBlank(str)) {
            excuter.excute();
        }
    }

    public static boolean isBlank(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isInteger(String str) {
        if (!RegularUtil.isInteger(str)) {
            return false;
        }
        if (str.length() > Dictionaries.IntegerMaxLength) {
            return false;
        }
        return true;
    }

    /**
     * 返回字符串中开始到匹配的最后一个字符(不包含)
     * lastCharToStart("xxx.yyy.zzz",".") => "xxx.yyy"
     */
    public static String lastCharToStart(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        return str.substring(0, endIndex);
    }

    /**
     * 返回字符串中匹配的最后一个字符(包含)到结尾
     * lastCharToEnd("xxx.yyy.zzz",".") => ".zzz"
     */
    public static String lastCharToEnd(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        return str.substring(endIndex);
    }

    /**
     * 返回字符串中最后一个字符(不包含)到结尾
     * lastCharToEnd("xxx.yyy.zzz",".") => "zzz"
     */
    public static String lastCharToEndNoCh(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex++ == -1) {
            return str;
        }
        if (endIndex == str.length()) {
            return "";
        }
        return str.substring(endIndex);
    }

    public static String randomStr32() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").toUpperCase();
    }
}