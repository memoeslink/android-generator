package com.memoeslink.generator.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeHelper {

    public static String getStrCurrentDateTime() {
        return LocalDateTime.now().toString();
    }

    public static String getStrCurrentTime(int type) {
        type = IntegerHelper.defaultInt(type, 1, 11);

        switch (type) {
            case 1:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a", Locale.getDefault()));
            case 2:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("K:mm a, z", Locale.getDefault()));
            case 3:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault()));
            case 4:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss Z", Locale.getDefault()));
            case 5:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault()));
            case 6:
                return ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
            case 7:
                return ZonedDateTime.now().format(DateTimeFormatter.ISO_TIME);
            case 8:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL));
            case 9:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG));
            case 10:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));
            case 11:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            default:
                return "";
        }
    }

    public static String getStrCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public static String getStrCurrentDate(int type) {
        type = IntegerHelper.defaultInt(type, 1, 13);

        switch (type) {
            case 1:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault()));
            case 2:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()));
            case 3:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.getDefault()));
            case 4:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.getDefault()));
            case 5:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy", Locale.getDefault()));
            case 6:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.getDefault()));
            case 7:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d", Locale.getDefault()));
            case 8:
                return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);
            case 9:
                return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE);
            case 10:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
            case 11:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
            case 12:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
            case 13:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            default:
                return "";
        }
    }

    public static String getStrCurrentDayOfWeek() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public static String getStrDate(int year, int month, int day) {
        return LocalDate.of(year, month, day).format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault())
        );
    }

    public static String getStrDate(LocalDate date) {
        if (date == null)
            return null;
        return date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.getDefault()));
    }

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

    public static String getStrDatePlusDays(String strDate, int days) {
        if (strDate == null)
            strDate = getStrCurrentDate();
        return LocalDate.parse(strDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).plusDays(days).toString();
    }

    public static LocalDate toLocalDate(Calendar date) {
        if (date == null)
            return null;
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}
