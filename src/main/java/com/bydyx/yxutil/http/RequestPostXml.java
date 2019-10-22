package com.bydyx.yxutil.http;

import com.bydyx.yxutil.string.ObjToStrUtil;

/**
 * @author FigureFragrance
 * @date 2019/8/9 11:49
 */
public class RequestPostXml extends RequestPost implements RequestXml {
    String xmlStr;

    RequestPostXml() {
    }

    RequestPostXml(String url) {
        this.url = url;
    }

    RequestPostXml(String url, String xmlStr) {
        this.url = url;
        this.xmlStr = xmlStr;
    }

    @Override
    String generateRequestParams() {
        if (this.xmlStr != null) {
            return xmlStr;
        }
        return ObjToStrUtil.mapToXmlStr(params);
    }

    @Override
    public void setXmlParams(String xmlStr) {
        this.xmlStr = xmlStr;
    }
}
