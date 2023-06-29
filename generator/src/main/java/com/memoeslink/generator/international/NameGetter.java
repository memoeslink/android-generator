package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

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
        return getAnyGetter().getFemaleForename();
    }

    @Override
    public String getFemaleForename(int id) {
        return getAnyGetter().getFemaleForename(id);
    }

    @Override
    public String getMaleForename() {
        return getAnyGetter().getMaleForename();
    }

    @Override
    public String getMaleForename(int id) {
        return getAnyGetter().getMaleForename(id);
    }

    @Override
    public String getFemalePatronymic() {
        return getAnyGetter().getFemalePatronymic();
    }

    @Override
    public String getFemalePatronymic(int id) {
        return getAnyGetter().getFemalePatronymic(id);
    }

    @Override
    public String getMalePatronymic() {
        return getAnyGetter().getMalePatronymic();
    }

    @Override
    public String getMalePatronymic(int id) {
        return getAnyGetter().getMalePatronymic(id);
    }

    @Override
    public String getDoubleBarrelledFemaleForename() {
        return getAnyGetter().getDoubleBarrelledFemaleForename();
    }

    @Override
    public String getDoubleBarrelledFemaleForename(int startId, int endId) {
        return getAnyGetter().getDoubleBarrelledFemaleForename(startId, endId);
    }

    @Override
    public String getDoubleBarrelledMaleForename() {
        return getAnyGetter().getDoubleBarrelledMaleForename();
    }

    @Override
    public String getDoubleBarrelledMaleForename(int startId, int endId) {
        return getAnyGetter().getDoubleBarrelledMaleForename(startId, endId);
    }

    @Override
    public String getDoubleFemaleForename() {
        return getAnyGetter().getDoubleFemaleForename();
    }

    @Override
    public String getDoubleFemaleForename(int startId, int endId) {
        return getAnyGetter().getDoubleFemaleForename(startId, endId);
    }

    @Override
    public String getDoubleMaleForename() {
        return getAnyGetter().getDoubleMaleForename();
    }

    @Override
    public String getDoubleMaleForename(int startId, int endId) {
        return getAnyGetter().getDoubleMaleForename(startId, endId);
    }

    @Override
    public String getGivenName() {
        return getAnyGetter().getGivenName();
    }

    @Override
    public String getFemaleGivenName() {
        return getAnyGetter().getFemaleGivenName();
    }

    @Override
    public String getMaleGivenName() {
        return getAnyGetter().getMaleGivenName();
    }

    @Override
    public String getSurname() {
        return getAnyGetter().getSurname();
    }

    @Override
    public String getSurname(int id) {
        return getAnyGetter().getSurname(id);
    }

    @Override
    public String getDualSurname() {
        return getAnyGetter().getDualSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return getAnyGetter().getDualSurname(startId, endId);
    }

    @Override
    public String getFemaleSimpleName() {
        return getAnyGetter().getFemaleSimpleName();
    }

    @Override
    public String getFemaleSimpleName(int forenameId, int surnameId) {
        return getAnyGetter().getFemaleSimpleName(forenameId, surnameId);
    }

    @Override
    public String getMaleSimpleName() {
        return getAnyGetter().getMaleSimpleName();
    }

    @Override
    public String getMaleSimpleName(int forenameId, int surnameId) {
        return getAnyGetter().getMaleSimpleName(forenameId, surnameId);
    }

    @Override
    public String getSimpleName() {
        return r.getBoolean() ? getMaleSimpleName() : getFemaleSimpleName();
    }

    @Override
    public String getFemaleFullName() {
        return getAnyGetter().getFemaleFullName();
    }

    @Override
    public String getMaleFullName() {
        return getAnyGetter().getMaleFullName();
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

    @Override
    public String getUsername() {
        return getAnyGetter().getUsername();
    }

    @Override
    public String getCompositeUsername() {
        return getAnyGetter().getCompositeUsername();
    }

    @Override
    public String getDerivedUsername() {
        return getAnyGetter().getDerivedUsername();
    }

    @Override
    public String getPatternUsername() {
        return getAnyGetter().getPatternUsername();
    }

    @Override
    public String getAnonymousName() {
        return getAnyGetter().getAnonymousName();
    }

    @Override
    public String getAnonymousAnimal() {
        return getAnyGetter().getAnonymousAnimal();
    }

    public com.memoeslink.generator.common.NameGetter getAnyGetter() {
        return switch (ResourceGetter.with(r).getString(com.memoeslink.generator.common.Constant.SUPPORTED_LOCALES)) {
            case "ar" -> new com.memoeslink.generator.arabic.NameGetter(r);
            case "de" -> new com.memoeslink.generator.german.NameGetter(r);
            case "en" -> new com.memoeslink.generator.english.NameGetter(r);
            case "es" -> new com.memoeslink.generator.spanish.NameGetter(r);
            case "es_MX" -> new com.memoeslink.generator.spanish.mexico.NameGetter(r);
            case "fr" -> new com.memoeslink.generator.french.NameGetter(r);
            case "it" -> new com.memoeslink.generator.italian.NameGetter(r);
            case "hi" -> new com.memoeslink.generator.hindi.NameGetter(r);
            case "ja" -> new com.memoeslink.generator.japanese.NameGetter(r);
            case "pt" -> new com.memoeslink.generator.portuguese.NameGetter(r);
            case "ru" -> new com.memoeslink.generator.russian.NameGetter(r);
            default -> new com.memoeslink.generator.common.NameGetter(r);
        };
    }
}
