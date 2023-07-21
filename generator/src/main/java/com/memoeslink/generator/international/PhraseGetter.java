package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.PhraseDefiner;

public class PhraseGetter extends com.memoeslink.generator.common.PhraseGetter implements PhraseDefiner {

    public PhraseGetter() {
        super();
    }

    public PhraseGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAgreement() {
        return getAnyGetter().getAgreement();
    }

    @Override
    public String getAmazement() {
        return getAnyGetter().getAmazement();
    }

    @Override
    public String getApology() {
        return getAnyGetter().getApology();
    }

    @Override
    public String getAppreciation() {
        return getAnyGetter().getAppreciation();
    }

    @Override
    public String getCongratulation() {
        return getAnyGetter().getCongratulation();
    }

    @Override
    public String getDisagreement() {
        return getAnyGetter().getDisagreement();
    }

    @Override
    public String getDoubt() {
        return getAnyGetter().getDoubt();
    }

    @Override
    public String getFarewell() {
        return getAnyGetter().getFarewell();
    }

    @Override
    public String getGreeting() {
        return getAnyGetter().getGreeting();
    }

    @Override
    public String getPetition() {
        return getAnyGetter().getPetition();
    }

    @Override
    public String getInitiationQuestion() {
        return getAnyGetter().getInitiationQuestion();
    }

    @Override
    public String getInquiryQuestion() {
        return getAnyGetter().getInquiryQuestion();
    }

    @Override
    public String getShortAnswer() {
        return getAnyGetter().getShortAnswer();
    }

    @Override
    public String getWelcome() {
        return getAnyGetter().getWelcome();
    }

    public com.memoeslink.generator.common.PhraseGetter getAnyGetter() {
        return switch (r.getInt(2)) {
            case 0 -> new com.memoeslink.generator.english.PhraseGetter(r);
            case 1 -> new com.memoeslink.generator.spanish.PhraseGetter(r);
            default -> new com.memoeslink.generator.common.PhraseGetter(r);
        };
    }
}
