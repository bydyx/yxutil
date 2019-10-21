package com.bydyx.http;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author FigureFragrance
 * @date 2019/8/9 11:29
 */
public class RequestPut extends BaseRequest {
    RequestPut(){}
    RequestPut(String url){
        this.url = url;
    }
    @Override
    String generateRequestParams() {
        return new RequestGet().generateRequestParams();
    }

    @Override
    HttpUriRequest generateHttpUriRequest() {
        String urlParams = new RequestGet().generateUrl();
        return new HttpGet(url + urlParams);
    }
}
