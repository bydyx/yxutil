package com.bydyx.random;


import lombok.Getter;

import java.util.Collections;
import java.util.LinkedList;

/**
 * @date 2020/8/31 13:01
 */
public class NumberPool {
    NumberPoolConfig config = new NumberPoolConfig();
    @Getter
    LinkedList<String> pool;

    public void init() {
        pool = new LinkedList<>();
        fillPool();
    }

    public static NumberPool createPool() {
        NumberPool pool = new NumberPool();
        pool.init();
        return pool;
    }

    private void fillPool() {
        if (pool.size() <= config.warningValue) {
            int num = config.size - pool.size();
            for (int i = 0; i < num; i++) {
                String nextNum = config.createNextNum();
                pool.add(nextNum);
            }
        }
    }

    public synchronized String getNumber() {
        fillPool();
        Collections.shuffle(pool);
        return pool.poll();
    }
}