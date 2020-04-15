package com.bydyx.yxutil.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author qiang.feng
 * @date 2020/3/16 10:50
 */
public class ArrayUtil {

    public static <T> List<T> arrayToList(T[] array) {
        List<T> list = new ArrayList<>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    public static <T> List<T> arrayToList(Object[] array, Class<T> clazz) {
        return arrayToList(array).stream()
                                 .map(clazz::cast)
                                 .collect(Collectors.toList());
    }
}