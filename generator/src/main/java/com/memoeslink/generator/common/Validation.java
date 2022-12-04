package com.memoeslink.generator.common;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

import java.util.regex.Pattern;

public class Validation {
    private static final String PHONE_REGEX = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    private static final String HEX_COLOR_REGEX = "^((a|b|big|i|s|small|tt|u),)*(a|b|big|i|s|small|tt|u)$";
    private static final Pattern HEX_COLOR_PATTERN = Pattern.compile(HEX_COLOR_REGEX);

    public static boolean isEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isUrl(String url) {
        return UrlValidator.getInstance().isValid(url);
    }

    public static boolean isPhone(String phone) {
        if (StringHelper.isNotNullOrBlank(phone))
            return phone.matches(PHONE_REGEX);
        return false;
    }

    public static boolean isHexColor(String color) {
        if (StringHelper.isNotNullOrBlank(color))
            return color.matches(HEX_COLOR_REGEX);
        return false;
    }
}
