package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

public class DateTimeGetter {
    private static Locale locale;
    private static Randomizer r;

    static {
        locale = Locale.getDefault();
        r = new Randomizer();
    }

    private DateTimeGetter(Randomizer r) {
        this(null, r);
    }

    private DateTimeGetter(Locale locale, Randomizer r) {
        DateTimeGetter.locale = locale == null ? Locale.getDefault() : locale;

        validation:
        {
            if (r == null || DateTimeGetter.r == null) {
                DateTimeGetter.r = r != null ? r : new Randomizer();
                break validation;
            }

            if (r.getSeed() == null && DateTimeGetter.r.getSeed() == null)
                break validation;

            if (r.getSeed() == null ^ DateTimeGetter.r.getSeed() == null) {
                DateTimeGetter.r = new Randomizer(r.getSeed());
                break validation;
            }

            if (!r.getSeed().equals(DateTimeGetter.r.getSeed()))
                DateTimeGetter.r = r;
        }
    }

    public String getSimpleCurrentDateTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).withLocale(locale));
    }

    public String getCurrentDateTime() {
        return getCurrentDateTime(r.getIntInRange(1, 6));
    }

    public String getCurrentDateTime(int type) {
        type = IntegerHelper.defaultByRange(type, 1, 6);

        return switch (type) {
            case 1 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(locale));
            case 2 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME.withLocale(locale));
            case 3 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale));
            case 4 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale));
            case 5 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale));
            case 6 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(locale));
            default -> "";
        };
    }

    public String getSimpleCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(locale));
    }

    public String getCurrentDate() {
        return getCurrentDate(r.getIntInRange(1, 14));
    }

    public String getCurrentDate(int type) {
        type = IntegerHelper.defaultByRange(type, 1, 14);

        return switch (type) {
            case 1 -> LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", locale));
            case 2 -> LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd", locale));
            case 3 -> LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy", locale));
            case 4 -> LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy", locale));
            case 5 -> LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", locale));
            case 6 -> LocalDate.now().format(DateTimeFormatter.ofPattern("E, MMM dd yyyy", locale));
            case 7 -> LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy", locale));
            case 8 -> LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d", locale));
            case 9 ->
                    LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE.withLocale(locale));
            case 10 -> LocalDateTime.now().format(DateTimeFormatter.ISO_DATE.withLocale(locale));
            case 11 ->
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(locale));
            case 12 ->
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale));
            case 13 ->
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
            case 14 ->
                    LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(locale));
            default -> "";
        };
    }

    public String getCurrentDayOfWeek() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, locale);
    }

    public String getNameOfDayOfWeek() {
        return android.text.format.DateFormat.format("EEEE", new Date()).toString();
    }

    public String getSimpleCurrentTime() {
        return ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss").withLocale(locale));
    }

    public String getCurrentTime() {
        return getCurrentTime(r.getIntInRange(1, 11));
    }

    public String getCurrentTime(int type) {
        type = IntegerHelper.defaultByRange(type, 1, 11);

        return switch (type) {
            case 1 -> ZonedDateTime.now().format(DateTimeFormatter.ofPattern("h:mm a", locale));
            case 2 -> ZonedDateTime.now().format(DateTimeFormatter.ofPattern("K:mm a, z", locale));
            case 3 -> ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm", locale));
            case 4 -> ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss Z", locale));
            case 5 -> ZonedDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss", locale));
            case 6 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME.withLocale(locale));
            case 7 -> ZonedDateTime.now().format(DateTimeFormatter.ISO_TIME.withLocale(locale));
            case 8 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).withLocale(locale));
            case 9 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).withLocale(locale));
            case 10 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).withLocale(locale));
            case 11 ->
                    ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(locale));
            default -> "";
        };
    }

    public static DateTimeGetter with(Randomizer r) {
        return new DateTimeGetter(null, r);
    }

    public static DateTimeGetter with(Locale locale, Randomizer r) {
        return new DateTimeGetter(locale, r);
    }

    public static DateTimeGetter without() {
        return with(null, null);
    }
}
