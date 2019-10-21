package com.bydyx.util.reflex;

import com.alibaba.fastjson.JSON;

/**
 * @author qiang.feng
 * @date 2019/10/18 16:03
 */
public class ConvertUtil {

    // 对于不确定的对象的类型转换
    public static <T> T typeCast(Object item, Class<T> clazz) {
        if (ClassUtil.isBaseType(clazz)) {
            return clazz.cast(item);
        }
        return JSON.parseObject(item.toString(), clazz);
    }
}
