package com.bydyx.yxutil.string;


import java.util.HashMap;
import java.util.Map;

public class StringFormatUtilTest {

    // Map类型使用的格式化字符串
    private final static String formatForMap = "#{name}, #{china.name}, #{usa.name}, #{china.beijing.name}, #{china.beijing.xicheng.name}, {world.region1}, {world.child.region1}.";


    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        Map<String, Object> childMap = new HashMap<>();

        paramMap.put("name","冯强");
        paramMap.put("china",childMap);
        childMap.put("name","中国");
        String s = StringUtil.placeholderReplacement(formatForMap, paramMap);
        System.out.println(s);
    }

}