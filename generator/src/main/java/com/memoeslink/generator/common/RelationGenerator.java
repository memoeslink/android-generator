package com.memoeslink.generator.common;

import org.memoeslink.StringHelper;

import java.util.Locale;

public class RelationGenerator extends Generator {

    public RelationGenerator() {
        super();
    }

    public RelationGenerator(Locale locale, Long seed) {
        super(locale, seed);
    }

    public TextComponent getRelation(Person person, int type, Gender gender) {
        RelationGetter getter = getGetter();
        gender = gender != null ? gender : Gender.UNDEFINED;

        return switch (gender) {
            case MASCULINE -> getter.getMaleRelationship(person, type);
            case FEMININE -> getter.getFemaleRelationship(person, type);
            case NEUTRAL -> getter.getRelationship(person, type);
            default -> getRelation(person, type, r.getElement(Gender.values()));
        };
    }

    private RelationGetter getGetter() {
        if (StringHelper.isNullOrEmpty(locale.getLanguage()) ||
                locale.getLanguage().equals("xx") || locale.getCountry().equals("XX"))
            return new com.memoeslink.generator.common.RelationGetter(r);
        else if (locale.getLanguage().equals("en"))
            return new com.memoeslink.generator.english.RelationGetter(r);
        else if (locale.getLanguage().equals("es"))
            return new com.memoeslink.generator.spanish.RelationGetter(r);
        return new com.memoeslink.generator.international.RelationGetter(r);
    }
}
