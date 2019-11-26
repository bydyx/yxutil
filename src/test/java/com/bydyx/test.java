package com.bydyx;


import com.bydyx.yxutil.wechat.office.MessageTemplate;

/**
 * @author bydyx
 * @date 2019/11/15 9:28
 */
public class test {
    public static void main(String[] args) {
        String assessToken = "27_79QZVkWjLELNTecLpYCuE0y6tdJUBGqN5b0KDcOdvzeAJ5cQVh5opXtWm7bFQMgRN2NlDz2Qvx7LCFjPIYqzpvqqOO9wUlGoqrTwpxpDqBqM8ZBLPx11Fi9mV8Xs12Pj_pPBX0oAb-UiwFc8MEKhABAQIO";
        String appid = "wxdcf1552054da4e89";
        String color = "red";
        String toUser = "oo3UrtxtrkGHnvfYQbFvpNIjJcjs";
        String templateId = "CTy_ZyDwfUExamtqQepHIEk17DXpcI3jSo8lPaNtwtE";
        MessageTemplate mst = new MessageTemplate(assessToken, appid, color, toUser, templateId, "流量充值未到账");
        mst.setFirstColor("red");
        mst.addKeyWord("2019111416341132231");
        mst.addKeyWord("冯强");
        mst.addKeyWord("流量充值未到账");
        mst.addKeyWord("2019-11-14 16:39");
        System.out.println(mst.getUrl());
        System.out.println(mst.getRequestBody());
    }

}
