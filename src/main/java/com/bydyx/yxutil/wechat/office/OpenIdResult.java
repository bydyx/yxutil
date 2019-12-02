package com.bydyx.yxutil.wechat.office;

import lombok.Data;

/**
 * @author qiang.feng
 * @date 2019/12/2 8:44
 */
@Data
public class OpenIdResult {
    String openid;
    String session_key;
    String unionid;
    String errmsg;
    String errcode;
}