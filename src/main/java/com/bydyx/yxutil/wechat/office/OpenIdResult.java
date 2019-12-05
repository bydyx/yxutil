package com.bydyx.yxutil.wechat.office;

import com.bydyx.yxutil.string.StringUtil;
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

    public void checkResult() {
        if (!StringUtil.isBlank(errcode) && !"0".equals(errcode)) {
            throw new RuntimeException(errmsg);
        }
    }
}