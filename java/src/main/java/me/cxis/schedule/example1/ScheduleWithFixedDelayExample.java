package me.cxis.schedule.example1;

import me.cxis.schedule.NoneDaemonThreadFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleWithFixedDelayExample {

    private static ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(
        1,
        new NoneDaemonThreadFactory("testFixedDelay")
    );

    public static void main(String[] args) {
        scheduledExecutorService.scheduleWithFixedDelay(new TestTask1(), 0L, 3, TimeUnit.SECONDS);
    }
}
