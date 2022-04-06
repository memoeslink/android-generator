package com.memoeslink.generator.common;

import java.util.Locale;

public class StringGenerator extends Generator {

    public StringGenerator() {
        super();
    }

    public StringGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getPercentage() {
        return r.getInt(0, 101) + "%";
    }

    public String getDecimalPercentage() {
        return String.format(locale, "%.2f%%", r.getFloat(0.0F, 100.0F));
    }

    public String getStrColor(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return String.format("#%06x", (0xFFFFFF & -7829368));
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }
}
