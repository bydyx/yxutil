package com.bydyx.yxutil.collections;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author bydyx
 * @date 2019/10/21 9:29
 */
public class ListUtil {

    public static <T> HashSet<T> toHashSet(List<T> list) {
        return new HashSet<>(list);
    }

    public static <T, R> List<R> listConvert(List<T> list, Function<T, R> mapper) {
        return list.stream()
                   .map(mapper)
                   .collect(Collectors.toList());
    }

    public static <T> List<T> duplicateRemoval(List<T> list) {
        return list.stream()
                   .distinct()
                   .collect(Collectors.toList());
    }

    public static <T> List<T> merge(List<T> bodyList, List<T> list2) {
        bodyList.addAll(list2);
        return bodyList;
    }

    /**
     * 新建含有一个子元素的list
     *
     * @date 2020/2/18 11:07
     * @author qiang.feng
     */
    public static <T> List<T> createList(T t) {
        ArrayList<T> list = new ArrayList<>();
        list.add(t);
        return list;
    }

    public static <T> List<T> createList(T... arrays) {
        ArrayList<T> list = new ArrayList<>();
        for (final T t : arrays) {
            list.add(t);
        }
        return list;
    }

    /**
     * 如果list是null,那么创建一个空的list
     *
     * @date 200/3/9 9:38
     * @author qiang.feng
     */
    public static <T> List<T> listOrNullCreateArrayList(List<T> list) {
        if (list == null) {
            return new ArrayList<>();
        }
        return list;
    }

}