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
        int percentage = r.getInt(0, 101);
        int decimals = r.getInt(0, 100);
        return percentage == 100 ? "100.00%" : (percentage + "." + String.format(locale, "%02d", decimals) + "%");
    }

    public String getStrColor(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return String.format("#%06X", (0xFFFFFF & -7829368));
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }
}
