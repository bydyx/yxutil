package com.bydyx.yxutil.pay;

import com.bydyx.yxutil.annotation.AnnotationUtil;
import com.bydyx.yxutil.file.XmlUtil;
import com.bydyx.yxutil.pay.wx.*;
import com.bydyx.yxutil.reflex.ClassUtil;
import com.bydyx.yxutil.string.StringUtil;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author bydyx
 * @date 2019/11/12 16:41
 */
public class PayUtil {
    private static Map<String, Merchant> merchantMap = new HashMap<>();

    //注册商户
    public static void registerMerchant(Merchant merchant) {
        merchantMap.put(merchant.getAppid(), merchant);
    }

    public static Merchant getMerchantByAppid(String appid) {
        return merchantMap.get(appid);
    }

    /**
     * 创建一个预下单的参数对象,若要传入特殊参数 可调用
     * <p>
     * 参数详细可看微信文档: https://pay.weixin.qq.com/wiki/doc/api/index.html
     *
     * @param initiatorIp 发起者ip 实际作用不明
     * @param tradeType   交易类型,枚举
     * @param totalFee    支付金额,单位为分
     * @param body        商品信息,用于支付时的展示
     * @param appid       registerMerchant注册时的merchant.appid
     * @param notifyUrl   微信支付成功后的通知地址,要求:
     *                    1.外网可访问的url
     *                    2.无需权限,无参数限制
     *                    3.接收时解析body即可
     * @date 2019/11/13 11:07
     * @author bydyx
     */
    public static PayParam createWxUnifiedOrderParam(String appid, TradeType tradeType, String body, Integer totalFee, String notifyUrl, String initiatorIp) {
        Merchant merchant = merchantMap.get(appid);
        return new UnifiedOrderParam(appid, merchant.getMchid(), body, totalFee, notifyUrl, initiatorIp, merchant.getMchKey(), tradeType);
    }

    public static PayRequest createWxOrderQueryParam(String appid, String orderNo) {
        Merchant merchant = merchantMap.get(appid);
        PayRequest orderQueryParam = new OrderQueryParam(appid, merchant.getMchid(), orderNo);
        return orderQueryParam;
    }

    /**
     * 支付结果解析
     */
    public static PayResult parseResult(String result) {
        PayResult payResult = WxResultNotify.parseXml(result);
        return payResult;
    }

    /**
     * wx 生成签名
     *
     * @param payParam  getParams()的参数
     * @param fieldList getParams()的返回值
     */
    public static String createSign(Object payParam, List<Field> fieldList, String mchKey) {
        return fieldList.stream().map(field -> field.getName() + "=" + ClassUtil.getFieldValue(field, payParam))
                        .reduce((perv, curr) -> perv + "&" + curr)
                        .map(str -> str + "&key=" + mchKey)
                        .map(StringUtil::md5)
                        .orElseThrow(() -> new RuntimeException("生成签名异常,没有传参!"));
    }

    public static String createXmlString(Object payRequest, List<Field> fieldList, String sign) {
        return fieldList.stream()
                        .map(item -> XmlUtil.toXmlLabel(payRequest, item))
                        .reduce((p, r) -> p + r)
                        .map(str -> "<xml>" + str + "<sign>" + sign + "</sign></xml>")
                        .orElseThrow(() -> new RuntimeException("空的参数对象"));
    }


    public static List<Field> getParams(Object payParam) {
        return ClassUtil.getAllField(payParam.getClass())
                        .stream()
                        .filter(item -> ClassUtil.getFieldValue(item, payParam) != null && !AnnotationUtil.fieldHasAnnotation(item, NoSerialize.class))
                        .sorted(Comparator.comparing(Field::getName))
                        .collect(Collectors.toList());
    }

}
