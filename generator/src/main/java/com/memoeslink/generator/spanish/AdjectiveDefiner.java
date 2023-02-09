package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.StringHelper;

public interface AdjectiveDefiner extends com.memoeslink.generator.common.AdjectiveDefiner {

    public String getCommonAdjective();

    public String getPluralCommonAdjective();

    public String getCommonAdjectiveWithArticle();

    public String getPluralCommonAdjectiveWithArticle();

    public String getCommonAdjectiveWithIndefiniteArticle();

    public String getPluralCommonAdjectiveWithIndefiniteArticle();

    default Adjective getRefinedAdjective(String s) {
        Adjective adjective = new Adjective();

        if (s.contains("AQ0C"))
            adjective.setGender(Gender.NEUTRAL);
        else if (StringHelper.containsAny(s, "AQ0M", "AO0M", "AQSM"))
            adjective.setGender(Gender.MASCULINE);
        else if (StringHelper.containsAny(s, "AQ0F", "AO0F", "AQSF"))
            adjective.setGender(Gender.FEMININE);
        else if (StringHelper.endsWith(s, "o"))
            adjective.setGender(Gender.MASCULINE);
        else if (StringHelper.endsWith(s, "a"))
            adjective.setGender(Gender.FEMININE);
        s = StringHelper.removeFirst(s, "\\s*(A[OQ][S0][CFM][A-Z\\d]{2})");
        adjective.setBase(s);
        return adjective;
    }

    public Adjective getRefinedSingularAdjective();

    public Adjective getRefinedPluralAdjective();

    public Adjective getRefinedFemaleAdjective();

    public Adjective getRefinedPluralFemaleAdjective();

    public Adjective getRefinedMaleAdjective();

    public Adjective getRefinedPluralMaleAdjective();

    public Adjective getRefinedCommonAdjective();

    public Adjective getRefinedPluralCommonAdjective();
}
