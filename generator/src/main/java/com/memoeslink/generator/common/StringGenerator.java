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

    public String getPercentage(boolean decimal) {
        float number = r.getFloatInRange(0.0F, 100.0F);

        if (decimal)
            return String.format(locale, "%.2f%%", number);
        return Math.round(number) + "%";
    }

    public String getAlphanumeric(int length, boolean uppercaseAllowed) {
        length = IntegerHelper.defaultByRange(length, 0, 9999);
        StringBuilder sb = new StringBuilder();
        String letters = com.memoeslink.generator.base.Constant.LOWERCASE_ALPHABET;

        if (uppercaseAllowed)
            letters = com.memoeslink.generator.base.Constant.LETTERS;

        for (int n = 0; n < length; n++) {
            if (r.getBoolean())
                sb.append(ResourceGetter.with(r).getChar(letters));
            else
                sb.append(r.getInt(10));
        }
        return sb.toString();
    }

    public String getPassword(int length) {
        length = IntegerHelper.defaultByRange(length, 0, 100);
        StringBuilder sb = new StringBuilder();

        for (int n = 0; n < length; n++) {
            sb.append(ResourceGetter.with(r).getChar(Constant.PASSWORD_CHARS));
        }
        return sb.toString();
    }

    public String getHexColor() {
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }
}
