package com.bydyx.yxutil.money;

/**
 * @author qiang.feng
 * @date 2019/12/24 14:03
 */
public enum MoneyUnit {
    fen(1),
    jiao(2),
    yuan(3);

    Integer value;

    MoneyUnit(Integer value) {
        this.value = value;
    }


    public double getDisparity(MoneyUnit toUnit) {
        int offSet = this.value - toUnit.value;
        double pow = Math.pow(10,offSet);
        return pow;
    }
}