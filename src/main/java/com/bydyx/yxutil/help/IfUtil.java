package com.bydyx.yxutil.help;

import com.bydyx.yxutil.function.Excuter;

/**
 * @author qiang.feng
 * @date 2019/11/5 16:56
 */
public class IfUtil {

    public static void excute(Boolean bool, Excuter excuter) {
        if (bool) {
            excuter.excute();
        }
    }
}
