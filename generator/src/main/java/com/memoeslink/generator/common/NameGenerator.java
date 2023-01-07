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
        NameGetter getter = getGetter();
        nameType = nameType != null ? nameType : NameType.EMPTY;

        switch (nameType) {
            case DEFAULT:
                return getter.getDefaultName();
            case TEST_CASE:
                return getter.getTestName();
            case MALE_FORENAME:
                return getter.getMaleForename();
            case FEMALE_FORENAME:
                return getter.getFemaleForename();
            case MALE_PATRONYMIC:
                return getter.getMalePatronymic();
            case FEMALE_PATRONYMIC:
                return getter.getFemalePatronymic();
            case DOUBLE_BARRELLED_MALE_FORENAME:
                return getter.getDoubleBarrelledMaleForename();
            case DOUBLE_BARRELLED_FEMALE_FORENAME:
                return getter.getDoubleBarrelledFemaleForename();
            case DOUBLE_MALE_FORENAME:
                return getter.getDoubleMaleForename();
            case DOUBLE_FEMALE_FORENAME:
                return getter.getDoubleFemaleForename();
            case MALE_GIVEN_NAME:
                return getter.getMaleGivenName();
            case FEMALE_GIVEN_NAME:
                return getter.getFemaleGivenName();
            case SURNAME:
                return getter.getSurname();
            case DUAL_SURNAME:
                return getter.getDualSurname();
            case MALE_SIMPLE_NAME:
                return getter.getMaleSimpleName();
            case FEMALE_SIMPLE_NAME:
                return getter.getFemaleSimpleName();
            case MALE_FULL_NAME:
                return getter.getMaleFullName();
            case FEMALE_FULL_NAME:
                return getter.getFemaleFullName();
            case MALE_DEFINED_FORENAME:
                return getter.getMaleDefinedForename();
            case FEMALE_DEFINED_FORENAME:
                return getter.getFemaleDefinedForename();
            case DEFINED_FAMILY_NAME:
                return getter.getDefinedFamilyName();
            case MALE_DEFINED_FULL_NAME:
                return getter.getMaleDefinedFullName();
            case FEMALE_DEFINED_FULL_NAME:
                return getter.getFemaleDefinedFullName();
            case DEFINED_FULL_NAME:
                return getter.getDefinedFullName();
            case MALE_ITERATIVE_FORENAME:
                return getter.getMaleIterativeForename();
            case FEMALE_ITERATIVE_FORENAME:
                return getter.getFemaleIterativeForename();
            case ITERATIVE_FAMILY_NAME:
                return getter.getIterativeFamilyName();
            case MALE_ITERATIVE_FULL_NAME:
                return getter.getMaleIterativeFullName();
            case FEMALE_ITERATIVE_FULL_NAME:
                return getter.getFemaleIterativeFullName();
            case ITERATIVE_FULL_NAME:
                return getter.getIterativeFullName();
            case MALE_PATTERN_FORENAME:
                return getter.getMalePatternForename();
            case FEMALE_PATTERN_FORENAME:
                return getter.getFemalePatternForename();
            case PATTERN_FAMILY_NAME:
                return getter.getPatternFamilyName();
            case MALE_PATTERN_FULL_NAME:
                return getter.getMalePatternFullName();
            case FEMALE_PATTERN_FULL_NAME:
                return getter.getFemalePatternFullName();
            case PATTERN_FULL_NAME:
                return getter.getPatternFullName();
            case MALE_FREQUENCY_FORENAME:
                return getter.getMaleFrequencyForename();
            case FEMALE_FREQUENCY_FORENAME:
                return getter.getFemaleFrequencyForename();
            case FREQUENCY_FAMILY_NAME:
                return getter.getFrequencyFamilyName();
            case MALE_FREQUENCY_FULL_NAME:
                return getter.getMaleFrequencyFullName();
            case FEMALE_FREQUENCY_FULL_NAME:
                return getter.getFemaleFrequencyFullName();
            case FREQUENCY_FULL_NAME:
                return getter.getFrequencyFullName();
            case MALE_PREFORMED_FORENAME:
                return getter.getMalePreformedForename();
            case FEMALE_PREFORMED_FORENAME:
                return getter.getFemalePreformedForename();
            case PREFORMED_FAMILY_NAME:
                return getter.getPreformedFamilyName();
            case MALE_PREFORMED_FULL_NAME:
                return getter.getMalePreformedFullName();
            case FEMALE_PREFORMED_FULL_NAME:
                return getter.getFemalePreformedFullName();
            case PREFORMED_FULL_NAME:
                return getter.getPreformedFullName();
            case MALE_MARKOV_FORENAME:
                return getter.getMaleMarkovForename();
            case FEMALE_MARKOV_FORENAME:
                return getter.getFemaleMarkovForename();
            case MARKOV_FAMILY_NAME:
                return getter.getMarkovFamilyName();
            case MALE_MARKOV_FULL_NAME:
                return getter.getMaleMarkovFullName();
            case FEMALE_MARKOV_FULL_NAME:
                return getter.getFemaleMarkovFullName();
            case MARKOV_FULL_NAME:
                return getter.getMarkovFullName();
            case SECRET_NAME:
                return getter.getSecretName();
            case USERNAME:
                return getter.getUsername();
            case COMPOSITE_USERNAME:
                return getter.getCompositeUsername();
            case DERIVED_USERNAME:
                return getter.getDerivedUsername();
            case ANONYMOUS_NAME:
                return getter.getAnonymousName();
            case ANONYMOUS_ANIMAL:
                return getter.getAnonymousAnimal();
            case EMPTY:
            default:
                return getter.getEmptyName();
        }
    }

    public String getNameOrDefault(NameType nameType) {
        return getNameOrDefault(nameType, getDefaultName());
    }

    public String getNameOrDefault(NameType nameType, String defaultValue) {
        String name = getName(nameType);

        if (StringHelper.isNullOrBlank(name) || StringHelper.equalsDefault(name))
            return defaultValue;
        return name;
    }

    public String getNameOrRetry(NameType nameType) {
        String name;
        boolean invalidName;
        int tries = 9999;

        do {
            name = getName(nameType);
            tries--;
        } while ((invalidName = (StringHelper.isNullOrBlank(name) || StringHelper.equalsDefault(name))) && tries > 0);

        if (invalidName && tries == 0)
            name = getDefaultName();
        return name;
    }

    public String getGivenName() {
        return getGetter().getGivenName();
    }

    public String getSimpleName() {
        return getGetter().getSimpleName();
    }

    public String getFullName() {
        return getGetter().getFullName();
    }

    public String getUsername() {
        switch (r.getInt(5)) {
            case 1:
                return getName(NameType.COMPOSITE_USERNAME);
            case 2:
                return getName(NameType.DERIVED_USERNAME);
            case 3:
                return getName(NameType.ANONYMOUS_NAME);
            case 4:
                return getName(NameType.ANONYMOUS_ANIMAL);
            case 0:
            default:
                return getName(NameType.USERNAME);
        }
    }

    public static String getDefaultName() {
        return Constant.DEFAULT_NAME;
    }

    private NameGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.international.NameGetter(r);
        else {
            switch (locale.getLanguage()) {
                case "ar":
                    return new com.memoeslink.generator.arabic.NameGetter(r);
                case "de":
                    return new com.memoeslink.generator.german.NameGetter(r);
                case "en":
                    return new com.memoeslink.generator.english.NameGetter(r);
                case "es":
                    if (locale.getCountry().equals("MX"))
                        return new com.memoeslink.generator.spanish.mexico.NameGetter(r);
                    else
                        return new com.memoeslink.generator.spanish.NameGetter(r);
                case "fr":
                    return new com.memoeslink.generator.french.NameGetter(r);
                case "it":
                    return new com.memoeslink.generator.italian.NameGetter(r);
                case "hi":
                    return new com.memoeslink.generator.hindi.NameGetter(r);
                case "ja":
                    return new com.memoeslink.generator.japanese.NameGetter(r);
                case "pt":
                    return new com.memoeslink.generator.portuguese.NameGetter(r);
                case "ru":
                    return new com.memoeslink.generator.russian.NameGetter(r);
                case "xx":
                case StringHelper.EMPTY:
                default:
                    return new com.memoeslink.generator.international.NameGetter(r);
            }
        }
    }
}
