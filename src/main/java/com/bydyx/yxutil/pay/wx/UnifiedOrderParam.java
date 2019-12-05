package com.bydyx.yxutil.pay.wx;

import com.bydyx.yxutil.pay.NoSerialize;
import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.param.annotation.NotNull;
import com.bydyx.yxutil.pay.PayParam;
import com.bydyx.yxutil.pay.PayResult;
import com.bydyx.yxutil.pay.PayUtil;
import com.bydyx.yxutil.pay.exception.PayRTException;
import com.bydyx.yxutil.request.Method;
import com.bydyx.yxutil.string.StringUtil;
import com.bydyx.yxutil.time.TimeUtil;
import com.bydyx.yxutil.time.entity.TimeFormat;
import com.bydyx.yxutil.time.entity.TimeUnit;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * @author bydyx
 * @date 2019/11/12 15:54
 */
@Data
public class UnifiedOrderParam implements PayParam {
    @NoSerialize
    public final static String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    @NotNull
    String appid;
    @NotNull
    String mch_id;
    @NotNull
    String nonce_str;
    @NotNull
    String body;
    @NotNull
    String out_trade_no;
    @NotNull
    Integer total_fee;
    @NotNull
    String spbill_create_ip;
    @NotNull
    String notify_url;
    @NotNull
    String trade_type;
    @NoSerialize
    String sign;

    String device_info;
    String sign_type;
    String detail;
    String attach;
    String fee_type;
    String time_start;
    String time_expire;
    String goods_tag;
    String product_id;
    String limit_pay;
    String openid;
    String receipt;

    @NoSerialize
    String mchKey;

    public UnifiedOrderParam(String appid,
                             String mch_id,
                             String body,
                             Integer total_fee,
                             String notify_url,
                             String spbill_create_ip,
                             String mchKey,
                             TradeType tradeType) {
        this.body = body;
        this.appid = appid;
        this.mchKey = mchKey;
        this.mch_id = mch_id;
        this.total_fee = total_fee;
        this.notify_url = notify_url;
        this.trade_type = tradeType.toString();
        this.spbill_create_ip = spbill_create_ip;

        this.sign_type = "MD5";
        this.nonce_str = StringUtil.randomStr32();
        this.out_trade_no = StringUtil.createOrderNo();
    }

    @Override
    public String getParam() {
        return this.toXmlString();
    }

    @Override
    public String setInfo(String attach) {
        return this.attach = attach;
    }

    /**
     * 设置结束时间,结束时间限制最大为2h
     *
     * @param num      时间单位的数量
     * @param timeUnit 时间单位,限制为时分秒
     * @date 2019/11/13 11:26
     * @author bydyx
     */
    @Override
    public void setEndDate(int num, TimeUnit timeUnit) {
        if (checkDate(num, timeUnit)) {
            num = 2;
            timeUnit = TimeUnit.HOUR;
        }
        Date startDate = TimeUtil.getCurrentDate();
        Date endDate = TimeUtil.add(startDate, timeUnit, num);

        this.time_start = TimeUtil.dateTimeFormat(startDate, TimeFormat.yyyyMMddHHmmss);
        this.time_expire = TimeUtil.dateTimeFormat(endDate, TimeFormat.yyyyMMddHHmmss);
    }

    /**
     * 1.空值字段不转
     * 2.必须字段检查
     *
     * @return 微信支付的xml参数
     */
    public String toXmlString() {
        List<Field> fieldList = PayUtil.getParams(this);
        this.sign = PayUtil.createSign(this, fieldList, mchKey);
        return PayUtil.createXmlString(this, fieldList, sign);
    }


    private boolean checkDate(int num, TimeUnit timeUnit) {
        int second = TimeUtil.getSecond(num, timeUnit);
        int maxEndDate = this.getMaxEndDate();
        return second > maxEndDate;
    }

    private int getMaxEndDate() {
        int second = TimeUtil.getSecond(2, TimeUnit.HOUR);
        return second;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public PayResult getResultObj(String resultStr) {
        UnifiedOrderResult result = XmlUtil.xmlStr2Json(resultStr).toJavaObject(UnifiedOrderResult.class);
        if (!result.isSuccess()) {
            throw new PayRTException("预下单失败:"+result.getReturn_msg());
        }
        return result;
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public void addParam(String key, Object value) {

    }
}