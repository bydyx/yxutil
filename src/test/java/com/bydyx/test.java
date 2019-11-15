package com.bydyx;


import com.bydyx.yxutil.wechat.office.MessageTemplate;

/**
 * @author qiang.feng
 * @date 2019/11/15 9:28
 */
public class test {
    public static void main(String[] args) {
        MessageTemplate mst = new MessageTemplate("saxax","appid","color","userOpenid","templateId","fv");
        mst.addKeyWord("xx");
        mst.addKeyWord("2xx");
        mst.addKeyWord("3xx");
        mst.addKeyWord("4xx");
        System.out.println(mst.getUrl());
        System.out.println(mst.getRequestBody());
    }

}
