package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.IntegerHelper;

import java.time.LocalTime;

public enum PeriodOfDay {
    MORNING(LocalTime.of(5, 0, 0), "Buen día"),
    AFTERNOON(LocalTime.of(12, 0, 0), "Buena tarde"),
    NIGHT(LocalTime.of(21, 0, 0), "Buena noche");

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
        else if (between(time, AFTERNOON.startTime, NIGHT.startTime))
            return AFTERNOON;
        return NIGHT;
    }

    private static boolean between(LocalTime time, LocalTime start, LocalTime end) {
        if (time == null) return false;
        return (!time.isBefore(start)) && time.isBefore(end);
    }
}
