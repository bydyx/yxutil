package com.bydyx.util.param.exception;

import com.bydyx.util.string.StringUtil;

/**
 * @author qiang.feng
 * @date 2019/10/21 9:39
 */
public class WrongParamException extends RuntimeException {
    public WrongParamException(Object... params) {
        super("错误的参数列表:" + StringUtil.objsToStr(params));
    }
}
