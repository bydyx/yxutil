package com.yuxiang.util.param;

import com.yuxiang.util.param.exception.WrongParamException;

/**
 * @author bydyx
 * @date 2019/10/21 9:38
 */
public class ParamUtil {

    public static void checkNotNull(Object... args) {
        for (Object obj : args) {
            if (obj == null) {
                throw new WrongParamException(args);
            }
        }
    }
}