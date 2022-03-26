package com.memoeslink.generator.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateTimeHelper {

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public static long getDifferenceInDays(String firstDate, String secondDate) {
        LocalDate initialDate = LocalDate.parse(firstDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate finalDate = LocalDate.parse(secondDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return ChronoUnit.DAYS.between(initialDate, finalDate);
    }

    public static Period getTimeBetweenDates(String firstDate, String secondDate) {
        LocalDate initialDate = LocalDate.parse(firstDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate finalDate = LocalDate.parse(secondDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return Period.between(initialDate, finalDate);
    }

    public static String getStrDateMinusDays(String strDate, int days) {
        if (StringHelper.isNullOrBlank(strDate))
            strDate = LocalDate.now().toString();
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).minusDays(days).toString();
    }

    public static String getStrDatePlusDays(String strDate, int days) {
        if (StringHelper.isNullOrBlank(strDate))
            strDate = LocalDate.now().toString();
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).plusDays(days).toString();
    }

    public static String toIso8601(int year, int month, int day) {
        return toIso8601(LocalDate.of(year, month, day));
    }

    public static String toIso8601(LocalDate date) {
        if (date == null)
            return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public static LocalDate toLocalDate(Calendar date) {
        if (date == null)
            return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}
