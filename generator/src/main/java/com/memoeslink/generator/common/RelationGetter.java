package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public class RelationGetter extends Getter implements RelationDefiner {

    public RelationGetter() {
        super();
    }

    public RelationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public TextComponent getRelationship(Person person) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getRelationship(Person person, int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getRelationship(Person person, int type, Gender gender) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getFemaleRelationship(Person person, int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getMaleRelationship(Person person, int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getUnspecificRelationship() {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getUnspecificRelationship(int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getUnspecificRelationship(int type, Gender gender) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getFemaleUnspecificRelationship(int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }

    @Override
    public TextComponent getMaleUnspecificRelationship(int type) {
        return new TextComponent(Database.DEFAULT_VALUE);
    }
}
