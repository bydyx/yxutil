package com.bydyx.yxutil.money;


import org.junit.Test;

public class MoneyUtilTest {

    @Test
    public void testMoneyUnitConverter() {
        Double originalValue = 12123.00;
        Double aDouble = MoneyUtil.moneyUnitConverter(originalValue, MoneyUnit.yuan, MoneyUnit.fen);
        System.out.println(aDouble);
    }

} 
