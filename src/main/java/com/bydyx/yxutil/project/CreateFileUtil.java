package com.bydyx.yxutil.project;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.reflex.ClassUtil;
import com.bydyx.yxutil.string.StringUtil;


/**
 * @author bydyx
 * @date 2019/11/8 10:42
 */
public class CreateFileUtil {

    // 根据json对象创建class
    public static void createClassByJson(JSONObject jsonObject) {
        jsonObject.keySet().forEach(key -> {
            Class<?> aClass = jsonObject.get(key).getClass();
            String className = StringUtil.lastCharToEndNoCh(aClass.getName(), ".");
            System.out.println(className + " " + key + ";");
        });
    }
}