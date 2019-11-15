package com.bydyx.yxutil.wechat.office;

import com.alibaba.fastjson.JSONObject;
import com.bydyx.yxutil.string.StringUtil;
import lombok.Data;

import java.util.*;

/**
 * 微信模板消息参数类
 *
 * @author qiang.feng
 * @date 2019/11/15 8:40
 */
@Data
public class MessageTemplate {
    private static final String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    String accessToken;

    String appId;
    String color;
    String toUser;
    String pagePath;
    String templateId;
    String forwardUrl;
    String firstValue;
    String firstColor;
    String remarkValue;
    String remarkColor;
    String keyWordColor;
    final List<String> keyWordList = new ArrayList<>();

    /**
     * @param accessToken 微信token
     * @param appId       公众号id
     * @param color       关键字默认颜色
     * @param toUser      接收人在appid对应公众号的openid
     * @param templateId  模板消息id
     * @date 2019/11/15 10:04
     * @author qiang.feng
     */
    public MessageTemplate(String accessToken, String appId, String color, String toUser, String templateId, String firstValue) {
        this.appId = appId;
        this.color = color;
        this.toUser = toUser;
        this.templateId = templateId;
        this.firstValue = firstValue;
        this.accessToken = accessToken;
    }

    public String getUrl() {
        return url + accessToken;
    }

    public String getRequestBody() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("touser", getToUser());
        requestBody.put("data", getContent());
        requestBody.put("template_id", getTemplateId());
        requestBody.put("miniprogram", getMiniProgram());
        requestBody.put("url", getForwardUrl());

        return requestBody.toJSONString();
    }

    private Map<String, String> getMiniProgram() {
        Map<String, String> map = new HashMap<>(2);
        map.put("appid", getAppId());
        map.put("pagepath", getPagePath());
        return map;
    }

    private Map<String, Map<String, String>> getContent() {
        Map<String, Map<String, String>> dataMap = new HashMap<>(keyWordList.size() + 2);
        dataMap.put("first", getFirstMap());
        setKeyWordList(dataMap);
        if (!StringUtil.isBlank(getRemarkValue())) {
            dataMap.put("remark", getRemarkMap());
        }
        return dataMap;
    }

    private void setKeyWordList(Map<String, Map<String, String>> dataMap) {
        int i = 1;
        for (final String value : keyWordList) {
            Map<String, String> map = new HashMap<>(2);
            map.put("value", value);
            map.put("color", getKeyWordColor());
            dataMap.put("keyword" + i++, map);
        }
    }

    private Map<String, String> getKeyValue(String value, String color) {
        Map<String, String> keyWord = new HashMap<>();
        keyWord.put("value", value);
        keyWord.put("color", color);
        return keyWord;
    }

    private Map<String, String> getFirstMap() {
        return getKeyValue(getFirstValue(), getFirstColor());
    }

    private Map<String, String> getRemarkMap() {
        return getKeyValue(getRemarkValue(), getRemarkColor());
    }

    public String getFirstColor() {
        if (StringUtil.isBlank(firstColor)) {
            return color;
        }
        return firstColor;
    }

    public String getRemarkColor() {
        if (StringUtil.isBlank(remarkColor)) {
            return color;
        }
        return remarkColor;
    }

    public String getKeyWordColor() {
        if (StringUtil.isBlank(keyWordColor)) {
            return color;
        }
        return keyWordColor;
    }

    public void addKeyWord(String keyWord) {
        keyWordList.add(keyWord);
    }
}