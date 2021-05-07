package com.bydyx.test;

import com.bydyx.string.PrintUtil;

/**
 * @author q.f
 * @date 2020/8/4 9:33
 */
public class Main {
    public static void main(String[] args) {
        PrintUtil.printJsonStr(1L << 32);
        PrintUtil.printJsonStr(Math.pow(2, 32));
        long count = 2;
        for (int i = 2; i <= 32; i++) {
            count = count * 2L;
        }
        PrintUtil.print("2的第{}次方是:{}", 32, count);
    }
}
