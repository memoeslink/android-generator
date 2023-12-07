package com.memoeslink.generator.spanish.mexico;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

import org.memoeslink.Separator;

public class NameGetter extends com.memoeslink.generator.spanish.NameGetter {

    public NameGetter() {
        super();
    }

    public NameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getFemaleForename() {
        return ResourceGetter.with(r).getString(Constant.FEMALE_FORENAMES);
    }

    @Override
    public String getFemaleForename(int id) {
        return ResourceGetter.with(r).getString(Constant.MALE_FORENAMES, id);
    }

    @Override
    public String getMaleForename() {
        return ResourceGetter.with(r).getString(Constant.MALE_FORENAMES);
    }

    @Override
    public String getMaleForename(int id) {
        return ResourceGetter.with(r).getString(Constant.MALE_FORENAMES, id);
    }

    @Override
    public String getFemaleDoubleBarrelledForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleBarrelledForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleBarrelledForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleBarrelledForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGivenName() {
        return r.getBoolean() ? getMaleGivenName() : getFemaleGivenName();
    }

    @Override
    public String getFemaleGivenName() {
        return getFemaleForename();
    }

    @Override
    public String getMaleGivenName() {
        return getMaleForename();
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
        return getSurname() + Separator.SPACE.getCharacter() + getSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return getSurname(startId) + Separator.SPACE.getCharacter() + getSurname(endId);
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
        return switch (r.getInt(2)) {
            case 1 -> getFemaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            default -> getFemaleSimpleName();
        };
    }

    @Override
    public String getMaleFullName() {
        return switch (r.getInt(2)) {
            case 1 -> getMaleForename() + Separator.SPACE.getCharacter() + getDualSurname();
            default -> getMaleSimpleName();
        };
    }
}
