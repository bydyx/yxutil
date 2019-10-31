package com.bydyx.yxutil.string;

import java.util.regex.Pattern;

/**
 * @author bydyx
 * @date 2019/10/21 11:18
 */
public class RegularUtil {
    public final static Pattern Integer_Pattern = Pattern.compile("^-?\\d+$");
    public static boolean isInteger(String str){

        return Integer_Pattern.matcher(str).matches();
    }
}