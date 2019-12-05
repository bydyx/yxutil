package com.bydyx.yxutil.pay.wx;

import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.pay.PayResult;
import com.bydyx.yxutil.pay.PayUtil;
import com.bydyx.yxutil.request.Method;
import com.bydyx.yxutil.request.Request;
import com.bydyx.yxutil.string.StringUtil;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author bydyx
 * @date 2019/11/13 16:20
 */
@Data
public class OrderQueryParam implements Request {
    String appid;
    String mch_id;
    String out_trade_no;
    String nonce_str;
    String sign;
    String sign_type;

    @Override
    public PayResult getResultObj(String result) {
        return XmlUtil.xmlStr2Json(result).toJavaObject(OrderQueryResult.class);
    }

    public OrderQueryParam(String appid, String mch_id, String out_trade_no) {
        this.appid = appid;
        this.mch_id = mch_id;
        this.out_trade_no = out_trade_no;
        this.nonce_str = StringUtil.randomStr32();
        this.sign_type = "MD5";
    }

    @Override
    public String getParam() {
        List<Field> fieldList = PayUtil.getParams(this);
        Merchant merchant = PayUtil.getMerchantByAppid(appid);
        this.sign = PayUtil.createSign(this, fieldList, merchant.getMchKey());
        return PayUtil.createXmlString(this, fieldList, sign);
    }

    @Override
    public void addParam(String key, Object value) {

    }

    @Override
    public String getUrl() {
        return "https://api.mch.weixin.qq.com/pay/orderquery";
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }
}
