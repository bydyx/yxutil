package com.bydyx.yxutil.time;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = Timer.start();
        Thread.sleep(1000);
        Long end = timer.end();
        System.out.println(end);
    }

    @Test
    public void testStart() throws Exception {
    }

    @Test
    public void testEnd() throws Exception {
    }
}