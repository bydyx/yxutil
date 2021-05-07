package com.bydyx.file.test;

import com.bydyx.file.ScanPackageUtil;

import java.util.List;

/**
 * @date 2020/8/11 10:27
 */
public class ScanPackageUtilTest {
    public static void main(String[] args) {
        ScanPackageUtil scanPackageUtil = new ScanPackageUtil("com.bydyx.asserts");
        List<String> fullyQualifiedClassNameList = scanPackageUtil.getFullyQualifiedClassNameList();
    }
}
