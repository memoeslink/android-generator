package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public class PhraseGetter extends Getter implements PhraseDefiner {

    public PhraseGetter() {
        super();
    }

    public PhraseGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAgreement() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAmazement() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getApology() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAppreciation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getCongratulation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDisagreement() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubt() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFarewell() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGreeting() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPetition() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getInitiationQuestion() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getInquiryQuestion() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getShortAnswer() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getWelcome() {
        return Database.DEFAULT_VALUE;
    }
}
