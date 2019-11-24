package com.bydyx.yxutil.pay.wx;

import lombok.Data;

/**
 * 微信商户
 *
 * @author bydyx
 * @date 2019/11/13 10:26
 */
@Data
public class Merchant {
    String mchid;
    String mchKey;
    String appid;
    String appSecret;

    public Merchant(String mchid, String mchKey, String appid, String appSecret) {
        this.mchid = mchid;
        this.mchKey = mchKey;
        this.appid = appid;
        this.appSecret = appSecret;
    }
}