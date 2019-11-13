package com.bydyx.yxutil.pay;

public interface PayRequest {
    /**
     * @return 请求时的请求参数
     */
    String createRequestParam();

    /**
     * 请求地址
     *
     * @return
     */
    String getUrl();
    /**
     * 解析结果
     */
    PayResult getResultObj(String result);
}
