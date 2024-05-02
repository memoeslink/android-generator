package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.util.Locale;

public class OccupationGenerator extends Generator {

    public OccupationGenerator() {
        super();
    }

    public OccupationGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getOccupation(OccupationType occupationType) {
        OccupationGetter getter = getOccupationGetter();
        occupationType = occupationType != null ? occupationType : OccupationType.ANY;

        return switch (occupationType) {
            case MALE_OCCUPATION -> getter.getMaleOccupation();
            case FEMALE_OCCUPATION -> getter.getFemaleOccupation();
            case GENDERLESS_OCCUPATION -> getter.getGenderlessOccupation();
            case MALE_JOB_TITLE -> getter.getMaleJobTitle();
            case FEMALE_JOB_TITLE -> getter.getFemaleJobTitle();
            case GENDERLESS_JOB_TITLE -> getter.getGenderlessJobTitle();
            case MALE_JOB_POSITION -> getter.getMaleJobPosition();
            case FEMALE_JOB_POSITION -> getter.getFemaleJobPosition();
            case GENDERLESS_JOB_POSITION -> getter.getGenderlessJobPosition();
            case MALE_FANTASY_CLASS -> getter.getMaleSimpleFantasyClass();
            case FEMALE_FANTASY_CLASS -> getter.getFemaleSimpleFantasyClass();
            case GENDERLESS_FANTASY_CLASS -> getter.getGenderlessSimpleFantasyClass();
            case MALE_LEVELED_FANTASY_CLASS -> getter.getMaleFantasyClass();
            case FEMALE_LEVELED_FANTASY_CLASS -> getter.getFemaleFantasyClass();
            case GENDERLESS_LEVELED_FANTASY_CLASS -> getter.getGenderlessFantasyClass();
            case MALE_HONORIFIC -> getter.getMaleHonorific();
            case FEMALE_HONORIFIC -> getter.getFemaleHonorific();
            case GENDERLESS_HONORIFIC -> getter.getGenderlessHonorific();
            case MALE_ROYAL_TITLE -> getter.getMaleRoyalTitle();
            case FEMALE_ROYAL_TITLE -> getter.getFemaleRoyalTitle();
            case GENDERLESS_ROYAL_TITLE -> getter.getGenderlessRoyalTitle();
            default -> getOccupation(r.getElement(OccupationType.values()));
        };
    }

    private OccupationGetter getOccupationGetter() {
        OccupationGetter getter;

        if (StringHelper.isNullOrEmpty(locale.getLanguage()))
            getter = new com.memoeslink.generator.international.OccupationGetter(r);
        else if (locale.getLanguage().equals("en"))
            getter = new com.memoeslink.generator.english.OccupationGetter(r);
        else if (locale.getLanguage().equals("es"))
            getter = new com.memoeslink.generator.spanish.OccupationGetter(r);
        else
            getter = new com.memoeslink.generator.international.OccupationGetter(r);
        return getter;
    }

    public static String getDefaultOccupation() {
        return Constant.DEFAULT_OCCUPATION;
    }
}
