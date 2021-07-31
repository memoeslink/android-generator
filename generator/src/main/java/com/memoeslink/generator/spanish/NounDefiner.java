package com.memoeslink.generator.spanish;

public interface NounDefiner extends com.memoeslink.generator.common.NounDefiner {

    public Noun getRefinedNoun();

    public Noun getRefinedPluralNoun();

    public Noun getRefinedFemaleNoun();

    public Noun getRefinedPluralFemaleNoun();

    public Noun getRefinedMaleNoun();

    public Noun getRefinedPluralMaleNoun();
}