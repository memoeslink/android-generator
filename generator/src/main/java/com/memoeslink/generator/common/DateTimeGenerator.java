package com.memoeslink.generator.common;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class DateTimeGenerator extends Generator {
    public static final LocalDate DEFAULT_START_DATE = LocalDate.of(1900, Month.JANUARY, 1);

    public DateTimeGenerator() {
        super();
    }

    public DateTimeGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public LocalDate getDefaultDate() {
        return DEFAULT_START_DATE;
    }

    public LocalDate getDate() {
        return getDate(getDefaultDate());
    }

    public String getStrDate() {
        return getStrDate(getDefaultDate());
    }

    public String getLocalizedDate(FormatStyle format) {
        return getLocalizedDate(getDefaultDate(), format);
    }

    public LocalDate getStartHumanDate() {
        return getDate(LocalDate.now().minus(Period.ofYears(122)).minus(Period.ofDays(164)));
    }

    public LocalDate getHumanDate() {
        return getDate(getStartHumanDate());
    }

    public String getStrHumanDate() {
        return getStrDate(getStartHumanDate());
    }

    public String getLocalizedHumanDate(FormatStyle format) {
        return getLocalizedDate(getStartHumanDate(), format);
    }

    public LocalDate getDate(LocalDate start) {
        start = start != null ? start : DEFAULT_START_DATE;
        long days = ChronoUnit.DAYS.between(start, LocalDate.now());
        return start.plusDays(r.getLong(days + 1));
    }

    public String getStrDate(LocalDate start) {
        return getDate(start).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", locale));
    }

    public String getLocalizedDate(LocalDate start, FormatStyle format) {
        format = format != null ? format : FormatStyle.SHORT;
        return getDate(start).format(DateTimeFormatter.ofLocalizedDate(format).withLocale(locale));
    }

    public LocalTime getTime() {
        return LocalTime.MIN.plusSeconds(r.getLong());
    }

    public String getStrTime() {
        return getTime().format(DateTimeFormatter.ofPattern("HH:mm", locale));
    }

    public String getLocalizedTime(FormatStyle format) {
        format = format != null ? format : FormatStyle.SHORT;
        return getTime().format(DateTimeFormatter.ofLocalizedDate(format).withLocale(locale));
    }
}
