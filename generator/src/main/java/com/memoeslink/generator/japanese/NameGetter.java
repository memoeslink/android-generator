package com.memoeslink.generator.japanese;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

import org.memoeslink.Separator;

public class NameGetter extends com.memoeslink.generator.base.NameGetter implements NameDefiner {

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
    public String getDoubleBarrelledFemaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleBarrelledFemaleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleBarrelledMaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleBarrelledMaleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleFemaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleFemaleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleMaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDoubleMaleForename(int startId, int endId) {
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
        return getFemaleForename();
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
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleSimpleName() {
        return getSurname() + Separator.SPACE.getCharacter() + getFemaleForename();
    }

    @Override
    public String getFemaleSimpleName(int forenameId, int surnameId) {
        return getSurname(forenameId) + Separator.SPACE.getCharacter() + getFemaleForename(surnameId);
    }

    @Override
    public String getMaleSimpleName() {
        return getSurname() + Separator.SPACE.getCharacter() + getMaleForename();
    }

    @Override
    public String getMaleSimpleName(int forenameId, int surnameId) {
        return getSurname(forenameId) + Separator.SPACE.getCharacter() + getMaleForename(surnameId);
    }

    @Override
    public String getSimpleName() {
        return r.getBoolean() ? getMaleSimpleName() : getFemaleSimpleName();
    }

    @Override
    public String getFemaleFullName() {
        return getFemaleSimpleName();
    }

    @Override
    public String getMaleFullName() {
        return getMaleSimpleName();
    }

    @Override
    public String getFullName() {
        return r.getBoolean() ? getMaleFullName() : getFemaleFullName();
    }

    @Override
    public String getUsername() {
        return Database.selectUsername(r.getIntInRange(1, Database.countUsernames()));
    }

    @Override
    public String getFemaleForenameSuffix() {
        return Separator.HYPHEN.getCharacter() +
                ResourceGetter.with(r).getString(Constant.FEMALE_FORENAME_SUFFIXES);
    }

    @Override
    public String getMaleForenameSuffix() {
        return Separator.HYPHEN.getCharacter() +
                ResourceGetter.with(r).getString(Constant.MALE_FORENAME_SUFFIXES);
    }

    @Override
    public String getFemaleForenameWithSuffix() {
        return getFemaleForename() + getFemaleForenameSuffix();
    }

    @Override
    public String getMaleForenameWithSuffix() {
        return getMaleForename() + getMaleForenameSuffix();
    }
}
