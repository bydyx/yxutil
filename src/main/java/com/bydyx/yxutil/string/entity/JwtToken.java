package com.bydyx.yxutil.string.entity;

import com.bydyx.yxutil.string.JwtUtil;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiang.feng
 * @date 2019/11/28 11:25
 */
@Data
public class JwtToken {
    String secret;
    Date expirationDate;
    Map<String, String> data = new HashMap<>();

    public JwtToken(String secret, Date expirationDate) {
        this.secret = secret;
        this.expirationDate = expirationDate;
    }

    public void addData(String key, String value) {
        data.put(key, value);
    }

    public String createToken() {
        return JwtUtil.createToken(data, secret, expirationDate);
    }
}