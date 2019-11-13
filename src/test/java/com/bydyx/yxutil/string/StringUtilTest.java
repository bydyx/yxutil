package com.bydyx.yxutil.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringUtilTest {

    @Test
    public void testIsBlank() throws Exception {
    }

    @Test
    public void testCreateVerifyCode() {
        for (int i = 0; i < 10; i++) {
            String result = StringUtil.createVerifyCode();
            System.out.println(result);
        }
    }

    @Test
    public void testLastCharToStart() {
        String result = StringUtil.lastCharToStart("xxx.yyy.zzz", ".");
        System.out.println(result);
    }

    @Test
    public void testIsInteger() throws Exception {
        Assert.assertEquals(true, StringUtil.isInteger("-5"));
        Assert.assertEquals(true, StringUtil.isInteger("5"));
        Assert.assertEquals(true, StringUtil.isInteger("-554"));
        Assert.assertEquals(true, StringUtil.isInteger("554"));

        Assert.assertEquals(false, StringUtil.isInteger("-5.0"));
        Assert.assertEquals(false, StringUtil.isInteger("5.0"));
        Assert.assertEquals(false, StringUtil.isInteger(""));
        Assert.assertEquals(false, StringUtil.isInteger("qq"));
        Assert.assertEquals(false, StringUtil.isInteger("  "));

    }

    @Test
    public void testRandomStr32() throws Exception {
    }

    @Test
    public void testObjsToStr() throws Exception {
    }


} 
