package com.bydyx.yxutil.time;

import com.bydyx.yxutil.time.entity.TimeUnit;

import java.util.Calendar;
import java.util.Date;

/**
 * @author qiang.feng
 * @date 2019/11/26 9:10
 */
public class TimeRangeUtil {

    /**
     * 判断两个时间是否在同一个时间单位片段内
     * 例如:
     * startTime = 2019/11/26 9:57 ; checkTime = 2019/11/26 8:57 ;timeUnit:HOUR =>false
     * startTime = 2019/11/26 9:57 ; checkTime = 2019/11/26 9:50 ;timeUnit:HOUR =>true
     *
     * @date 2019/11/26 9:57
     * @author qiang.feng
     */
    public static boolean inRange(Date startTime, Date checkTime, TimeUnit timeUnit) {
        int start = TimeRangeUtil.get(startTime, timeUnit);
        int check = TimeRangeUtil.get(checkTime, timeUnit);
        return start == check;
    }

    /**
     * 返回当前时间对应单位的值
     * 例如 date = " 2019/11/26 9:54 ",timeUnit = HOUR -> 9
     *
     * @date 2019/11/26 9:54
     * @author qiang.feng
     */
    public static int get(Date date, TimeUnit timeUnit) {
        Calendar calendar = Calendar.getInstance();
        if (timeUnit.equals(TimeUnit.WEEK)) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
        }
        calendar.setTime(date);
        return calendar.get(timeUnit.getValue());
    }
}