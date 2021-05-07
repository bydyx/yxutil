package com.bydyx.file.test;

import com.bydyx.file.FileUtil;

/**
 * @author 风清扬
 * @date 2020/9/29 17:29
 */
public class FileUtilTest {
    public static void main(String[] args) {
        String s = FileUtil.readFileByChars("C:\\Users\\13624\\Desktop\\新建文本文档.txt");
        System.out.println(s);
    }
}
