package com.memoeslink.generator.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateTimeHelper {
    public static final String ISO_8601_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String ISO_8601_DATE_PATTERN = "yyyy-MM-dd";
    public static final String ISO_8601_TIME_PATTERN = "HH:mm:ssZ";

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static String getStrCurrentDateTime() {
        return toIso8601DateTime(getCurrentDateTime());
    }

    public static String getStrCurrentDate() {
        return toIso8601Date(getCurrentDate());
    }

    public static String getStrCurrentTime() {
        return toIso8601Time(getCurrentTime());
    }

    public static long getDifferenceInDays(String firstDate, String secondDate) {
        LocalDate initialDate = LocalDate.parse(firstDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN));
        LocalDate finalDate = LocalDate.parse(secondDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN));
        return ChronoUnit.DAYS.between(initialDate, finalDate);
    }

    public static Period getTimeBetweenDates(String firstDate, String secondDate) {
        LocalDate initialDate = LocalDate.parse(firstDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN));
        LocalDate finalDate = LocalDate.parse(secondDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN));
        return Period.between(initialDate, finalDate);
    }

    public static String getStrDateMinusDays(String strDate, int days) {
        if (StringHelper.isNullOrBlank(strDate))
            return strDate;
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN)).minusDays(days).toString();
    }

    public static String getStrDatePlusDays(String strDate, int days) {
        if (StringHelper.isNullOrBlank(strDate))
            return strDate;
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN)).plusDays(days).toString();
    }

    public static String toIso8601DateTime(int year, int month, int day, int hour, int minute, int second) {
        return toIso8601DateTime(LocalDateTime.of(year, month, day, hour, minute, second));
    }

    public static String toIso8601DateTime(LocalDateTime dateTime) {
        if (dateTime == null)
            return null;
        return dateTime.atZone(ZoneId.of("UTC"))
                .format(DateTimeFormatter.ofPattern(ISO_8601_DATE_TIME_PATTERN));
    }

    public static String toIso8601Date(int year, int month, int day) {
        return toIso8601Date(LocalDate.of(year, month, day));
    }

    public static String toIso8601Date(LocalDate date) {
        if (date == null)
            return null;
        return date.format(DateTimeFormatter.ofPattern(ISO_8601_DATE_PATTERN));
    }

    public static String toIso8601Time(int hour, int minute, int second) {
        return toIso8601Time(LocalTime.of(hour, minute, second));
    }

    public static String toIso8601Time(LocalTime time) {
        if (time == null)
            return null;
        return ZonedDateTime.now(ZoneId.of("UTC"))
                .with(time)
                .withZoneSameInstant(ZoneOffset.UTC)
                .format(DateTimeFormatter.ofPattern(ISO_8601_TIME_PATTERN));
    }

    public static LocalDate toLocalDate(Calendar date) {
        if (date == null)
            return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}