package com.bydyx.date.test;

import com.bydyx.date.DateBuilder;

import java.util.Date;

/**
 * @author q.f
 * @date 2020/8/3 11:26
 */
public class DateBuilderTest {
    public static void main(String[] args) {
        // 2020-08-03 11:26:44
        long time = 1596425204000L;
        Date date = new Date(time);

        Date date1 = DateBuilder.builder(date)
                                .clearHourMinSecond()
                                .getDate();
        System.out.println(date1);
    }
}
