package com.bydyx.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author FigureFragrance
 * @date 2019/8/9 10:56
 */
public class RequestGet extends BaseRequest {
    RequestGet(){}
    RequestGet(String url){
        this.url = url;
    }
    @Override
    protected HttpUriRequest generateHttpUriRequest() {
        String urlParams = this.generateUrl();
        return new HttpGet(url + urlParams);
    }

    public String generateUrl() {
        String urlParams = this.generateRequestParams();
        if (isEncode) {
            return encodeParamStr(urlParams);
        }
        return urlParams;
    }

    @Override
    String generateRequestParams() {
        StringBuilder paramsSB = new StringBuilder("?");
        this.params.forEach((key, value) -> this.appendParams(paramsSB, key, value));
        paramsSB.deleteCharAt(paramsSB.length() - 1);
        return paramsSB.toString();
    }

    private void appendParams(StringBuilder sb, String key, Object value) {
        sb.append(key);
        sb.append("=");
        sb.append(value);
        sb.append("&");
    }
}
