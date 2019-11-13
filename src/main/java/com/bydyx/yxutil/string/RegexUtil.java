package com.bydyx.yxutil.string;

import java.util.regex.Pattern;

/**
 * @author bydyx
 * @date 2019/10/21 11:18
 */
public class RegexUtil {
    public final static Pattern IntegerPattern = Pattern.compile("^-?\\d+$");
    public final static Pattern numberPattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    public static boolean isInteger(String str) {
        return IntegerPattern.matcher(str).matches();
    }

    public static boolean isNumber(String str) {
        return numberPattern.matcher(str).matches();
    }
}