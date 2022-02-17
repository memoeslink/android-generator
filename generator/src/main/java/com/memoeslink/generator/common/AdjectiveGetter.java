package com.memoeslink.generator.common;

import java.main.common.Randomizer;

public class AdjectiveGetter extends Getter implements AdjectiveDefiner {

    public AdjectiveGetter() {
        super();
    }

    public AdjectiveGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getEmptyAdjective() {
        return StringHelper.EMPTY;
    }

    @Override
    public String getAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleAdjective() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleAdjectiveWithArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralFemaleAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPluralMaleAdjectiveWithIndefiniteArticle() {
        return Database.DEFAULT_VALUE;
    }
}
