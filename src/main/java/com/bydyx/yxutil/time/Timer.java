package com.bydyx.yxutil.time;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * @author qiang.feng
 * @date 2019/11/6 17:35
 */
public class Timer {
    Instant startTime;

    private Timer() {
    }

    public static Timer start() {
        Timer timer = new Timer();
        timer.startTime = Instant.now();
        return timer;
    }

    public Long end() {
        Instant startTimeInstant = Instant.parse(startTime.toString());
        Instant endTimeInstant = Instant.now();

        Duration between = Duration.between(startTimeInstant, endTimeInstant);
        return between.toMillis();
    }
}
