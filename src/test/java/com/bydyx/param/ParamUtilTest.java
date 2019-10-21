package com.bydyx.param;

import com.bydyx.param.annotation.CanNull;
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
        ParamUtil.objFieldsIsNull(this);
    }

    @Test
    public void testCheckObjsNotNull() throws Exception {
    }

    @Test
    public void testCheckObjIsNull() throws Exception {
    }

}