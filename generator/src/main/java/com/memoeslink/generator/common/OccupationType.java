package com.memoeslink.generator.common;

public enum OccupationType {
    ANY(Gender.UNDEFINED),
    MALE_OCCUPATION(Gender.MASCULINE),
    FEMALE_OCCUPATION(Gender.FEMININE),
    GENDERLESS_OCCUPATION(Gender.NEUTRAL),
    MALE_JOB_TITLE(Gender.MASCULINE),
    FEMALE_JOB_TITLE(Gender.FEMININE),
    GENDERLESS_JOB_TITLE(Gender.NEUTRAL),
    MALE_JOB_POSITION(Gender.MASCULINE),
    FEMALE_JOB_POSITION(Gender.FEMININE),
    GENDERLESS_JOB_POSITION(Gender.NEUTRAL),
    MALE_FANTASY_CLASS(Gender.MASCULINE),
    FEMALE_FANTASY_CLASS(Gender.FEMININE),
    GENDERLESS_FANTASY_CLASS(Gender.NEUTRAL),
    MALE_LEVELED_FANTASY_CLASS(Gender.MASCULINE),
    FEMALE_LEVELED_FANTASY_CLASS(Gender.FEMININE),
    GENDERLESS_LEVELED_FANTASY_CLASS(Gender.NEUTRAL),
    MALE_HONORIFIC(Gender.MASCULINE),
    FEMALE_HONORIFIC(Gender.FEMININE),
    GENDERLESS_HONORIFIC(Gender.NEUTRAL),
    MALE_ROYAL_TITLE(Gender.MASCULINE),
    FEMALE_ROYAL_TITLE(Gender.FEMININE),
    GENDERLESS_ROYAL_TITLE(Gender.NEUTRAL);

    private final Gender gender;

    private OccupationType(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }
}
