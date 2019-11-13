package com.bydyx.yxutil.pay.wx;

public enum TradeType {
    APP("APP"),
    MWEB("MWEB"),
    NATIVE("NATIVE"),
    JSAPI("JSAPI");
    String tradeType;

    TradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public String toString() {
        return tradeType;
    }
}