package com.bydyx.yxutil.math;

import java.util.Random;

/**
 * @author bydyx
 * @date 2019/11/10 20:54
 */
public class IntegerUtil {

    /**
     * 获取随机数
     */
    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) + min;
    }

}