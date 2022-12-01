package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.Person;
import com.memoeslink.generator.common.RelationDefiner;
import com.memoeslink.generator.common.TextComponent;

public final class RelationGetter extends com.memoeslink.generator.common.RelationGetter implements RelationDefiner {

    public RelationGetter() {
        super();
    }

    public RelationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public TextComponent getRelationship(Person person) {
        return getAnyGetter().getRelationship(person);
    }

    @Override
    public TextComponent getRelationship(Person person, int type) {
        return getAnyGetter().getRelationship(person, type);
    }

    @Override
    public TextComponent getRelationship(Person person, int type, Gender gender) {
        return getAnyGetter().getRelationship(person, type, gender);
    }

    @Override
    public TextComponent getFemaleRelationship(Person person, int type) {
        return getAnyGetter().getFemaleRelationship(person, type);
    }

    @Override
    public TextComponent getMaleRelationship(Person person, int type) {
        return getAnyGetter().getMaleRelationship(person, type);
    }

    @Override
    public TextComponent getUnspecificRelationship() {
        return getAnyGetter().getUnspecificRelationship();
    }

    @Override
    public TextComponent getUnspecificRelationship(int type) {
        return getAnyGetter().getUnspecificRelationship(type);
    }

    @Override
    public TextComponent getUnspecificRelationship(int type, Gender gender) {
        return getAnyGetter().getUnspecificRelationship(type, gender);
    }

    @Override
    public TextComponent getFemaleUnspecificRelationship(int type) {
        return getAnyGetter().getFemaleUnspecificRelationship(type);
    }

    @Override
    public TextComponent getMaleUnspecificRelationship(int type) {
        return getAnyGetter().getMaleUnspecificRelationship(type);
    }

    public com.memoeslink.generator.common.RelationGetter getAnyGetter() {
        switch (r.getInt(2)) {
            case 0:
                return new com.memoeslink.generator.english.RelationGetter(r);
            case 1:
                return new com.memoeslink.generator.spanish.RelationGetter(r);
        }
        return new com.memoeslink.generator.common.RelationGetter(r);
    }
}
