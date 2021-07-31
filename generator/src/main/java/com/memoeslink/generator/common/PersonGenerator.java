package com.memoeslink.generator.common;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class PersonGenerator extends Generator {
    NameGenerator nameGenerator;
    OccupationGenerator occupationGenerator;
    DateTimeGenerator dateTimeGenerator;

    public PersonGenerator() {
        super();
        nameGenerator = new NameGenerator(new Locale("xx"), null);
        occupationGenerator = new OccupationGenerator(new Locale("xx"), null);
        dateTimeGenerator = new DateTimeGenerator(new Locale("xx"), null);
    }

    public PersonGenerator(Locale locale, Long seed) {
        super(locale, seed);
        nameGenerator = new NameGenerator(locale, seed);
        occupationGenerator = new OccupationGenerator(locale, seed);
        dateTimeGenerator = new DateTimeGenerator(locale, seed);
    }

    public Person getPerson(Gender gender) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        String name;

        switch (gender) {
            case MASCULINE:
                name = nameGenerator.getName(NameType.MALE_FULL_NAME);
                break;
            case FEMININE:
                name = nameGenerator.getName(NameType.FEMALE_FULL_NAME);
                break;
            case NEUTRAL:
            case UNDEFINED:
            default:
                name = nameGenerator.getName(NameType.FULL_NAME);
                break;
        }
        String japaneseHonorific = StringHelper.EMPTY;

        if (StringHelper.endsWithAny(name, "-chan", "-kun", "-sama", "-san")) {
            List<String> parts = StringHelper.split(name, Separator.HYPHEN.getCharacter());
            name = parts.get(0);
            japaneseHonorific = parts.get(1);
        }
        Gender tempGender = gender == Gender.UNDEFINED || gender == Gender.NEUTRAL ?
                (r.getBoolean() ? Gender.MASCULINE : Gender.FEMININE) : gender;
        OccupationType occupationType;

        do {
            occupationType = OccupationType.values()[r.getInt(OccupationType.values().length)];
        } while (occupationType.getGender() != tempGender);
        String occupation = occupationGenerator.getOccupation(occupationType);
        String postNominalLetters = ResourceGetter.with(r).getString(Constant.POST_NOMINAL_LETTERS);
        LocalDate birthdate = dateTimeGenerator.getHumanDate();

        switch (r.getInt(4)) {
            case 0:
                postNominalLetters = StringHelper.EMPTY;
                break;
            case 1:
                occupation = StringHelper.EMPTY;
                break;
            default:
                occupation = StringHelper.EMPTY;
                postNominalLetters = StringHelper.EMPTY;
                break;
        }

        return new Person.PersonBuilder()
                .setFullName(name)
                .setGender(gender)
                .setJapaneseHonorific(japaneseHonorific)
                .setOccupation(occupation)
                .setPostNominalLetters(postNominalLetters)
                .setBirthdate(birthdate)
                .setAttribute("generated")
                .build();
    }

    public Person getAnonymousPerson(Gender gender) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        String username = nameGenerator.getUsername();
        LocalDate birthdate = dateTimeGenerator.getHumanDate();

        return new Person.PersonBuilder()
                .setUsername(username)
                .setGender(gender)
                .setBirthdate(birthdate)
                .setAttribute("generated")
                .setAttribute("anonymous")
                .build();
    }
}
