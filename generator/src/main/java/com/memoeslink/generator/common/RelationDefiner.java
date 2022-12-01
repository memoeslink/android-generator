package com.memoeslink.generator.common;

public interface RelationDefiner {

    public TextComponent getRelationship(Person person);

    public TextComponent getRelationship(Person person, int type);

    public TextComponent getRelationship(Person person, int type, Gender gender);

    public TextComponent getFemaleRelationship(Person person, int type);

    public TextComponent getMaleRelationship(Person person, int type);

    public TextComponent getUnspecificRelationship();

    public TextComponent getUnspecificRelationship(int type);

    public TextComponent getUnspecificRelationship(int type, Gender gender);

    public TextComponent getFemaleUnspecificRelationship(int type);

    public TextComponent getMaleUnspecificRelationship(int type);
}
