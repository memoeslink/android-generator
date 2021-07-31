package com.memoeslink.generator.common;

import java.util.Locale;

public class AdjectiveGenerator extends Generator {

    public AdjectiveGenerator() {
        super();
    }

    public AdjectiveGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getAdjective(Form form) {
        AdjectiveGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getAdjective();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralAdjective();
            case SINGULAR_MASCULINE:
                return getter.getMaleAdjective();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleAdjective();
            case SINGULAR_FEMININE:
                return getter.getFemaleAdjective();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleAdjective();
            case UNDEFINED:
            default:
                return getAdjective(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public String getAdjectiveWithArticle(Form form) {
        AdjectiveGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getAdjectiveWithArticle();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralAdjectiveWithArticle();
            case SINGULAR_MASCULINE:
                return getter.getMaleAdjectiveWithArticle();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleAdjectiveWithArticle();
            case SINGULAR_FEMININE:
                return getter.getFemaleAdjectiveWithArticle();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleAdjectiveWithArticle();
            case UNDEFINED:
            default:
                return getAdjectiveWithArticle(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public String getAdjectiveWithIndefiniteArticle(Form form) {
        AdjectiveGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getAdjectiveWithIndefiniteArticle();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralAdjectiveWithIndefiniteArticle();
            case SINGULAR_MASCULINE:
                return getter.getMaleAdjectiveWithIndefiniteArticle();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleAdjectiveWithIndefiniteArticle();
            case SINGULAR_FEMININE:
                return getter.getFemaleAdjectiveWithIndefiniteArticle();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleAdjectiveWithIndefiniteArticle();
            case UNDEFINED:
            default:
                return getAdjectiveWithIndefiniteArticle(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public static String getDefaultName() {
        return Constant.DEFAULT_NAME;
    }

    private AdjectiveGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.international.AdjectiveGetter(r);
        else if (locale.getLanguage().equals("en"))
            return new com.memoeslink.generator.english.AdjectiveGetter(r);
        else if (locale.getLanguage().equals("es"))
            return new com.memoeslink.generator.spanish.AdjectiveGetter(r);
        return new com.memoeslink.generator.international.AdjectiveGetter(r);
    }
}
