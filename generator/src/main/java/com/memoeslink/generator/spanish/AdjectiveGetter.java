package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.AdjectiveDefiner;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;

import java.main.common.Randomizer;

public final class AdjectiveGetter extends com.memoeslink.generator.common.AdjectiveGetter implements AdjectiveDefiner, com.memoeslink.generator.spanish.AdjectiveDefiner {

    public AdjectiveGetter() {
        super();
    }

    public AdjectiveGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getAdjective() {
        return getRefinedSingularAdjective().getBase();
    }

    @Override
    public String getPluralAdjective() {
        return getRefinedPluralAdjective().getBase();
    }

    @Override
    public String getFemaleAdjective() {
        return getRefinedFemaleAdjective().getBase();
    }

    @Override
    public String getPluralFemaleAdjective() {
        return getRefinedPluralFemaleAdjective().getBase();
    }

    @Override
    public String getMaleAdjective() {
        return getRefinedMaleAdjective().getBase();
    }

    @Override
    public String getPluralMaleAdjective() {
        return getRefinedPluralMaleAdjective().getBase();
    }

    @Override
    public String getAdjectiveWithArticle() {
        return getRefinedSingularAdjective().getBaseWithArticle();
    }

    @Override
    public String getPluralAdjectiveWithArticle() {
        return getRefinedPluralAdjective().getBaseWithArticle();
    }

    @Override
    public String getFemaleAdjectiveWithArticle() {
        return getRefinedFemaleAdjective().getBaseWithArticle();
    }

    @Override
    public String getPluralFemaleAdjectiveWithArticle() {
        return getRefinedPluralFemaleAdjective().getBaseWithArticle();
    }

    @Override
    public String getMaleAdjectiveWithArticle() {
        return getRefinedMaleAdjective().getBaseWithArticle();
    }

    @Override
    public String getPluralMaleAdjectiveWithArticle() {
        return getRefinedPluralMaleAdjective().getBaseWithArticle();
    }

    @Override
    public String getAdjectiveWithIndefiniteArticle() {
        return getRefinedSingularAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralAdjectiveWithIndefiniteArticle() {
        return getRefinedPluralAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getFemaleAdjectiveWithIndefiniteArticle() {
        return getRefinedFemaleAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralFemaleAdjectiveWithIndefiniteArticle() {
        return getRefinedPluralFemaleAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getMaleAdjectiveWithIndefiniteArticle() {
        return getRefinedMaleAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralMaleAdjectiveWithIndefiniteArticle() {
        return getRefinedPluralMaleAdjective().getBaseWithIndefiniteArticle();
    }

    @Override
    public Adjective getRefinedSingularAdjective() {
        Adjective adjective = getRefinedAdjective(Database.selectSpanishSingularAdjective(r.getInt(1, Database.countSpanishSingularAdjectives())));
        adjective.setPlural(false);
        return adjective;
    }

    @Override
    public Adjective getRefinedPluralAdjective() {
        Adjective adjective = getRefinedAdjective(Database.selectSpanishPluralAdjective(r.getInt(1, Database.countSpanishPluralAdjectives())));
        adjective.setPlural(true);
        return adjective;
    }

    @Override
    public Adjective getRefinedFemaleAdjective() {
        Adjective adjective = getRefinedSingularAdjective();
        return adjective.getGender() == Gender.FEMININE ? adjective : getRefinedFemaleAdjective();
    }

    @Override
    public Adjective getRefinedPluralFemaleAdjective() {
        Adjective adjective = getRefinedPluralAdjective();
        return adjective.getGender() == Gender.FEMININE ? adjective : getRefinedPluralFemaleAdjective();
    }

    @Override
    public Adjective getRefinedMaleAdjective() {
        Adjective adjective = getRefinedSingularAdjective();
        return adjective.getGender() == Gender.MASCULINE ? adjective : getRefinedMaleAdjective();
    }

    @Override
    public Adjective getRefinedPluralMaleAdjective() {
        Adjective adjective = getRefinedPluralAdjective();
        return adjective.getGender() == Gender.MASCULINE ? adjective : getRefinedPluralMaleAdjective();
    }
}
