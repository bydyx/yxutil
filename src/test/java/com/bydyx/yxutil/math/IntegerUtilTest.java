package com.bydyx.yxutil.math;

import org.junit.Test;

public class IntegerUtilTest {

    @Test
    public void testParseString() throws Exception {
        Integer integer = IntegerUtil.parseString("176.5146484375");
        System.out.println(integer);
    }

}