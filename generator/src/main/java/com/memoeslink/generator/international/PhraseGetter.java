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
    public String getSimpleGreeting() {
        return getAnyGetter().getSimpleGreeting();
    }

    public com.memoeslink.generator.common.PhraseGetter getAnyGetter() {
        return switch (r.getInt(2)) {
            case 0 -> new com.memoeslink.generator.english.PhraseGetter(r);
            case 1 -> new com.memoeslink.generator.spanish.PhraseGetter(r);
            default -> new com.memoeslink.generator.common.PhraseGetter(r);
        };
    }
}
