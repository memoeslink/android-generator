package com.memoeslink.generator.spanish;

import org.memoeslink.StringHelper;

import java.util.regex.Pattern;

public class TextProcessor extends com.memoeslink.generator.common.TextProcessor {
    private static final String NAME_START_REGEX = "^[dD]e(l|\\s(la(s)?|lo(s)?))?\\s+";
    private static final Pattern NAME_START_PATTERN = Pattern.compile(NAME_START_REGEX);

    public static String removeNameStart(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return s;
        return NAME_START_PATTERN.matcher(s).replaceFirst(StringHelper.EMPTY);
    }
}
