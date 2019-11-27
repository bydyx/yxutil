package com.bydyx.yxutil.file;


import com.bydyx.yxutil.exception.file.FileRTException;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author FigureFragrance
 * @date 2019/7/30 13:08
 */
public class PropertiesUtil {

    /**
     * 读取properties文件中的某个值
     */
    public static String getPropertiesValueByKey(String path, String key) {
        Properties properties = getProperties(path);
        //获取key对应的value值
        return properties.getProperty(key);
    }

    public static Map<String, String> readProperties(String path) {
        Properties properties = getProperties(path);
        Set<String> keys = properties.stringPropertyNames();
        return keys.stream()
                   .collect(Collectors.toMap(String::toString, properties::getProperty));
    }

    public static Properties getProperties(String path) {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            InputStream ins = classLoader.getResourceAsStream(path);
            properties.load(ins);
            ins.close();
            return properties;
        } catch (IOException e) {
            throw new FileRTException(e.getMessage());
        }
    }
}
