package com.bydyx.yxutil.param;

import com.bydyx.yxutil.param.annotation.CanNull;
import org.junit.Test;

public class ParamUtilTest {

    public ParamUtilTest() {
    }

    String name;
    @CanNull
    Integer age;

    @Test
    public void testObjIsNull() throws Exception {
        this.name = "";
        ParamUtil.checkObjIsNull(this);
    }

    @Test
    public void testCheckObjsNotNull() throws Exception {
    }

    @Test
    public void testCheckObjIsNull() throws Exception {
    }

}