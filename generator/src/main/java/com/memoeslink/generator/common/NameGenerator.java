package com.memoeslink.generator.common;

import java.util.Locale;

public class NameGenerator extends Generator {

    public NameGenerator() {
        super();
    }

    public NameGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public String getName(NameType nameType) {
        NameGetter getter = NameGetterFactory.getNameGetter(locale, r);
        nameType = nameType != null ? nameType : NameType.EMPTY;

        return switch (nameType) {
            case MALE_FORENAME -> getter.getMaleForename();
            case FEMALE_FORENAME -> getter.getFemaleForename();
            case MALE_PATRONYMIC -> getter.getMalePatronymic();
            case FEMALE_PATRONYMIC -> getter.getFemalePatronymic();
            case MALE_DOUBLE_BARRELLED_FORENAME -> getter.getMaleDoubleBarrelledForename();
            case FEMALE_DOUBLE_BARRELLED_FORENAME -> getter.getFemaleDoubleBarrelledForename();
            case MALE_DOUBLE_FORENAME -> getter.getMaleDoubleForename();
            case FEMALE_DOUBLE_FORENAME -> getter.getFemaleDoubleForename();
            case MALE_GIVEN_NAME -> getter.getMaleGivenName();
            case FEMALE_GIVEN_NAME -> getter.getFemaleGivenName();
            case SURNAME -> getter.getSurname();
            case DUAL_SURNAME -> getter.getDualSurname();
            case MALE_SIMPLE_NAME -> getter.getMaleSimpleName();
            case FEMALE_SIMPLE_NAME -> getter.getFemaleSimpleName();
            case MALE_FULL_NAME -> getter.getMaleFullName();
            case FEMALE_FULL_NAME -> getter.getFemaleFullName();
            case MALE_DEFINED_FORENAME -> getter.getMaleDefinedForename();
            case FEMALE_DEFINED_FORENAME -> getter.getFemaleDefinedForename();
            case DEFINED_FAMILY_NAME -> getter.getDefinedFamilyName();
            case MALE_DEFINED_FULL_NAME -> getter.getMaleDefinedFullName();
            case FEMALE_DEFINED_FULL_NAME -> getter.getFemaleDefinedFullName();
            case DEFINED_FULL_NAME -> getter.getDefinedFullName();
            case MALE_ITERATIVE_FORENAME -> getter.getMaleIterativeForename();
            case FEMALE_ITERATIVE_FORENAME -> getter.getFemaleIterativeForename();
            case ITERATIVE_FAMILY_NAME -> getter.getIterativeFamilyName();
            case MALE_ITERATIVE_FULL_NAME -> getter.getMaleIterativeFullName();
            case FEMALE_ITERATIVE_FULL_NAME -> getter.getFemaleIterativeFullName();
            case ITERATIVE_FULL_NAME -> getter.getIterativeFullName();
            case MALE_PATTERN_FORENAME -> getter.getMalePatternForename();
            case FEMALE_PATTERN_FORENAME -> getter.getFemalePatternForename();
            case PATTERN_FAMILY_NAME -> getter.getPatternFamilyName();
            case MALE_PATTERN_FULL_NAME -> getter.getMalePatternFullName();
            case FEMALE_PATTERN_FULL_NAME -> getter.getFemalePatternFullName();
            case PATTERN_FULL_NAME -> getter.getPatternFullName();
            case MALE_FREQUENCY_FORENAME -> getter.getMaleFrequencyForename();
            case FEMALE_FREQUENCY_FORENAME -> getter.getFemaleFrequencyForename();
            case FREQUENCY_FAMILY_NAME -> getter.getFrequencyFamilyName();
            case MALE_FREQUENCY_FULL_NAME -> getter.getMaleFrequencyFullName();
            case FEMALE_FREQUENCY_FULL_NAME -> getter.getFemaleFrequencyFullName();
            case FREQUENCY_FULL_NAME -> getter.getFrequencyFullName();
            case MALE_PREFORMED_FORENAME -> getter.getMalePreformedForename();
            case FEMALE_PREFORMED_FORENAME -> getter.getFemalePreformedForename();
            case PREFORMED_FAMILY_NAME -> getter.getPreformedFamilyName();
            case MALE_PREFORMED_FULL_NAME -> getter.getMalePreformedFullName();
            case FEMALE_PREFORMED_FULL_NAME -> getter.getFemalePreformedFullName();
            case PREFORMED_FULL_NAME -> getter.getPreformedFullName();
            case MALE_MARKOVIAN_FORENAME -> getter.getMaleMarkovianForename();
            case FEMALE_MARKOVIAN_FORENAME -> getter.getFemaleMarkovianForename();
            case MARKOVIAN_FAMILY_NAME -> getter.getMarkovianFamilyName();
            case MALE_MARKOVIAN_FULL_NAME -> getter.getMaleMarkovianFullName();
            case FEMALE_MARKOVIAN_FULL_NAME -> getter.getFemaleMarkovianFullName();
            case MARKOVIAN_FULL_NAME -> getter.getMarkovianFullName();
            case SECRET_NAME -> getter.getSecretName();
            default -> getter.getEmptyName();
        };
    }

    public String getGivenName() {
        return NameGetterFactory.getNameGetter(locale, r).getGivenName();
    }

    public String getSimpleName() {
        return NameGetterFactory.getNameGetter(locale, r).getSimpleName();
    }

    public String getFullName() {
        return NameGetterFactory.getNameGetter(locale, r).getFullName();
    }
}
