package com.memoeslink.generator.japanese;

import com.memoeslink.common.Randomizer;
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
    public String getFemaleDoubleBarrelledForename() {
        return super.getFemaleDoubleBarrelledForename();
    }

    @Override
    public String getFemaleDoubleBarrelledForename(int startId, int endId) {
        return super.getFemaleDoubleBarrelledForename(startId, endId);
    }

    @Override
    public String getMaleDoubleBarrelledForename() {
        return super.getMaleDoubleBarrelledForename();
    }

    @Override
    public String getMaleDoubleBarrelledForename(int startId, int endId) {
        return super.getMaleDoubleBarrelledForename(startId, endId);
    }

    @Override
    public String getFemaleDoubleForename() {
        return super.getFemaleDoubleForename();
    }

    @Override
    public String getFemaleDoubleForename(int startId, int endId) {
        return super.getFemaleDoubleForename(startId, endId);
    }

    @Override
    public String getMaleDoubleForename() {
        return super.getMaleDoubleForename();
    }

    @Override
    public String getMaleDoubleForename(int startId, int endId) {
        return super.getMaleDoubleForename(startId, endId);
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
        return super.getDualSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return super.getDualSurname(startId, endId);
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
