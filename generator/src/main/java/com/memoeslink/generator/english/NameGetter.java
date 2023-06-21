package com.memoeslink.generator.english;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

import java.util.Locale;

public class NameGetter extends com.memoeslink.generator.international.NameGetter implements NameDefiner {
    private final NounGetter nounGetter;
    private final AdjectiveGetter adjectiveGetter;

    public NameGetter() {
        super();
        nounGetter = new NounGetter();
        adjectiveGetter = new AdjectiveGetter();
    }

    public NameGetter(Randomizer r) {
        super(r);
        nounGetter = new NounGetter(r);
        adjectiveGetter = new AdjectiveGetter(r);
    }

    @Override
    public String getFemaleForename() {
        return getFemaleForename(r.getIntInRange(1, Database.countEnglishFemaleNames()));
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.selectEnglishFemaleName(id);
    }

    @Override
    public String getMaleForename() {
        return getMaleForename(r.getIntInRange(1, Database.countEnglishMaleNames()));
    }

    @Override
    public String getMaleForename(int id) {
        return Database.selectEnglishMaleName(id);
    }

    @Override
    public String getDoubleBarrelledFemaleForename() {
        return getFemaleForename() + Separator.HYPHEN.getCharacter() + getFemaleForename();
    }

    @Override
    public String getDoubleBarrelledFemaleForename(int startId, int endId) {
        return getFemaleForename(startId) + Separator.HYPHEN.getCharacter() + getFemaleForename(endId);
    }

    @Override
    public String getDoubleBarrelledMaleForename() {
        return getMaleForename() + Separator.HYPHEN.getCharacter() + getMaleForename();
    }

    @Override
    public String getDoubleBarrelledMaleForename(int startId, int endId) {
        return getMaleForename(startId) + Separator.HYPHEN.getCharacter() + getMaleForename(endId);
    }

    @Override
    public String getDoubleFemaleForename() {
        return getFemaleForename() + Separator.SPACE.getCharacter() + getFemaleForename();
    }

    @Override
    public String getDoubleFemaleForename(int startId, int endId) {
        return getFemaleForename(startId) + Separator.SPACE.getCharacter() + getFemaleForename(endId);
    }

    @Override
    public String getDoubleMaleForename() {
        return getMaleForename() + Separator.SPACE.getCharacter() + getMaleForename();
    }

    @Override
    public String getDoubleMaleForename(int startId, int endId) {
        return getMaleForename(startId) + Separator.SPACE.getCharacter() + getMaleForename(endId);
    }

    @Override
    public String getGivenName() {
        return r.getBoolean() ? getMaleGivenName() : getFemaleGivenName();
    }

    @Override
    public String getFemaleGivenName() {
        return switch (r.getInt(4)) {
            case 1 -> getDoubleBarrelledFemaleForename();
            case 2 -> getFemaleForename() + Separator.SPACE.getCharacter() + getFemaleMiddleName();
            case 3 -> getFemaleForename() + Separator.SPACE.getCharacter() + getMiddleNameInitial();
            default -> getFemaleForename();
        };
    }

    @Override
    public String getMaleGivenName() {
        return switch (r.getInt(4)) {
            case 1 -> getDoubleBarrelledMaleForename();
            case 2 -> getMaleForename() + Separator.SPACE.getCharacter() + getMaleMiddleName();
            case 3 -> getMaleForename() + Separator.SPACE.getCharacter() + getMiddleNameInitial();
            default -> getMaleForename();
        };
    }

    @Override
    public String getSurname() {
        return getSurname(r.getIntInRange(1, Database.countEnglishSurnames()));
    }

    @Override
    public String getSurname(int id) {
        return Database.selectEnglishSurname(id);
    }

    @Override
    public String getDualSurname() {
        return getDoubleBarrelledSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return getDoubleBarrelledSurname(startId, endId);
    }

    @Override
    public String getFemaleSimpleName() {
        return getFemaleForename() + Separator.SPACE.getCharacter() + getSurname();
    }

    @Override
    public String getFemaleSimpleName(int forenameId, int surnameId) {
        return getFemaleForename(forenameId) + Separator.SPACE.getCharacter() + getSurname(surnameId);
    }

    @Override
    public String getMaleSimpleName() {
        return getMaleForename() + Separator.SPACE.getCharacter() + getSurname();
    }

    @Override
    public String getMaleSimpleName(int forenameId, int surnameId) {
        return getMaleForename(forenameId) + Separator.SPACE.getCharacter() + getSurname(surnameId);
    }

    @Override
    public String getSimpleName() {
        return r.getBoolean() ? getMaleSimpleName() : getFemaleSimpleName();
    }

    @Override
    public String getFemaleFullName() {
        return switch (r.getInt(8)) {
            case 1 -> getFemaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2 -> (getDoubleBarrelledFemaleForename() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 3 -> (getFemaleForename() + Separator.SPACE.getCharacter() +
                    getFemaleMiddleName() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 4 -> (getFemaleForename() + Separator.SPACE.getCharacter() +
                    getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 5 -> (getFemaleForename() + Separator.SPACE.getCharacter() +
                    getFemaleMiddleName() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            case 6 -> (getFemaleForename() + Separator.SPACE.getCharacter() +
                    getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            case 7 -> getFemaleName();
            default -> getFemaleSimpleName();
        };
    }

    @Override
    public String getMaleFullName() {
        return switch (r.getInt(8)) {
            case 1 -> getMaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2 -> (getDoubleBarrelledMaleForename() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 3 -> (getMaleForename() + Separator.SPACE.getCharacter() +
                    getMaleMiddleName() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 4 -> (getMaleForename() + Separator.SPACE.getCharacter() +
                    getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 5 -> (getMaleForename() + Separator.SPACE.getCharacter() +
                    getMaleMiddleName() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            case 6 -> (getMaleForename() + Separator.SPACE.getCharacter() +
                    getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            case 7 -> getMaleName();
            default -> getFemaleSimpleName();
        };
    }

    @Override
    public String getUsername() {
        return Database.selectUsername(r.getIntInRange(1, Database.countUsernames()));
    }

    @Override
    public String getCompositeUsername() {
        String adjective = StringHelper.removeAll(adjectiveGetter.getAdjective(), "[^a-zA-Z0-9\\\\s]");
        String noun = StringHelper.removeAll(nounGetter.getNoun(), "[^a-zA-Z0-9\\\\s]");
        return getCompositeUsername(adjective, noun, r);
    }

    @Override
    public String getDerivedUsername() {
        return getDerivedUsername(Database.selectFamilyName(r.getIntInRange(1, Database.countFamilyNames())), r);
    }

    @Override
    public String getPatternUsername() {
        return getPatternUsername(r.getBoolean() ? getMaleForename() : getFemaleForename(), getSurname(), Locale.ENGLISH, r);
    }

    @Override
    public String getAnonymousName() {
        String adjective = StringHelper.removeAll(adjectiveGetter.getAdjective(), "[^a-zA-Z0-9\\\\s]");
        String noun = StringHelper.removeAll(nounGetter.getNoun(), "[^a-zA-Z0-9\\\\s]");
        return StringHelper.joinWithSpace(adjective, noun);
    }

    @Override
    public String getAnonymousAnimal() {
        return "Anonymous" + Separator.SPACE.getCharacter() +
                StringHelper.capitalize(ResourceGetter.with(r).getString(Constant.ANONYMOUS_ANIMALS));
    }

    @Override
    public String getFemaleMiddleName() {
        return getFemaleForename();
    }

    @Override
    public String getFemaleMiddleName(int id) {
        return getFemaleForename(id);
    }

    @Override
    public String getMaleMiddleName() {
        return getMaleForename();
    }

    @Override
    public String getMaleMiddleName(int id) {
        return getMaleForename(id);
    }

    @Override
    public String getMiddleNameInitial() {
        return ResourceGetter.with(r).getChar(Constant.UPPERCASE_ALPHABET) + StringHelper.EMPTY + Separator.DOT.getCharacter();
    }

    @Override
    public String getDoubleBarrelledSurname() {
        return getSurname() + Separator.HYPHEN.getCharacter() + getSurname();
    }

    @Override
    public String getDoubleBarrelledSurname(int startId, int endId) {
        return getSurname(startId) + Separator.HYPHEN.getCharacter() + getSurname(endId);
    }

    @Override
    public String getFemaleName() {
        return getFemaleFullName() + Separator.SPACE.getCharacter() + getGenerationalSuffix(r);
    }

    @Override
    public String getMaleName() {
        return getMaleFullName() + Separator.SPACE.getCharacter() + getGenerationalSuffix(r);
    }
}
