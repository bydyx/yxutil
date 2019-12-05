package com.bydyx.yxutil.string;

import com.bydyx.yxutil.time.TimeUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import com.bydyx.yxutil.time.entity.TimeUnit;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtilTest {

    @Test
    public void testJwt() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImhwYyIsImV4cCI6MTU3NTAxMTE3MH0.3JUBD2vHp1LsLAVkx6lNToApCLDRzaAqxkMkatiKmxE";
        JwtUtil.getValues(token).forEach((s, s2) -> System.out.println(s + ":" + s2));
    }

    @Test
    public void testCreateJwtToken() throws Exception {
        String userName = "xx";
        String secret = "xx";
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("userName", userName);
        Date expirationDate = TimeUtil.add(TimeUnit.MINUTE, 20);

        System.out.println(TimeUtil.dateTimeFormat(expirationDate, TimeFormat.HyyyyMMddHHmmss));
        String jwtToken = JwtUtil.createToken(infoMap, secret, expirationDate);
        String username1 = JwtUtil.getValue(jwtToken, "userName");
        System.out.println(jwtToken);
        infoMap.put("userName", userName);
        JwtUtil.verify(infoMap, jwtToken, secret);

    }
}