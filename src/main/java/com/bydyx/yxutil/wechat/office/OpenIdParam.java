package com.bydyx.yxutil.wechat.office;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author bydyx
 * @date 2019/11/24 21:09
 */
@Data
public class OpenIdParam {
    private static final String url = "https://api.weixin.qq.com/sns/jscode2session";

    String appid;
    String secret;
    String js_code;

    public String getUrl() {
        StringBuilder stringBuilder = new StringBuilder(url);
        stringBuilder.append("?appid=" + appid);
        stringBuilder.append("&secret=" + secret);
        stringBuilder.append("&js_code=" + js_code);
        stringBuilder.append("&grant_type=authorization_code");
        return stringBuilder.toString();
    }

    public OpenIdParam(String appid, String secret, String js_code) {
        this.appid = appid;
        this.secret = secret;
        this.js_code = js_code;
    }

    public String getParam() {
        throw new RuntimeException("This is a GET Request!");
    }

    public Object getResultObj(String resultStr) {
        JSONObject jsonObject = JSON.parseObject(resultStr);
        return jsonObject;
    }
}
