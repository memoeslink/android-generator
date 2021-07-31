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
        return percentage == 100 ? "100.00%" : (percentage + String.format("%02d", decimals) + "%");
    }
}
