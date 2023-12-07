package com.memoeslink.generator.russian;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
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
        return r.getBoolean() ? ResourceGetter.with(r).getString(Constant.FEMALE_FORENAMES) :
                getFemaleForename(r.getIntInRange(1, Database.countRussianFemaleNames()));
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.selectRussianFemaleName(id);
    }

    @Override
    public String getMaleForename() {
        return r.getBoolean() ? ResourceGetter.with(r).getString(Constant.MALE_FORENAMES) :
                getMaleForename(r.getIntInRange(1, Database.countRussianMaleNames()));
    }

    @Override
    public String getMaleForename(int id) {
        return Database.selectRussianMaleName(id);
    }

    @Override
    public String getFemalePatronymic() {
        String name = getMaleForename();
        return getPatronymic(name, Gender.FEMININE);
    }

    @Override
    public String getFemalePatronymic(int id) {
        String name = getMaleForename(id);
        return getPatronymic(name, Gender.FEMININE);
    }

    @Override
    public String getMalePatronymic() {
        String name = getMaleForename();
        return getPatronymic(name, Gender.MASCULINE);
    }

    @Override
    public String getMalePatronymic(int id) {
        String name = getMaleForename(id);
        return getPatronymic(name, Gender.MASCULINE);
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
        return super.getDualSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return super.getDualSurname(startId, endId);
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
            case 1 -> getFemaleSimpleName();
            default -> (getFemaleForename() + Separator.SPACE.getCharacter() +
                    getFemalePatronymic() + Separator.SPACE.getCharacter() +
                    getSurname());
        };
    }

    @Override
    public String getMaleFullName() {
        return switch (r.getInt(2)) {
            case 1 -> getMaleSimpleName();
            default -> (getMaleForename() + Separator.SPACE.getCharacter() +
                    getMalePatronymic() + Separator.SPACE.getCharacter() +
                    getSurname());
        };
    }
}
