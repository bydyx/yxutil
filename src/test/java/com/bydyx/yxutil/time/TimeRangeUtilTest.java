package com.bydyx.yxutil.time;

import org.junit.Test;

import java.util.Date;

public class TimeRangeUtilTest {

    @Test
    public void testInRangeByDay() throws Exception {
        Date date = TimeUtil.parseDateStr("2019-11-28 11:23:22");
        Date date2 = TimeUtil.parseDateStr("2019-11-28 23:23:22");

        boolean b = TimeRangeUtil.inRangeByDay(date, date2);
        System.out.println(b);
    }
}