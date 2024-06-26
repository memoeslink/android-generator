package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.PhraseDefiner;
import com.memoeslink.generator.common.ResourceGetter;

import java.time.LocalTime;
import java.util.Locale;

public class PhraseGetter extends com.memoeslink.generator.common.PhraseGetter implements PhraseDefiner {

    public PhraseGetter() {
        super();
    }

    public PhraseGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAgreement() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.agreement");
    }

    @Override
    public String getAmazement() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.amazement");
    }

    @Override
    public String getApology() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.apology");
    }

    @Override
    public String getAppreciation() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.appreciation");
    }

    @Override
    public String getCongratulation() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.congratulation");
    }

    @Override
    public String getDisagreement() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.disagreement");
    }

    @Override
    public String getDoubt() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.doubt");
    }

    @Override
    public String getFarewell() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.farewell");
    }

    @Override
    public String getGreeting() {
        if (r.getBoolean()) {
            return switch (PeriodOfDay.get(LocalTime.now())) {
                case MORNING ->
                        ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.greeting.morning");
                case AFTERNOON ->
                        ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.greeting.afternoon");
                case NIGHT ->
                        ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.greeting.night");
            };
        } else
            return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.greeting");
    }

    @Override
    public String getPetition() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.petition");
    }

    @Override
    public String getInitiationQuestion() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.question.initiation");
    }

    @Override
    public String getInquiryQuestion() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.question.inquiry");
    }

    @Override
    public String getShortAnswer() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.short.answer");
    }

    @Override
    public String getWelcome() {
        return ResourceGetter.with(r).getStrFromResBundle(new Locale("es"), "phrase.common.welcome");
    }
}
