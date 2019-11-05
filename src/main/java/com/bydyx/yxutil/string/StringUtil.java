package com.bydyx.yxutil.string;

import com.bydyx.yxutil.Dictionaries;
import com.bydyx.yxutil.function.Excuter;

import java.util.UUID;

/**
 * @author bydyx
 * @date 2019/10/18 16:45
 */
public class StringUtil {

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

    public static String lastCharToEnd(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex == -1) {
            return str;
        }
        return str.substring(endIndex);
    }

    public static String lastCharToEndNoCh(String str, String ch) {
        int endIndex = str.lastIndexOf(ch);
        if (endIndex++ == -1) {
            return str;
        }
        if(endIndex == str.length()){
            return "";
        }
        return str.substring(endIndex);
    }

    public static String randomStr32() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "").toUpperCase();
    }
}