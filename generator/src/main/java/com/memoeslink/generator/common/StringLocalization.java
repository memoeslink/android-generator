package com.memoeslink.generator.common;

import java.util.Locale;
import java.util.ResourceBundle;

public class StringLocalization {
    private final ResourceBundle bundle;

    public StringLocalization(Locale locale) {
        if (locale == null)
            locale = Locale.ENGLISH;
        else if (locale.getLanguage().equals("en"))
            locale = Locale.ENGLISH;
        else if (locale.getLanguage().equals("es"))
            locale = new Locale("es");
        else
            locale = Locale.ENGLISH;
        bundle = ResourceBundle.getBundle("strings", locale);
    }

    public String getString(String key) {
        if (bundle.containsKey(key))
            return bundle.getString(key);
        return Database.DEFAULT_VALUE;
    }
}
