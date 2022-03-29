package com.memoeslink.generator.common;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.UrlValidator;

public class Validation {

    public static boolean isEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public static boolean isUrl(String url) {
        return UrlValidator.getInstance().isValid(url);
    }

    public static boolean isPhone(String phone) {
        if (StringHelper.isNotNullOrBlank(phone))
            return phone.matches("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
        return false;
    }
}
