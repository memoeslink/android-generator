package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import java.util.Locale;

public class Generator {
    protected Locale locale;
    protected Randomizer r;

    public Generator() {
        this(null, null);
    }

    public Generator(Long seed) {
        this(null, seed);
    }

    public Generator(Locale locale, Long seed) {
        this.locale = locale != null ? locale : new Locale("xx");
        this.r = new Randomizer(seed);
    }

    public Locale getLocale() {
        return locale;
    }

    public Randomizer getRandomizer() {
        return r;
    }

    public String getDefaultValue() {
        return Database.DEFAULT_VALUE;
    }
}
