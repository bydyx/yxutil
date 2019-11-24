package com.bydyx.yxutil.pay.wx;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.json.JsonUtil;
import com.bydyx.yxutil.pay.PayResult;
import lombok.Data;

/**
 * @author bydyx
 * @date 2019/11/13 16:52
 */
@Data
public class OrderQueryResult implements PayResult {
    String return_code;
    String return_msg;
    String appid;
    String mch_id;
    String nonce_str;
    String sign;
    String result_code;
    String err_code;
    String err_code_des;
    String device_info;
    String openid;
    String is_subscribe;
    String trade_type;
    String trade_state;
    String bank_type;
    String total_fee;
    String settlement_total_fee;
    String fee_type;
    Integer cash_fee;
    String cash_fee_type;
    String coupon_fee;
    String coupon_count;
    String coupon_type_$n;
    String coupon_id_$n;
    String coupon_fee_$n;
    String transaction_id;
    String out_trade_no;
    String attach;
    String time_end;
    String trade_state_desc;

    @Override
    public boolean isSuccess() {
        boolean resultCode = "SUCCESS".equals(this.result_code);
        boolean returnCode = "SUCCESS".equals(this.return_code);
        return resultCode && returnCode ;
    }

    @Override
    public String getErrMsg() {
        return return_msg + err_code + err_code_des;
    }

    @Override
    public Integer getFee() {
        return cash_fee;
    }

    @Override
    public String getOrderNo() {
        return out_trade_no;
    }

    @Override
    public JSONObject toJson() {
        return JsonUtil.objToJsonObj(this);
    }
}
