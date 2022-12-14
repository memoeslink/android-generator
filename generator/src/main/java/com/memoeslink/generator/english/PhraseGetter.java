package com.memoeslink.generator.english;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.PhraseDefiner;

import java.time.LocalTime;

public class PhraseGetter extends com.memoeslink.generator.common.PhraseGetter implements PhraseDefiner {

    public PhraseGetter() {
        super();
    }

    public PhraseGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getSimpleGreeting() {
        return PeriodOfDay.get(LocalTime.now()).getGreeting();
    }
}
