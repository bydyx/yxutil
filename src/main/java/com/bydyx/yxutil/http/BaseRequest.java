package com.bydyx.yxutil.http;

import com.bydyx.yxutil.http.exception.RequestFailException;
import com.bydyx.yxutil.http.util.HttpClientBuilderUtil;
import com.bydyx.yxutil.http.util.UrlUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author FigureFragrance
 * @date 2019/8/8 17:04
 */
public abstract class BaseRequest implements Request {
    String url = "";
    Boolean isEncode = false;
    Map<String, Object> params = new HashMap<>();

    abstract String generateRequestParams();

    abstract HttpUriRequest generateHttpUriRequest();


    @Override
    public String execute() throws RequestFailException {
        HttpUriRequest httpUriRequest = this.generateHttpUriRequest();
        return this.execute(httpUriRequest);
    }

    @Override
    public void setEncode(boolean b) {
        this.isEncode = b;
    }

    @Override
    public void addParam(String key, Object param) {
        params.put(key, param);
    }

    @Override
    public Map<String, Object> getParams() {
        return params;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    String encodeParamStr(String paramStr) {
        return UrlUtil.encodeStr(paramStr);
    }

    private String execute(HttpUriRequest httpUriRequest) throws RequestFailException {
        try {
            CloseableHttpResponse response = HttpClientBuilderUtil.getBuilder().execute(httpUriRequest);
            this.judgeHttpCode(response.getStatusLine().getStatusCode());
            String result = this.getResult(response.getEntity());
            response.close();
            return result;
        } catch (IOException e) {
            throw new RequestFailException(e);
        }
    }

    private void judgeHttpCode(int statusInt) throws RequestFailException {
        if (200 != statusInt) {
            throw new RequestFailException("请求失败!http状态码:" + statusInt);
        }
    }

    private String getResult(HttpEntity entity) throws IOException {
        if (null != entity) {
            return EntityUtils.toString(entity, "UTF-8");
        }
        return "";
    }
}