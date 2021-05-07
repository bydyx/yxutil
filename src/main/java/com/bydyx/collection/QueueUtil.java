package com.bydyx.collection;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date 2020/8/11 10:40
 */
public class QueueUtil {
    public static <T> Queue<T> queueLinkedListInit(T... params) {
        return new LinkedList<T>() {{
            for (T param : params) {
                offer(param);
            }
        }};
    }
}