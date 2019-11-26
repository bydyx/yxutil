package com.bydyx.yxutil.time.entity;

import java.util.Calendar;

/**
 * @author bydyx
 * @date 2019/11/5 17:13
 */
public enum TimeUnit {
    SECOND(Calendar.SECOND),
    MINUTE(Calendar.MINUTE),
    HOUR(Calendar.HOUR_OF_DAY),
    DATE(Calendar.DATE),
    WEEK(Calendar.WEEK_OF_YEAR),
    MONTH(Calendar.MONTH),
    YEAR(Calendar.YEAR),
    ;

    TimeUnit(Integer value) {
        this.value = value;
    }

    Integer value;

    public Integer getValue() {
        return value;
    }

    public long getTimeStampByTimeUnit() {
        switch (this) {
            case YEAR:
                return 365 * TimeUnit.DATE.getTimeStampByTimeUnit();
            case MONTH:
                return 30 * TimeUnit.DATE.getTimeStampByTimeUnit();
            case WEEK:
                return 7 * TimeUnit.DATE.getTimeStampByTimeUnit();
            case DATE:
                return 24 * TimeUnit.HOUR.getTimeStampByTimeUnit();
            case HOUR:
                return 60 * TimeUnit.MINUTE.getTimeStampByTimeUnit();
            case MINUTE:
                return 60 * TimeUnit.SECOND.getTimeStampByTimeUnit();
            case SECOND:
                return 1000;
        }
        throw new RuntimeException("无单位!");
    }
}
