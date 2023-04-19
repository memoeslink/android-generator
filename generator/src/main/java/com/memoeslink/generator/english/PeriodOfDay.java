package com.memoeslink.generator.english;

import com.memoeslink.generator.common.IntegerHelper;

import java.time.LocalTime;

public enum PeriodOfDay {
    MORNING(LocalTime.of(5, 0, 0), "Good morning"),
    AFTERNOON(LocalTime.of(12, 0, 0), "Good afternoon"),
    EVENING(LocalTime.of(18, 0, 0), "Good evening"),
    NIGHT(LocalTime.of(21, 0, 0), "Good night");

    private final LocalTime startTime;
    private final String greeting;

    private PeriodOfDay(LocalTime startTime, String greeting) {
        this.startTime = startTime;
        this.greeting = greeting;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public String getGreeting() {
        return greeting;
    }

    public static PeriodOfDay get(int hourOfDay) {
        hourOfDay = IntegerHelper.defaultByRange(hourOfDay, 0, 23);
        return get(LocalTime.of(hourOfDay, 0, 0));
    }

    public static PeriodOfDay get(LocalTime time) {
        if (time == null)
            return MORNING;

        if (between(time, MORNING.startTime, AFTERNOON.startTime))
            return MORNING;
        else if (between(time, AFTERNOON.startTime, EVENING.startTime))
            return AFTERNOON;
        else if (between(time, EVENING.startTime, NIGHT.startTime))
            return EVENING;
        return NIGHT;
    }

    private static boolean between(LocalTime time, LocalTime start, LocalTime end) {
        if (time == null) return false;
        return (!time.isBefore(start)) && time.isBefore(end);
    }
}
