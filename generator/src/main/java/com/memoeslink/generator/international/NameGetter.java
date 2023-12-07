package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.NameGetterFactory;
import com.memoeslink.generator.common.NameMapper;

import org.memoeslink.Separator;

public class NameGetter extends com.memoeslink.generator.base.NameGetter {

    public NameGetter() {
        super();
    }

    public NameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getFemaleForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleForename();
    }

    @Override
    public String getFemaleForename(int id) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleForename(id);
    }

    @Override
    public String getMaleForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleForename();
    }

    @Override
    public String getMaleForename(int id) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleForename(id);
    }

    @Override
    public String getFemalePatronymic() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemalePatronymic();
    }

    @Override
    public String getFemalePatronymic(int id) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemalePatronymic(id);
    }

    @Override
    public String getMalePatronymic() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMalePatronymic();
    }

    @Override
    public String getMalePatronymic(int id) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMalePatronymic(id);
    }

    @Override
    public String getFemaleDoubleBarrelledForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleDoubleBarrelledForename();
    }

    @Override
    public String getFemaleDoubleBarrelledForename(int startId, int endId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleDoubleBarrelledForename(startId, endId);
    }

    @Override
    public String getMaleDoubleBarrelledForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleDoubleBarrelledForename();
    }

    @Override
    public String getMaleDoubleBarrelledForename(int startId, int endId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleDoubleBarrelledForename(startId, endId);
    }

    @Override
    public String getFemaleDoubleForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleDoubleForename();
    }

    @Override
    public String getFemaleDoubleForename(int startId, int endId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleDoubleForename(startId, endId);
    }

    @Override
    public String getMaleDoubleForename() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleDoubleForename();
    }

    @Override
    public String getMaleDoubleForename(int startId, int endId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleDoubleForename(startId, endId);
    }

    @Override
    public String getGivenName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getGivenName();
    }

    @Override
    public String getFemaleGivenName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleGivenName();
    }

    @Override
    public String getMaleGivenName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleGivenName();
    }

    @Override
    public String getSurname() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getSurname();
    }

    @Override
    public String getSurname(int id) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getSurname(id);
    }

    @Override
    public String getDualSurname() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getDualSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getDualSurname(startId, endId);
    }

    @Override
    public String getFemaleSimpleName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleSimpleName();
    }

    @Override
    public String getFemaleSimpleName(int forenameId, int surnameId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleSimpleName(forenameId, surnameId);
    }

    @Override
    public String getMaleSimpleName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleSimpleName();
    }

    @Override
    public String getMaleSimpleName(int forenameId, int surnameId) {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleSimpleName(forenameId, surnameId);
    }

    @Override
    public String getSimpleName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getSimpleName();
    }

    @Override
    public String getFemaleFullName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getFemaleFullName();
    }

    @Override
    public String getMaleFullName() {
        return getAnyGetter(new Exception().getStackTrace()[0].getMethodName()).getMaleFullName();
    }

    @Override
    public String getFullName() {
        return switch (r.getInt(4)) {
            case 1 -> getFemaleFullName();
            case 2 -> getMaleFullName();
            case 3 ->
                    Database.selectForename(r.getIntInRange(1, Database.countForenames())) + Separator.SPACE.getCharacter() +
                            Database.selectSurname(r.getIntInRange(1, Database.countSurnames()));
            default -> Database.selectName(r.getIntInRange(1, Database.countNames())) +
                    Separator.SPACE.getCharacter() +
                    Database.selectFamilyName(r.getIntInRange(1, Database.countFamilyNames()));
        };
    }

    public com.memoeslink.generator.common.NameGetter getAnyGetter(String methodName) {
        String locale = r.getElement(NameMapper.getNameMapping().get(methodName));
        return NameGetterFactory.getNameGetter(locale);
    }
}
