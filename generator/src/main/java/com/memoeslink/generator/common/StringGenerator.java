package com.memoeslink.generator.common;

import org.memoeslink.IntegerHelper;

import java.util.Locale;

public class StringGenerator extends Generator {

    public StringGenerator() {
        super();
    }

    public StringGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getPercentage() {
        return r.getInt(101) + "%";
    }

    public String getDecimalPercentage() {
        return String.format(locale, "%.2f%%", r.getFloatInRange(0.0F, 100.0F));
    }

    public String getHexColor() {
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }

    public String getPassword(int length) {
        length = IntegerHelper.defaultByRange(length, 0, 100);
        StringBuilder sb = new StringBuilder();

        for (int n = 0; n < length; n++) {
            sb.append(ResourceGetter.with(r).getChar(Constant.PASSWORD_CHARS));
        }
        return sb.toString();
    }
}
