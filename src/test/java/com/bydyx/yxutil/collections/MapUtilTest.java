package com.bydyx.yxutil.collections;

import com.bydyx.yxutil.string.entity.JwtToken;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class MapUtilTest {

    @Test
    public void testObj2Map() throws Exception {
        JwtToken jwtToken = new JwtToken("xx", new Date());
        Map<String, Object> stringObjectMap = MapUtil.obj2Map(jwtToken);
        Date date = (Date) (stringObjectMap.get("expirationDate"));
        System.out.println(date.getTime());
    }
}