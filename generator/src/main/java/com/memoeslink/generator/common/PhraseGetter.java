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
    public String getSimpleGreeting() {
        return Database.DEFAULT_VALUE;
    }
}
