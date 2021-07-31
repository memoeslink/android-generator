package com.memoeslink.generator.common;

import java.util.Locale;

public class NounGenerator extends Generator {

    public NounGenerator() {
        super();
    }

    public NounGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getNoun(Form form) {
        NounGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getNoun();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralNoun();
            case SINGULAR_MASCULINE:
                return getter.getMaleNoun();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleNoun();
            case SINGULAR_FEMININE:
                return getter.getFemaleNoun();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleNoun();
            case UNDEFINED:
            default:
                return getNoun(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public String getNounWithArticle(Form form) {
        NounGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getNounWithArticle();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralNounWithArticle();
            case SINGULAR_MASCULINE:
                return getter.getMaleNounWithArticle();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleNounWithArticle();
            case SINGULAR_FEMININE:
                return getter.getFemaleNounWithArticle();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleNounWithArticle();
            case UNDEFINED:
            default:
                return getNounWithArticle(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public String getNounWithIndefiniteArticle(Form form) {
        NounGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        switch (form) {
            case SINGULAR:
            case SINGULAR_NEUTER:
                return getter.getNounWithIndefiniteArticle();
            case PLURAL:
            case PLURAL_NEUTER:
                return getter.getPluralNounWithIndefiniteArticle();
            case SINGULAR_MASCULINE:
                return getter.getMaleNounWithIndefiniteArticle();
            case PLURAL_MASCULINE:
                return getter.getPluralMaleNounWithIndefiniteArticle();
            case SINGULAR_FEMININE:
                return getter.getFemaleNounWithIndefiniteArticle();
            case PLURAL_FEMININE:
                return getter.getPluralFemaleNounWithIndefiniteArticle();
            case UNDEFINED:
            default:
                return getNounWithIndefiniteArticle(Form.values()[r.getInt(Form.values().length)]);
        }
    }

    public static String getDefaultName() {
        return Constant.DEFAULT_NAME;
    }

    private NounGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.international.NounGetter(r);
        else if (locale.getLanguage().equals("en"))
            return new com.memoeslink.generator.english.NounGetter(r);
        else if (locale.getLanguage().equals("es"))
            return new com.memoeslink.generator.spanish.NounGetter(r);
        return new com.memoeslink.generator.international.NounGetter(r);
    }
}
