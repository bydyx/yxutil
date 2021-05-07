package com.bydyx.date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * 快速修改时间
 *
 * @author q.f
 * @date 2020/8/1 13:53
 */
@AllArgsConstructor
public class DateBuilder {
    @Getter
    Date date;

    public static DateBuilder builder() {
        return builder(new Date());
    }

    public static DateBuilder builder(Date date) {
        return new DateBuilder(date);
    }

    public DateBuilder addDays(int day) {
        date = DateUtils.addDays(date, day);
        return this;
    }

    public DateBuilder addHours(int hour) {
        date = DateUtils.addHours(date, hour);
        return this;
    }

    public DateBuilder addMinutes(int minutes) {
        date = DateUtils.addMinutes(date, minutes);
        return this;
    }

    public DateBuilder addSeconds(int seconds) {
        date = DateUtils.addSeconds(date, seconds);
        return this;
    }

    public DateBuilder setDays(int day) {
        date = DateUtils.setDays(date, day);
        return this;
    }

    public DateBuilder setHours(int hour) {
        date = DateUtils.setHours(date, hour);
        return this;
    }

    public DateBuilder setMinutes(int minutes) {
        date = DateUtils.setMinutes(date, minutes);
        return this;
    }

    public DateBuilder setSeconds(int seconds) {
        date = DateUtils.setSeconds(date, seconds);
        return this;
    }

    public DateBuilder clearHourMinSecond() {
        return this.setHours(0)
                   .setMinutes(0)
                   .setSeconds(0);
    }

}