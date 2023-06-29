package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import org.memoeslink.StringHelper;

public class NounGetter extends Getter implements NounDefiner {

    public NounGetter() {
        super();
    }

    public NounGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getEmptyNoun() {
        return StringHelper.EMPTY;
    }

    @Override
    public String getNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleNoun() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleNounWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleNounWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }
}
