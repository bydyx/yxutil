package com.bydyx.yxutil.http;

import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * @author FigureFragrance
 * @date 2019/8/9 10:45
 */
public class RequestPost extends BaseRequest {
    RequestPost(){}
    RequestPost(String url){
        this.url = url;
    }
    @Override
    protected HttpUriRequest generateHttpUriRequest() {
        String params = this.generateRequestParams();
        StringEntity paramsEntity = this.generateStringEntity(params);
        return generateHttpPost(paramsEntity);
    }

    @Override
    String generateRequestParams() {
        return JSON.toJSONString(this.params);
    }

    private StringEntity generateStringEntity(String params) {
        if (isEncode) {
            params = encodeParamStr(params);
        }
        StringEntity paramsEntity = this.initializationStringEntity(params);
        paramsEntity.setContentType("application/json");
        paramsEntity.setContentEncoding("UTF-8");
        return paramsEntity;
    }

    private HttpPost generateHttpPost(StringEntity paramsEntity) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(paramsEntity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");
        return httpPost;
    }

    private StringEntity initializationStringEntity(String params) {
        StringEntity stringEntity = null;
        try {
            stringEntity = new StringEntity(params);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();// It's impossible
        }
        return stringEntity;
    }
}