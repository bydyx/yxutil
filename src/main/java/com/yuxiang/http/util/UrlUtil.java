package com.yuxiang.http.util;

import com.yuxiang.http.entity.RequestParams;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * URL处理
 *
 * @author FigureFragrance
 * @date 2019/7/1 13:39
 */
public class UrlUtil {


    public static String encodeStr(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // It's impossible
            e.printStackTrace();
        }
        return str;
    }


    /**
     * 把map适配成HttpPost识别的参数
     *
     * @param map      前端传的post的参数
     * @param postData 把map类型的参数转为NameValuePair类型
     * @return
     * @date 2019/7/4 11:07
     * @author FigureFragrance
     */
    public static void setHttpPostData(List<NameValuePair> postData, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
    }

    /**
     * GET请求的URL处理,把请求体中的参数拼接到URL中
     * url:"http://xxxx/test"
     * data:{
     *      xx:cc
     * }
     * => url:"http://xxxx/test?xx=cc"
     */
    public static String getUrlAppendParams(RequestParams requestParams) {
        Map<String, Object> data = requestParams.getData();
        StringBuffer url = new StringBuffer();
        if (data.size() > 0) {
            //url拼接参数
            url.append("?");
            data.forEach((key, value) -> {
                url.append(key);
                url.append("=");
                url.append(value);
                url.append("&");
            });
            //去除url中最后的&
            url.deleteCharAt(url.length() - 1);
        }
        String result = url.toString();
        if (requestParams.isEncode()) {
            result = URLEncoder.encode(result);
        }
        result = requestParams.getUrl() + result;
        return result;
    }

    /**
     * GET请求的URL处理
     * 把请求体中的参数拼接到URL中,增加占位符
     * url:"http://xxxx/test"
     * data:{
     * xx:cc
     * }
     * => url:"http://xxxx/test?xx={{xx}}"
     *
     * @param requestParams
     * @date 2019/7/1 13:40
     * @author FigureFragrance
     */
    public static String getUrlAppendParamsPlaceholder(RequestParams requestParams) {
        Map<String, Object> data = requestParams.getData();
        StringBuffer url = new StringBuffer(requestParams.getUrl());
        if (data.size() > 0) {
            url.append("?");
            data.forEach((key, value) -> {
                url.append(key);
                url.append("={");
                url.append(key);
                url.append("}&");
            });
            //去除url中最后的&
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }
}