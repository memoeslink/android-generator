package com.memoeslink.generator.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateTimeGetter {
    private static Locale locale;

    static {
        locale = Locale.getDefault();
    }

    private DateTimeGetter(Locale locale) {
        DateTimeGetter.locale = locale == null ? Locale.getDefault() : locale;
    }

    public static String getCurrentDateTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).withLocale(locale));
    }

    public static String getCurrentDateTime(int type) {
        type = IntegerHelper.defaultInt(type, 1, 4);

        switch (type) {
            case 1:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale));
            case 2:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale));
            case 3:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
            case 4:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(locale));
            default:
                return "";
        }
    }

    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd").withLocale(locale));
    }

    public static String getCurrentDate(int type) {
        type = IntegerHelper.defaultInt(type, 1, 13);

        switch (type) {
            case 1:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd", locale));
            case 2:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy", locale));
            case 3:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy", locale));
            case 4:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", locale));
            case 5:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy", locale));
            case 6:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", locale));
            case 7:
                return LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d", locale));
            case 8:
                return LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE.withLocale(locale));
            case 9:
                return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE.withLocale(locale));
            case 10:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale));
            case 11:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale));
            case 12:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
            case 13:
                return LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale));
            default:
                return "";
        }
    }

    public static String getCurrentDayOfWeek() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, locale);
    }

    public static String getCurrentTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(locale));
    }

    public static String getCurrentTime(int type) {
        type = IntegerHelper.defaultInt(type, 1, 11);

        switch (type) {
            case 1:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a", locale));
            case 2:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("K:mm a, z", locale));
            case 3:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm", locale));
            case 4:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss Z", locale));
            case 5:
                return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", locale));
            case 6:
                return ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME.withLocale(locale));
            case 7:
                return ZonedDateTime.now().format(DateTimeFormatter.ISO_TIME.withLocale(locale));
            case 8:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).withLocale(locale));
            case 9:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).withLocale(locale));
            case 10:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(locale));
            case 11:
                return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale));
            default:
                return "";
        }
    }

    public static DateTimeGetter with(Locale locale) {
        return new DateTimeGetter(locale);
    }

    public static DateTimeGetter without() {
        return with(null);
    }
}
