package com.bydyx.util.string;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testIsBlank() throws Exception {
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
