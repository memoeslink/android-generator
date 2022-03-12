package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.NounDefiner;

public final class NounGetter extends com.memoeslink.generator.common.NounGetter implements NounDefiner {

    public NounGetter() {
        super();
    }

    public NounGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getNoun() {
        return getAnyGetter().getNoun();
    }

    @Override
    public String getPluralNoun() {
        return getAnyGetter().getPluralNoun();
    }

    @Override
    public String getFemaleNoun() {
        return getAnyGetter().getFemaleNoun();
    }

    @Override
    public String getPluralFemaleNoun() {
        return getAnyGetter().getPluralFemaleNoun();
    }

    @Override
    public String getMaleNoun() {
        return getAnyGetter().getMaleNoun();
    }

    @Override
    public String getPluralMaleNoun() {
        return getAnyGetter().getPluralMaleNoun();
    }

    @Override
    public String getNounWithArticle() {
        return getAnyGetter().getNounWithArticle();
    }

    @Override
    public String getPluralNounWithArticle() {
        return getAnyGetter().getPluralNounWithArticle();
    }

    @Override
    public String getFemaleNounWithArticle() {
        return getAnyGetter().getFemaleNounWithArticle();
    }

    @Override
    public String getPluralFemaleNounWithArticle() {
        return getAnyGetter().getPluralFemaleNounWithArticle();
    }

    @Override
    public String getMaleNounWithArticle() {
        return getAnyGetter().getMaleNounWithArticle();
    }

    @Override
    public String getPluralMaleNounWithArticle() {
        return getAnyGetter().getPluralMaleNounWithArticle();
    }

    @Override
    public String getNounWithIndefiniteArticle() {
        return getAnyGetter().getNounWithIndefiniteArticle();
    }

    @Override
    public String getPluralNounWithIndefiniteArticle() {
        return getAnyGetter().getPluralNounWithIndefiniteArticle();
    }

    @Override
    public String getFemaleNounWithIndefiniteArticle() {
        return getAnyGetter().getFemaleNounWithIndefiniteArticle();
    }

    @Override
    public String getPluralFemaleNounWithIndefiniteArticle() {
        return getAnyGetter().getPluralFemaleNounWithIndefiniteArticle();
    }

    @Override
    public String getMaleNounWithIndefiniteArticle() {
        return getAnyGetter().getMaleNounWithIndefiniteArticle();
    }

    @Override
    public String getPluralMaleNounWithIndefiniteArticle() {
        return getAnyGetter().getPluralMaleNounWithIndefiniteArticle();
    }

    public com.memoeslink.generator.common.NounGetter getAnyGetter() {
        switch (r.getInt(2)) {
            case 0:
                return new com.memoeslink.generator.english.NounGetter(r);
            case 1:
                return new com.memoeslink.generator.spanish.NounGetter(r);
        }
        return new com.memoeslink.generator.common.NounGetter(r);
    }
}
