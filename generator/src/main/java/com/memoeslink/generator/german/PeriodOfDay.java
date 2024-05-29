package com.memoeslink.generator.german;

import org.memoeslink.IntegerHelper;

import java.time.LocalTime;

public enum PeriodOfDay {
    MORNING(LocalTime.of(0, 0, 0)),
    AFTERNOON(LocalTime.of(12, 0, 0)),
    EVENING(LocalTime.of(18, 0, 0)),
    NIGHT(LocalTime.of(22, 0, 0));

    private final LocalTime startTime;

    private PeriodOfDay(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
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
