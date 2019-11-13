package com.bydyx.yxutil.math;

import com.bydyx.yxutil.string.RegexUtil;
import com.bydyx.yxutil.string.StringUtil;
import com.bydyx.yxutil.string.exception.StringFormatRTException;

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

    /**
     * 数字型字符串转int,且去除 .后数字
     */
    public static Integer parseString(String numStr) {
        if (!RegexUtil.isNumber(numStr)) {
            throw new StringFormatRTException("非数字类型字符串:" + numStr);
        }
        String str = StringUtil.lastCharToStart(numStr, ".");
        return Integer.valueOf(str);
    }

}