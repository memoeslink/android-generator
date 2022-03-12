package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.AdjectiveDefiner;

public final class AdjectiveGetter extends com.memoeslink.generator.common.AdjectiveGetter implements AdjectiveDefiner {

    public AdjectiveGetter() {
        super();
    }

    public AdjectiveGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAdjective() {
        return getAnyGetter().getAdjective();
    }

    @Override
    public String getPluralAdjective() {
        return getAnyGetter().getPluralAdjective();
    }

    @Override
    public String getFemaleAdjective() {
        return getAnyGetter().getFemaleAdjective();
    }

    @Override
    public String getPluralFemaleAdjective() {
        return getAnyGetter().getPluralFemaleAdjective();
    }

    @Override
    public String getMaleAdjective() {
        return getAnyGetter().getMaleAdjective();
    }

    @Override
    public String getPluralMaleAdjective() {
        return getAnyGetter().getPluralMaleAdjective();
    }

    @Override
    public String getAdjectiveWithArticle() {
        return getAnyGetter().getAdjectiveWithArticle();
    }

    @Override
    public String getPluralAdjectiveWithArticle() {
        return getAnyGetter().getPluralAdjectiveWithArticle();
    }

    @Override
    public String getFemaleAdjectiveWithArticle() {
        return getAnyGetter().getFemaleAdjectiveWithArticle();
    }

    @Override
    public String getPluralFemaleAdjectiveWithArticle() {
        return getAnyGetter().getPluralFemaleAdjectiveWithArticle();
    }

    @Override
    public String getMaleAdjectiveWithArticle() {
        return getAnyGetter().getMaleAdjectiveWithArticle();
    }

    @Override
    public String getPluralMaleAdjectiveWithArticle() {
        return getAnyGetter().getPluralMaleAdjectiveWithArticle();
    }

    @Override
    public String getAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getAdjectiveWithIndefiniteArticle();
    }

    @Override
    public String getPluralAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getPluralAdjectiveWithIndefiniteArticle();
    }

    @Override
    public String getFemaleAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getFemaleAdjectiveWithIndefiniteArticle();
    }

    @Override
    public String getPluralFemaleAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getPluralFemaleAdjectiveWithIndefiniteArticle();
    }

    @Override
    public String getMaleAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getMaleAdjectiveWithIndefiniteArticle();
    }

    @Override
    public String getPluralMaleAdjectiveWithIndefiniteArticle() {
        return getAnyGetter().getPluralMaleAdjectiveWithIndefiniteArticle();
    }

    public com.memoeslink.generator.common.AdjectiveGetter getAnyGetter() {
        switch (r.getInt(2)) {
            case 0:
                return new com.memoeslink.generator.english.AdjectiveGetter(r);
            case 1:
                return new com.memoeslink.generator.spanish.AdjectiveGetter(r);
        }
        return new com.memoeslink.generator.common.AdjectiveGetter(r);
    }
}
