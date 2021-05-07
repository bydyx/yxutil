package com.bydyx.random;

import java.util.ArrayList;

/**
 * @date 2020/9/1 9:18
 */
public class PoolTest {
    static int forCount = 10;
    static ArrayList<String> list = new ArrayList(2000);
    static NumberPool pool = NumberPool.createPool();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            new Thread(PoolTest::dos).start();
        }
        Thread.sleep(100);
        list.stream()
            .mapToInt(Integer::valueOf)
            .sorted()
            .forEach(System.out::println);

        System.out.println(pool.getPool());
    }

    private static void dos() {
        for (int i = 0; i < forCount; i++) {
            String number = pool.getNumber();
            list.add(number);
        }
    }
}