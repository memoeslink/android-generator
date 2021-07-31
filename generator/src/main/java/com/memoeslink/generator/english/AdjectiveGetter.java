package com.memoeslink.generator.english;

import com.memoeslink.generator.common.AdjectiveDefiner;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Randomizer;
import com.memoeslink.generator.common.Separator;

public final class AdjectiveGetter extends com.memoeslink.generator.common.AdjectiveGetter implements AdjectiveDefiner {

    public AdjectiveGetter() {
        super();
    }

    public AdjectiveGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAdjective() {
        return Database.selectEnglishAdjective(r.getInt(1, Database.countEnglishAdjectives()));
    }

    @Override
    public String getPluralAdjective() {
        return getAdjective();
    }

    @Override
    public String getFemaleAdjective() {
        return getAdjective();
    }

    @Override
    public String getPluralFemaleAdjective() {
        return getPluralAdjective();
    }

    @Override
    public String getMaleAdjective() {
        return getAdjective();
    }

    @Override
    public String getPluralMaleAdjective() {
        return getPluralAdjective();
    }

    @Override
    public String getAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getAdjective();
    }

    @Override
    public String getPluralAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralAdjective();
    }

    @Override
    public String getFemaleAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getFemaleAdjective();
    }

    @Override
    public String getPluralFemaleAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralFemaleAdjective();
    }

    @Override
    public String getMaleAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getMaleAdjective();
    }

    @Override
    public String getPluralMaleAdjectiveWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralMaleAdjective();
    }

    @Override
    public String getAdjectiveWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getAdjective());
    }

    @Override
    public String getPluralAdjectiveWithIndefiniteArticle() {
        return getPluralAdjective();
    }

    @Override
    public String getFemaleAdjectiveWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getFemaleAdjective());
    }

    @Override
    public String getPluralFemaleAdjectiveWithIndefiniteArticle() {
        return getPluralFemaleAdjective();
    }

    @Override
    public String getMaleAdjectiveWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getMaleAdjective());
    }

    @Override
    public String getPluralMaleAdjectiveWithIndefiniteArticle() {
        return getPluralMaleAdjective();
    }
}
