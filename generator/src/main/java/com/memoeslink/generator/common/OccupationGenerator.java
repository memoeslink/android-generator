package com.memoeslink.generator.common;

import java.util.Locale;

public class OccupationGenerator extends Generator {

    public OccupationGenerator() {
        super();
    }

    public OccupationGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getOccupation(OccupationType occupationType) {
        OccupationGetter getter;

        if (StringHelper.isNullOrEmpty(locale.getLanguage()))
            getter = new com.memoeslink.generator.international.OccupationGetter(r);
        else if (locale.getLanguage().equals("en"))
            getter = new com.memoeslink.generator.english.OccupationGetter(r);
        else if (locale.getLanguage().equals("es"))
            getter = new com.memoeslink.generator.spanish.OccupationGetter(r);
        else
            getter = new com.memoeslink.generator.international.OccupationGetter(r);
        occupationType = occupationType != null ? occupationType : OccupationType.ANY;

        switch (occupationType) {
            case MALE_OCCUPATION:
                return getter.getMaleOccupation();
            case FEMALE_OCCUPATION:
                return getter.getFemaleOccupation();
            case GENDERLESS_OCCUPATION:
                return getter.getGenderlessOccupation();
            case MALE_JOB_TITLE:
                return getter.getMaleJobTitle();
            case FEMALE_JOB_TITLE:
                return getter.getFemaleJobTitle();
            case GENDERLESS_JOB_TITLE:
                return getter.getGenderlessJobTitle();
            case MALE_FANTASY_CLASS:
                return getter.getMaleSimpleFantasyClass();
            case FEMALE_FANTASY_CLASS:
                return getter.getFemaleSimpleFantasyClass();
            case GENDERLESS_FANTASY_CLASS:
                return getter.getGenderlessSimpleFantasyClass();
            case MALE_LEVELED_FANTASY_CLASS:
                return getter.getMaleFantasyClass();
            case FEMALE_LEVELED_FANTASY_CLASS:
                return getter.getFemaleFantasyClass();
            case GENDERLESS_LEVELED_FANTASY_CLASS:
                return getter.getGenderlessFantasyClass();
            case MALE_HONORIFIC:
                return getter.getMaleHonorific();
            case FEMALE_HONORIFIC:
                return getter.getFemaleHonorific();
            case GENDERLESS_HONORIFIC:
                return getter.getGenderlessHonorific();
            case MALE_ROYAL_TITLE:
                return getter.getMaleRoyalTitle();
            case FEMALE_ROYAL_TITLE:
                return getter.getFemaleRoyalTitle();
            case GENDERLESS_ROYAL_TITLE:
                return getter.getGenderlessRoyalTitle();
            case ANY:
            default:
                return getOccupation(OccupationType.values()[r.getInt(OccupationType.values().length)]);
        }
    }

    public static String getDefaultOccupation() {
        return Constant.DEFAULT_OCCUPATION;
    }
}
