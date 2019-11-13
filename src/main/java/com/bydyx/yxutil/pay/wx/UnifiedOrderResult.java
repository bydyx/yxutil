package com.bydyx.yxutil.pay.wx;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.json.JsonUtil;
import com.bydyx.yxutil.pay.PayResult;
import lombok.Data;

/**
 * @author qiang.feng
 * @date 2019/11/12 15:54
 */
@Data
public class UnifiedOrderResult implements PayResult {
    String return_code;
    String return_msg;
    String appid;
    String mch_id;
    String device_info;
    String nonce_str;
    String sign;
    String result_code;
    String err_code;
    String err_code_des;
    String trade_type;
    String prepay_id;
    String code_url;

    @Override
    public boolean isSuccess() {
        boolean resultCode = "SUCCESS".equals(this.result_code);
        boolean returnCode = "SUCCESS".equals(this.return_code);
        return resultCode && returnCode;
    }

    @Override
    public String getErrMsg() {
        return return_msg + err_code + err_code_des;
    }

    @Override
    public Integer getFee() {
        return null;
    }

    @Override
    public String getOrderNo() {
        return null;
    }

    @Override
    public JSONObject toJson() {
        return JsonUtil.objToJsonObj(this);
    }
}