package com.memoeslink.generator.common;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.regex.Pattern;

public class Validation {
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final String HEX_COLOR_REGEX = "^#([0-9a-f]{3}|[0-9a-f]{6}|[0-9a-f]{8})$";
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile(HEX_COLOR_REGEX);
    private static final String UTF_REGEX = "^U\\+[0-9A-Fa-f]{4,5}$";
    private static final Pattern UTF_PATTERN = Pattern.compile(UTF_REGEX);

    public static boolean isEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isUrl(String url) {
        return UrlValidator.getInstance().isValid(url);
    }

    public static boolean isPhone(String phone) {
        if (StringHelper.isNotNullOrBlank(phone))
            return PHONE_PATTERN.matcher(phone).matches();
        return false;
    }

    public static boolean isHexColor(String color) {
        if (StringHelper.isNotNullOrBlank(color))
            return HEX_COLOR_PATTERN.matcher(color.toLowerCase()).matches();
        return false;
    }

    public static boolean isUtf(String utf) {
        if (StringHelper.isNotNullOrBlank(utf))
            return UTF_PATTERN.matcher(utf).matches();
        return false;
    }
}
