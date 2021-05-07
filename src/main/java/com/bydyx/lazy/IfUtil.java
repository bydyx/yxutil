package com.bydyx.lazy;

import java.util.function.Supplier;

/**
 * @date 2020/8/17 15:00
 */
public class IfUtil {
    public static <T> void trueDo(boolean bool, Supplier<T> supplier) {
        if (bool) {
            supplier.get();
        }
    }
}
