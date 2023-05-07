package com.memoeslink.generator.french;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.international.Shaper;

public class NameGetter extends com.memoeslink.generator.common.NameGetter implements com.memoeslink.generator.common.NameDefiner, NameDefiner {
    private final com.memoeslink.generator.international.NameGetter nameGetter;

    public NameGetter() {
        super();
        nameGetter = new com.memoeslink.generator.international.NameGetter();
    }

    public NameGetter(Randomizer r) {
        super(r);
        nameGetter = new com.memoeslink.generator.international.NameGetter(r);
    }

    @Override
    public String getFemaleForename() {
        String name = r.getBoolean() ? ResourceGetter.with(r).getString(Constant.FEMALE_FORENAMES) :
                getFemaleForename(r.getInt(1, Database.countFrenchFemaleNames()));
        return !StringHelper.containsAny(name, " ", "-") ? name : getFemaleForename();
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.selectFrenchFemaleName(id);
    }

    @Override
    public String getMaleForename() {
        String name = r.getBoolean() ? ResourceGetter.with(r).getString(Constant.MALE_FORENAMES) :
                getMaleForename(r.getInt(1, Database.countFrenchMaleNames()));
        return !StringHelper.containsAny(name, " ", "-") ? name : getMaleForename();
    }

    @Override
    public String getMaleForename(int id) {
        return Database.selectFrenchMaleName(id);
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
        return switch (r.getInt(3)) {
            case 1 -> getDoubleBarrelledFemaleForename();
            case 2 -> getDoubleFemaleForename();
            default -> getFemaleForename();
        };
    }

    @Override
    public String getMaleGivenName() {
        return switch (r.getInt(3)) {
            case 1 -> getDoubleBarrelledMaleForename();
            case 2 -> getDoubleMaleForename();
            default -> getMaleForename();
        };
    }

    @Override
    public String getSurname() {
        return ResourceGetter.with(r).getString(Constant.SURNAMES);
    }

    @Override
    public String getSurname(int id) {
        return ResourceGetter.with(r).getString(Constant.SURNAMES, id);
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
        return switch (r.getInt(4)) {
            case 1 -> getFemaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2 -> (getDualFemaleForename() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 3 -> (getDualFemaleForename() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            default -> getFemaleSimpleName();
        };
    }

    @Override
    public String getMaleFullName() {
        return switch (r.getInt(4)) {
            case 1 -> getMaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            case 2 -> (getDualMaleForename() + Separator.SPACE.getCharacter() +
                    getSurname());
            case 3 -> (getDualMaleForename() + Separator.SPACE.getCharacter() +
                    getDualSurname());
            default -> getMaleSimpleName();
        };
    }

    @Override
    public String getFullName() {
        return r.getBoolean() ? getMaleFullName() : getFemaleFullName();
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
        return nameGetter.getFemaleFrequencyForename(type);
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
    public String getSecretName() {
        return nameGetter.getSecretName();
    }

    @Override
    public String getUsername() {
        return Database.selectUsername(r.getInt(1, Database.countUsernames()));
    }

    @Override
    public String getCompositeUsername() {
        return nameGetter.getCompositeUsername();
    }

    @Override
    public String getDerivedUsername() {
        return nameGetter.getDerivedUsername();
    }

    @Override
    public String getAnonymousName() {
        return nameGetter.getAnonymousName();
    }

    @Override
    public String getAnonymousAnimal() {
        return nameGetter.getAnonymousAnimal();
    }

    @Override
    public String getDualFemaleForename() {
        return switch (r.getInt(2)) {
            case 1 -> getDoubleBarrelledFemaleForename();
            default -> getDoubleFemaleForename();
        };
    }

    @Override
    public String getDualFemaleForename(int startId, int endInd) {
        return switch (r.getInt(2)) {
            case 1 -> getDoubleBarrelledFemaleForename(startId, endInd);
            default -> getDoubleFemaleForename(startId, endInd);
        };
    }

    @Override
    public String getDualMaleForename() {
        return switch (r.getInt(2)) {
            case 1 -> getDoubleBarrelledMaleForename();
            default -> getDoubleMaleForename();
        };
    }

    @Override
    public String getDualMaleForename(int startId, int endInd) {
        return switch (r.getInt(2)) {
            case 1 -> getDoubleBarrelledMaleForename(startId, endInd);
            default -> getDoubleMaleForename(startId, endInd);
        };
    }

    @Override
    public String getDoubleBarrelledSurname() {
        return getSurname() + Separator.HYPHEN.getCharacter() + getSurname();
    }

    @Override
    public String getDoubleBarrelledSurname(int startId, int endId) {
        return getSurname(startId) + Separator.HYPHEN.getCharacter() + getSurname(endId);
    }
}
