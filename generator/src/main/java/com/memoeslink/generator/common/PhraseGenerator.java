package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.util.Locale;

public class PhraseGenerator extends Generator {

    public PhraseGenerator() {
        super();
    }

    public PhraseGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getPhrase(PhraseType phraseType) {
        PhraseGetter getter = getGetter();
        phraseType = phraseType != null ? phraseType : PhraseType.ANY;

        return switch (phraseType) {
            case AGREEMENT -> getter.getAgreement();
            case APOLOGY -> getter.getApology();
            case APPRECIATION -> getter.getAppreciation();
            case CONGRATULATION -> getter.getCongratulation();
            case DISAGREEMENT -> getter.getDisagreement();
            case DOUBT -> getter.getDoubt();
            case FAREWELL -> getter.getFarewell();
            case GREETING -> getter.getGreeting();
            case INITIATION_QUESTION -> getter.getInitiationQuestion();
            case INQUIRY_QUESTION -> getter.getInquiryQuestion();
            case SHORT_ANSWER -> getter.getShortAnswer();
            case WELCOME -> getter.getWelcome();
            default -> getPhrase(r.getElement(PhraseType.values()));
        };
    }

    private PhraseGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.common.PhraseGetter(r);
        else if (locale.getLanguage().equals("en"))
            return new com.memoeslink.generator.english.PhraseGetter(r);
        else if (locale.getLanguage().equals("es"))
            return new com.memoeslink.generator.spanish.PhraseGetter(r);
        return new com.memoeslink.generator.international.PhraseGetter(r);
    }
}
