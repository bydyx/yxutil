package com.bydyx.yxutil.http.util;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

/**
 * HttpClient生成工具
 *
 * @author FigureFragrance
 * @date 2019/7/4 11:21
 */
public class HttpClientBuilderUtil {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36";

    /**
     * 超时设置
     */
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
    .setConnectTimeout(5000)
    .setConnectionRequestTimeout(5000)
    .setSocketTimeout(10000)
    .build();

    /**
     * 编码设置
     */
    private static final ConnectionConfig CONNECTION_CONFIG = ConnectionConfig.custom()
    .setMalformedInputAction(CodingErrorAction.IGNORE)
    .setUnmappableInputAction(CodingErrorAction.IGNORE)
    .setCharset(Consts.UTF_8)
    .build();

    /**
     * 生成HttpClient
     * @return CloseableHttpClient
     * @date 2019/7/8 8:18
     * @author FigureFragrance
     */
    public static CloseableHttpClient getBuilder() {
        List<Header> headers = new ArrayList<>();
        Header header = new BasicHeader("User-Agent", USER_AGENT);
        headers.add(header);
        CloseableHttpClient httpClientBuilder = HttpClients.custom().setDefaultConnectionConfig(CONNECTION_CONFIG).setDefaultHeaders(headers).setDefaultRequestConfig(REQUEST_CONFIG).build();
        return httpClientBuilder;
    }
}
