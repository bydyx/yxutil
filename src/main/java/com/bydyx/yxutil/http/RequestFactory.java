package com.bydyx.yxutil.http;


import com.bydyx.yxutil.http.entity.Method;

/**
 * @author FigureFragrance
 * @date 2019/8/8 17:26
 */
public class RequestFactory {

    public static RequestXml generateRequestXml(String url) {
        return new RequestPostXml(url);
    }

    public static RequestXml generateRequestXml(String url, String xmlStr) {
        return new RequestPostXml(url, xmlStr);
    }

    public static Request generateRequestPost(String url) {
        return generateRequest(Method.POST, url);
    }

    public static Request generateRequestGet(String url) {
        return generateRequest(Method.GET, url);
    }

    private static Request generateRequest(Method method,String url) {
        switch (method) {
            case GET:
                return new RequestGet(url);
            case POST:
                return new RequestPost(url);
            case PUT:
                return new RequestPut(url);
            default:
                System.err.println("method no Method.value!");
                return new RequestGet();
        }
    }
}
