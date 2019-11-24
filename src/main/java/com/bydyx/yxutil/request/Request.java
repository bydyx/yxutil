package com.bydyx.yxutil.request;

/**
 * @author bydyx
 */
public interface Request {

    /**
     * @date 2019/11/24 21:26
     * @author bydyx
     */
    String getUrl();

    /**
     * 创建请求参数
     *
     * @date 2019/11/24 21:20
     * @author bydyx
     */
    String getParam();

    /**
     * 解析结果
     *
     * @param resultStr 请求返回的结果字符串
     * @date 2019/11/24 21:16
     * @author bydyx
     */
    Object getResultObj(String resultStr);
}
