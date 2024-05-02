package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import org.memoeslink.StringHelper;
import org.memoeslink.Validation;

import java.util.Locale;

public class NameGetterFactory {

    private NameGetterFactory() {
    }

    public static NameGetter getNameGetter(String locale) {
        return getNameGetter(locale, null);
    }

    public static NameGetter getNameGetter(String locale, Randomizer r) {
        if (!Validation.isLocale(locale))
            return new com.memoeslink.generator.international.NameGetter(r);
        locale = StringHelper.replace(locale, "_", "-");

        if (!StringHelper.equalsAny(locale, Constant.SUPPORTED_LOCALES))
            return new com.memoeslink.generator.international.NameGetter(r);
        return getNameGetter(Locale.forLanguageTag(locale), r);
    }

    public static NameGetter getNameGetter(Locale locale) {
        return getNameGetter(locale, null);
    }

    public static NameGetter getNameGetter(Locale locale, Randomizer r) {
        if (locale == null || StringHelper.isNullOrBlank(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.international.NameGetter(r);

        return switch (locale.getLanguage()) {
            case "ar" -> new com.memoeslink.generator.arabic.NameGetter(r);
            case "de" -> new com.memoeslink.generator.german.NameGetter(r);
            case "en" -> new com.memoeslink.generator.english.NameGetter(r);
            case "es" ->
                    locale.getCountry().equals("MX") ? new com.memoeslink.generator.spanish.mexico.NameGetter(r) :
                            new com.memoeslink.generator.spanish.NameGetter(r);
            case "fr" -> new com.memoeslink.generator.french.NameGetter(r);
            case "it" -> new com.memoeslink.generator.italian.NameGetter(r);
            case "hi" -> new com.memoeslink.generator.hindi.NameGetter(r);
            case "ja" -> new com.memoeslink.generator.japanese.NameGetter(r);
            case "pt" -> new com.memoeslink.generator.portuguese.NameGetter(r);
            case "ru" -> new com.memoeslink.generator.russian.NameGetter(r);
            default -> new com.memoeslink.generator.international.NameGetter(r);
        };
    }
}
