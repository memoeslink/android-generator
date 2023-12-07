package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

public class NameGetterFactory {

    private NameGetterFactory() {
    }

    public static NameGetter getNameGetter(String locale) {
        if (StringHelper.isNullOrBlank(locale))
            return new com.memoeslink.generator.common.NameGetter();

        return switch (locale) {
            case "ar" -> new com.memoeslink.generator.arabic.NameGetter();
            case "de" -> new com.memoeslink.generator.german.NameGetter();
            case "en" -> new com.memoeslink.generator.english.NameGetter();
            case "es" -> new com.memoeslink.generator.spanish.NameGetter();
            case "es_MX" -> new com.memoeslink.generator.spanish.mexico.NameGetter();
            case "fr" -> new com.memoeslink.generator.french.NameGetter();
            case "it" -> new com.memoeslink.generator.italian.NameGetter();
            case "hi" -> new com.memoeslink.generator.hindi.NameGetter();
            case "ja" -> new com.memoeslink.generator.japanese.NameGetter();
            case "pt" -> new com.memoeslink.generator.portuguese.NameGetter();
            case "ru" -> new com.memoeslink.generator.russian.NameGetter();
            default -> new com.memoeslink.generator.common.NameGetter();
        };
    }
}
