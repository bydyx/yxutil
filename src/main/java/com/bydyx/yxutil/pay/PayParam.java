package com.bydyx.yxutil.pay;

import com.bydyx.yxutil.time.entity.TimeUnit;

/**
 * 支付接口,并不会向对应的平台发起请求,只会生成对应的请求参数
 *
 * @author bydyx
 */
public interface PayParam extends PayRequest {

    /**
     * 附加数据,自定义参数,大小127个字符
     */
    String setInfo(String attach);

    /**
     * 设置支付截止日期
     *
     * @param num      数量
     * @param timeUnit 时间单位
     */
    void setEndDate(int num, TimeUnit timeUnit);
}