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

        return switch (form) {
            case SINGULAR, SINGULAR_NEUTER -> getter.getNoun();
            case PLURAL, PLURAL_NEUTER -> getter.getPluralNoun();
            case SINGULAR_MASCULINE -> getter.getMaleNoun();
            case PLURAL_MASCULINE -> getter.getPluralMaleNoun();
            case SINGULAR_FEMININE -> getter.getFemaleNoun();
            case PLURAL_FEMININE -> getter.getPluralFemaleNoun();
            default -> getNoun(r.getElement(Form.values()));
        };
    }

    public String getNounWithArticle() {
        return r.getBoolean() ? getNounWithArticle(Form.UNDEFINED) :
                getNounWithIndefiniteArticle(Form.UNDEFINED);
    }

    public String getNounWithArticle(Form form) {
        NounGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        return switch (form) {
            case SINGULAR, SINGULAR_NEUTER -> getter.getNounWithArticle();
            case PLURAL, PLURAL_NEUTER -> getter.getPluralNounWithArticle();
            case SINGULAR_MASCULINE -> getter.getMaleNounWithArticle();
            case PLURAL_MASCULINE -> getter.getPluralMaleNounWithArticle();
            case SINGULAR_FEMININE -> getter.getFemaleNounWithArticle();
            case PLURAL_FEMININE -> getter.getPluralFemaleNounWithArticle();
            default -> getNounWithArticle(r.getElement(Form.values()));
        };
    }

    public String getNounWithIndefiniteArticle(Form form) {
        NounGetter getter = getGetter();
        form = form != null ? form : Form.UNDEFINED;

        return switch (form) {
            case SINGULAR, SINGULAR_NEUTER -> getter.getNounWithIndefiniteArticle();
            case PLURAL, PLURAL_NEUTER -> getter.getPluralNounWithIndefiniteArticle();
            case SINGULAR_MASCULINE -> getter.getMaleNounWithIndefiniteArticle();
            case PLURAL_MASCULINE -> getter.getPluralMaleNounWithIndefiniteArticle();
            case SINGULAR_FEMININE -> getter.getFemaleNounWithIndefiniteArticle();
            case PLURAL_FEMININE -> getter.getPluralFemaleNounWithIndefiniteArticle();
            default -> getNounWithIndefiniteArticle(r.getElement(Form.values()));
        };
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
