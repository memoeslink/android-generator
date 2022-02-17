package com.memoeslink.generator.english;

import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.international.Shaper;

import java.main.common.Randomizer;

public final class NameGetter extends com.memoeslink.generator.common.NameGetter implements com.memoeslink.generator.common.NameDefiner, NameDefiner {
    private final NounGetter nounGetter;
    private final AdjectiveGetter adjectiveGetter;
    private final com.memoeslink.generator.international.NameGetter nameGetter;

    public NameGetter() {
        super();
        nounGetter = new NounGetter();
        adjectiveGetter = new AdjectiveGetter();
        nameGetter = new com.memoeslink.generator.international.NameGetter();
    }

    public NameGetter(Randomizer r) {
        super(r);
        nounGetter = new NounGetter(r);
        adjectiveGetter = new AdjectiveGetter(r);
        nameGetter = new com.memoeslink.generator.international.NameGetter(r);
    }

    @Override
    public String getFemaleForename() {
        return getFemaleForename(r.getInt(1, Database.countEnglishFemaleNames()));
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.selectEnglishFemaleName(id);
    }

    @Override
    public String getMaleForename() {
        return getMaleForename(r.getInt(1, Database.countEnglishMaleNames()));
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
        switch (r.getInt(4)) {
            case 1:
                return getDoubleBarrelledFemaleForename();
            case 2:
                return getFemaleForename() + Separator.SPACE.getCharacter() + getFemaleMiddleName();
            case 3:
                return getFemaleForename() + Separator.SPACE.getCharacter() + getMiddleNameInitial();
            case 0:
            default:
                return getFemaleForename();
        }
    }

    @Override
    public String getMaleGivenName() {
        switch (r.getInt(4)) {
            case 1:
                return getDoubleBarrelledMaleForename();
            case 2:
                return getMaleForename() + Separator.SPACE.getCharacter() + getMaleMiddleName();
            case 3:
                return getMaleForename() + Separator.SPACE.getCharacter() + getMiddleNameInitial();
            case 0:
            default:
                return getMaleForename();
        }
    }

    @Override
    public String getSurname() {
        return getSurname(r.getInt(1, Database.countEnglishSurnames()));
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
    public String getFullName() {
        return r.getBoolean() ? getMaleFullName() : getFemaleFullName();
    }

    @Override
    public String getFemaleFullName() {
        switch (r.getInt(8)) {
            case 1:
                return getFemaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2:
                return (getDoubleBarrelledFemaleForename() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 3:
                return (getFemaleForename() + Separator.SPACE.getCharacter() +
                        getFemaleMiddleName() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 4:
                return (getFemaleForename() + Separator.SPACE.getCharacter() +
                        getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 5:
                return (getFemaleForename() + Separator.SPACE.getCharacter() +
                        getFemaleMiddleName() + Separator.SPACE.getCharacter() +
                        getDualSurname());
            case 6:
                return (getFemaleForename() + Separator.SPACE.getCharacter() +
                        getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                        getDualSurname());
            case 7:
                return getFemaleName();
            case 0:
            default:
                return getFemaleSimpleName();
        }
    }

    @Override
    public String getMaleFullName() {
        switch (r.getInt(8)) {
            case 1:
                return getMaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2:
                return (getDoubleBarrelledMaleForename() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 3:
                return (getMaleForename() + Separator.SPACE.getCharacter() +
                        getMaleMiddleName() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 4:
                return (getMaleForename() + Separator.SPACE.getCharacter() +
                        getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                        getSurname());
            case 5:
                return (getMaleForename() + Separator.SPACE.getCharacter() +
                        getMaleMiddleName() + Separator.SPACE.getCharacter() +
                        getDualSurname());
            case 6:
                return (getMaleForename() + Separator.SPACE.getCharacter() +
                        getMiddleNameInitial() + Separator.SPACE.getCharacter() +
                        getDualSurname());
            case 7:
                return getMaleName();
            case 0:
            default:
                return getFemaleSimpleName();
        }
    }

    @Override
    public String getFemaleDefinedForename() {
        return nameGetter.getFemaleDefinedForename();
    }

    @Override
    public String getFemaleDefinedForename(int type) {
        return nameGetter.getFemaleDefinedForename(type);
    }

    @Override
    public String getMaleDefinedForename() {
        return nameGetter.getMaleDefinedForename();
    }

    @Override
    public String getMaleDefinedForename(int type) {
        return nameGetter.getMaleDefinedForename(type);
    }

    @Override
    public String getDefinedFamilyName() {
        return nameGetter.getDefinedFamilyName();
    }

    @Override
    public String getDefinedFamilyName(int type) {
        return nameGetter.getDefinedFamilyName(type);
    }

    @Override
    public String getFemaleDefinedFullName() {
        return nameGetter.getFemaleDefinedFullName();
    }

    @Override
    public String getMaleDefinedFullName() {
        return nameGetter.getMaleDefinedFullName();
    }

    @Override
    public String getDefinedFullName() {
        return nameGetter.getDefinedFullName();
    }

    @Override
    public String getFemaleIterativeForename() {
        return nameGetter.getFemaleIterativeForename();
    }

    @Override
    public String getMaleIterativeForename() {
        return nameGetter.getMaleIterativeForename();
    }

    @Override
    public String getIterativeFamilyName() {
        return nameGetter.getIterativeFamilyName();
    }

    @Override
    public String getFemaleIterativeFullName() {
        return nameGetter.getFemaleIterativeFullName();
    }

    @Override
    public String getMaleIterativeFullName() {
        return nameGetter.getMaleIterativeFullName();
    }

    @Override
    public String getIterativeFullName() {
        return nameGetter.getIterativeFullName();
    }

    @Override
    public String getFemalePatternForename() {
        return nameGetter.getFemalePatternForename();
    }

    @Override
    public String getMalePatternForename() {
        return nameGetter.getMalePatternForename();
    }

    @Override
    public String getPatternFamilyName() {
        return nameGetter.getPatternFamilyName();
    }

    @Override
    public String getFemalePatternFullName() {
        return nameGetter.getFemalePatternFullName();
    }

    @Override
    public String getMalePatternFullName() {
        return nameGetter.getMalePatternFullName();
    }

    @Override
    public String getPatternFullName() {
        return nameGetter.getPatternFullName();
    }

    @Override
    public String getFemaleFrequencyForename() {
        return nameGetter.getFemaleFrequencyForename();
    }

    @Override
    public String getFemaleFrequencyForename(int type) {
        return nameGetter.getFemaleFrequencyForename();
    }

    @Override
    public String getMaleFrequencyForename() {
        return nameGetter.getMaleFrequencyForename();
    }

    @Override
    public String getMaleFrequencyForename(int type) {
        return nameGetter.getMaleFrequencyForename(type);
    }

    @Override
    public String getFrequencyFamilyName() {
        return nameGetter.getFrequencyFamilyName();
    }

    @Override
    public String getFrequencyFamilyName(int type) {
        return nameGetter.getFrequencyFamilyName(type);
    }

    @Override
    public String getFemaleFrequencyFullName() {
        return nameGetter.getFemaleFrequencyFullName();
    }

    @Override
    public String getMaleFrequencyFullName() {
        return nameGetter.getMaleFrequencyFullName();
    }

    @Override
    public String getFrequencyFullName() {
        return nameGetter.getFrequencyFullName();
    }

    @Override
    public String getFemalePreformedForename() {
        return nameGetter.getFemalePreformedForename();
    }

    @Override
    public String getFemalePreformedForename(Shaper shaper) {
        return nameGetter.getFemalePreformedForename(shaper);
    }

    @Override
    public String getMalePreformedForename() {
        return nameGetter.getMalePreformedForename();
    }

    @Override
    public String getMalePreformedForename(Shaper shaper) {
        return nameGetter.getMalePreformedForename(shaper);
    }

    @Override
    public String getPreformedFamilyName() {
        return nameGetter.getPreformedFamilyName();
    }

    @Override
    public String getPreformedFamilyName(Shaper shaper) {
        return nameGetter.getPreformedFamilyName(shaper);
    }

    @Override
    public String getFemalePreformedFullName() {
        return nameGetter.getFemalePreformedFullName();
    }

    @Override
    public String getMalePreformedFullName() {
        return nameGetter.getMalePreformedFullName();
    }

    @Override
    public String getPreformedFullName() {
        return nameGetter.getPreformedFullName();
    }

    @Override
    public String getFemaleMarkovForename() {
        return nameGetter.getFemaleMarkovForename();
    }

    @Override
    public String getMaleMarkovForename() {
        return nameGetter.getMaleMarkovForename();
    }

    @Override
    public String getMarkovFamilyName() {
        return nameGetter.getMarkovFamilyName();
    }

    @Override
    public String getFemaleMarkovFullName() {
        return nameGetter.getFemaleMarkovFullName();
    }

    @Override
    public String getMaleMarkovFullName() {
        return nameGetter.getMaleMarkovFullName();
    }

    @Override
    public String getMarkovFullName() {
        return nameGetter.getMarkovFullName();
    }

    @Override
    public String getUsername() {
        return Database.selectUsername(r.getInt(1, Database.countUsernames()));
    }

    @Override
    public String getCompositeUsername() {
        String adjective = StringHelper.removeAll(adjectiveGetter.getAdjective(), "[^a-zA-Z0-9\\\\s]");
        String noun = StringHelper.removeAll(nounGetter.getNoun(), "[^a-zA-Z0-9\\\\s]");
        return getCompositeUsername(adjective, noun, r);
    }

    @Override
    public String getDerivedUsername() {
        return getDerivedUsername(Database.selectFamilyName(r.getInt(1, Database.countFamilyNames())), r);
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
        return ResourceGetter.with(r).getUpperCase() + StringHelper.EMPTY + Separator.DOT.getCharacter();
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
