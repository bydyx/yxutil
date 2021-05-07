package com.bydyx.random;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

/**
 * @date 2020/8/31 13:03
 */
@Data
@Accessors(chain = true)
public class NumberPoolConfig {
    Integer size = 30;
    Integer warningValue = 19;
    String startNum = "10";
    String currentNum = startNum;

    public String createNextNum() {
        BigInteger nextNum = new BigInteger(currentNum);
        currentNum = nextNum.add(BigInteger.ONE).toString();
        return currentNum;
    }
}