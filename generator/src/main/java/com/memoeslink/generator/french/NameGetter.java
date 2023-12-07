package com.memoeslink.generator.french;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

public class NameGetter extends com.memoeslink.generator.base.NameGetter implements NameDefiner {

    public NameGetter() {
        super();
    }

    public NameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getFemaleForename() {
        String name = r.getBoolean() ? ResourceGetter.with(r).getString(Constant.FEMALE_FORENAMES) :
                getFemaleForename(r.getIntInRange(1, Database.countFrenchFemaleNames()));
        return !StringHelper.containsAny(name, " ", "-") ? name : getFemaleForename();
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.selectFrenchFemaleName(id);
    }

    @Override
    public String getMaleForename() {
        String name = r.getBoolean() ? ResourceGetter.with(r).getString(Constant.MALE_FORENAMES) :
                getMaleForename(r.getIntInRange(1, Database.countFrenchMaleNames()));
        return !StringHelper.containsAny(name, " ", "-") ? name : getMaleForename();
    }

    @Override
    public String getMaleForename(int id) {
        return Database.selectFrenchMaleName(id);
    }

    @Override
    public String getFemaleDoubleBarrelledForename() {
        return getFemaleForename() + Separator.HYPHEN.getCharacter() + getFemaleForename();
    }

    @Override
    public String getFemaleDoubleBarrelledForename(int startId, int endId) {
        return getFemaleForename(startId) + Separator.HYPHEN.getCharacter() + getFemaleForename(endId);
    }

    @Override
    public String getMaleDoubleBarrelledForename() {
        return getMaleForename() + Separator.HYPHEN.getCharacter() + getMaleForename();
    }

    @Override
    public String getMaleDoubleBarrelledForename(int startId, int endId) {
        return getMaleForename(startId) + Separator.HYPHEN.getCharacter() + getMaleForename(endId);
    }

    @Override
    public String getFemaleDoubleForename() {
        return getFemaleForename() + Separator.SPACE.getCharacter() + getFemaleForename();
    }

    @Override
    public String getFemaleDoubleForename(int startId, int endId) {
        return getFemaleForename(startId) + Separator.SPACE.getCharacter() + getFemaleForename(endId);
    }

    @Override
    public String getMaleDoubleForename() {
        return getMaleForename() + Separator.SPACE.getCharacter() + getMaleForename();
    }

    @Override
    public String getMaleDoubleForename(int startId, int endId) {
        return getMaleForename(startId) + Separator.SPACE.getCharacter() + getMaleForename(endId);
    }

    @Override
    public String getGivenName() {
        return r.getBoolean() ? getMaleGivenName() : getFemaleGivenName();
    }

    @Override
    public String getFemaleGivenName() {
        return switch (r.getInt(3)) {
            case 1 -> getFemaleDoubleBarrelledForename();
            case 2 -> this.getFemaleDoubleForename();
            default -> getFemaleForename();
        };
    }

    @Override
    public String getMaleGivenName() {
        return switch (r.getInt(3)) {
            case 1 -> this.getMaleDoubleBarrelledForename();
            case 2 -> this.getMaleDoubleForename();
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
    public String getDualFemaleForename() {
        return switch (r.getInt(2)) {
            case 1 -> getFemaleDoubleBarrelledForename();
            default -> this.getFemaleDoubleForename();
        };
    }

    @Override
    public String getDualFemaleForename(int startId, int endInd) {
        return switch (r.getInt(2)) {
            case 1 -> this.getFemaleDoubleBarrelledForename(startId, endInd);
            default -> getFemaleDoubleForename(startId, endInd);
        };
    }

    @Override
    public String getDualMaleForename() {
        return switch (r.getInt(2)) {
            case 1 -> this.getMaleDoubleBarrelledForename();
            default -> this.getMaleDoubleForename();
        };
    }

    @Override
    public String getDualMaleForename(int startId, int endInd) {
        return switch (r.getInt(2)) {
            case 1 -> this.getMaleDoubleBarrelledForename(startId, endInd);
            default -> getMaleDoubleForename(startId, endInd);
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
