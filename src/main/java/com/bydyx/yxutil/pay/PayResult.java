package com.bydyx.yxutil.pay;

import com.alibaba.fastjson.JSONObject;

/**
 * @author qiang.feng
 * @date 2019/11/13 15:19
 */
public interface PayResult {

    boolean isSuccess();

    String getErrMsg();

    Integer getFee();

    String getOrderNo();

    JSONObject toJson();
}
