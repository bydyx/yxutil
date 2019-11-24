package com.bydyx.yxutil.pay.wx;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.annotation.NoSerialize;
import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.json.JsonUtil;
import com.bydyx.yxutil.pay.PayResult;
import com.bydyx.yxutil.pay.PayUtil;
import com.bydyx.yxutil.pay.exception.PayRTException;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 支付结果
 *
 * @author bydyx
 * @date 2019/11/13 13:59
 */
@Data
public class WxResultNotify implements PayResult {
    String appid;
    String mch_id;
    String device_info;
    String nonce_str;
    @NoSerialize
    String sign;
    String sign_type;
    String result_code;
    String err_code;
    String err_code_des;
    String openid;
    String is_subscribe;
    String trade_type;
    String bank_type;
    String settlement_total_fee;
    String fee_type;
    String cash_fee_type;
    String coupon_fee;
    String coupon_count;
    String transaction_id;
    String out_trade_no;
    String attach;
    String time_end;
    String return_code;
    String return_msg;
    Integer cash_fee;
    Integer total_fee;

    public static WxResultNotify parseXml(String xml) {
        JSONObject result = XmlUtil.xmlStr2Json(xml);
        return result.toJavaObject(WxResultNotify.class);
    }

    @Override
    public boolean isSuccess() {
        verifySign();

        boolean resultCode = "SUCCESS".equals(this.result_code);
        boolean returnCode = "SUCCESS".equals(this.return_code);
        return resultCode && returnCode;
    }

    private void verifySign() {
        List<Field> fieldList = PayUtil.getParams(this);
        Merchant merchant = PayUtil.getMerchantByAppid(appid);
        String newSign = PayUtil.createSign(this, fieldList, merchant.getMchKey());
        if (!newSign.equals(sign)) {
            throw new PayRTException("签名不一致!计算出:" + newSign + ";通知的签名:" + sign);
        }
    }

    @Override
    public String getErrMsg() {
        return return_msg + ",err_code:" + err_code + "err_code_des:" + err_code_des;
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