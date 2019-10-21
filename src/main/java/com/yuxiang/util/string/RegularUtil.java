package com.yuxiang.util.string;

/**
 * @author qiang.feng
 * @date 2019/10/21 11:18
 */
public class RegularUtil {
    public static boolean isInteger(String str){
        return RegularPattern.Integer_Pattern.matcher(str).matches();
    }
}