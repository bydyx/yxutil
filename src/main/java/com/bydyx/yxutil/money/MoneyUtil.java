package com.bydyx.yxutil.money;


import java.math.BigDecimal;

/**
 * @author qiang.feng
 * @date 2019/12/24 14:01
 */
public class MoneyUtil {
    /**
     * 金额单位转换
     *
     * @param originalValue 原始金额
     * @param originalUnit  原始单位
     * @param toUnit        转换后的单位
     */
    public static Double moneyUnitConverter(double originalValue, MoneyUnit originalUnit, MoneyUnit toUnit) {
        double disparity = originalUnit.getDisparity(toUnit);
        BigDecimal bigDecimal = BigDecimal.valueOf(originalValue);
        BigDecimal disparityBigDecimal = BigDecimal.valueOf(disparity);
        BigDecimal multiply = bigDecimal.multiply(disparityBigDecimal);
        return multiply.doubleValue();
    }
}