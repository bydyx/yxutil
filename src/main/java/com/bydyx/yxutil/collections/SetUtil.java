package com.bydyx.yxutil.collections;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author qiang.feng
 * @date 2020/4/16 11:08
 */
public class SetUtil {
    public static <T, R> Set<R> convert(Set<T> list, Function<T, R> mapper) {
        return list.stream()
                   .map(mapper)
                   .collect(Collectors.toSet());
    }

}