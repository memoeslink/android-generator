package com.memoeslink.generator.english;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.Person;
import com.memoeslink.generator.common.RelationDefiner;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.TextComponent;

import org.memoeslink.StringHelper;

public class RelationGetter extends com.memoeslink.generator.common.RelationGetter implements RelationDefiner {

    public RelationGetter() {
        super();
    }

    public RelationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public TextComponent getRelationship(Person person) {
        int type = r.getInt(2, Constant.RELATIONSHIP.length);
        return getRelationship(person, type);
    }

    @Override
    public TextComponent getRelationship(Person person, int type) {
        return getRelationship(person, type, Gender.NEUTRAL);
    }

    @Override
    public TextComponent getRelationship(Person person, int type, Gender gender) {
        String relationship;

        if (person == null)
            relationship = ResourceGetter.with(r).getString(Constant.RELATIONSHIP, 0);
        else {
            relationship = ResourceGetter.with(r).getString(Constant.RELATIONSHIP, type);
            relationship = String.format(relationship,
                    StringHelper.getFirstNonEmptyElseDefault(person.getDescription(), person.getFullName()));
        }
        TextComponent textComponent = new TextComponent(relationship);
        textComponent.addGender(gender);
        return textComponent;
    }

    @Override
    public TextComponent getFemaleRelationship(Person person, int type) {
        return getRelationship(person, type, Gender.FEMININE);
    }

    @Override
    public TextComponent getMaleRelationship(Person person, int type) {
        return getRelationship(person, type, Gender.MASCULINE);
    }

    @Override
    public TextComponent getUnspecificRelationship() {
        int type = r.getInt(2, Constant.RELATIONSHIP.length);
        return getUnspecificRelationship(type);
    }

    @Override
    public TextComponent getUnspecificRelationship(int type) {
        return getUnspecificRelationship(type, Gender.NEUTRAL);
    }

    @Override
    public TextComponent getUnspecificRelationship(int type, Gender gender) {
        String relationship = ResourceGetter.with(r).getString(Constant.UNSPECIFIC_RELATIONSHIP, type);
        TextComponent textComponent = new TextComponent(relationship);
        textComponent.addGender(gender);
        return textComponent;
    }

    @Override
    public TextComponent getFemaleUnspecificRelationship(int type) {
        return getUnspecificRelationship(type, Gender.FEMININE);
    }

    @Override
    public TextComponent getMaleUnspecificRelationship(int type) {
        return getUnspecificRelationship(type, Gender.MASCULINE);
    }
}
