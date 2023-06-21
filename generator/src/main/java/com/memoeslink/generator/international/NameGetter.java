package com.memoeslink.generator.international;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.IntegerHelper;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.common.TextProcessor;

public class NameGetter extends com.memoeslink.generator.common.NameGetter implements NameDefiner {

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
    public String getFemaleDefinedForename() {
        return TextProcessor.feminize(getDefinedForename(r));
    }

    @Override
    public String getFemaleDefinedForename(int type) {
        return TextProcessor.feminize(getDefinedForename(type, r));
    }

    @Override
    public String getMaleDefinedForename() {
        return getDefinedForename(r);
    }

    @Override
    public String getMaleDefinedForename(int type) {
        return getDefinedForename(type, r);
    }

    @Override
    public String getDefinedFamilyName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getDefinedFamilyName(type);
    }

    @Override
    public String getDefinedFamilyName(int type) {
        type = IntegerHelper.defaultIndex(type, Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return StringHelper.capitalizeFirst(ResourceGetter.with(r).getString(Constant.GENERATED_NAME_START[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_NAME_MIDDLE[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_FAMILY_NAME_SUFFIX[type]));
    }

    @Override
    public String getFemaleDefinedFullName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getFemaleDefinedForename(type) + Separator.SPACE.getCharacter() + getDefinedFamilyName(type);
    }

    @Override
    public String getMaleDefinedFullName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getMaleDefinedForename(type) + Separator.SPACE.getCharacter() + getDefinedFamilyName(type);
    }

    @Override
    public String getDefinedFullName() {
        return r.getBoolean() ? getMaleDefinedFullName() : getFemaleDefinedFullName();
    }

    @Override
    public String getFemaleIterativeForename() {
        return TextProcessor.feminize(getIterativeName(r.getInt(1, 6), r));
    }

    @Override
    public String getMaleIterativeForename() {
        return getIterativeName(r.getInt(1, 6), r);
    }

    @Override
    public String getIterativeFamilyName() {
        return getIterativeName(r.getInt(1, 4), r);
    }

    @Override
    public String getFemaleIterativeFullName() {
        return getFemaleIterativeForename() + Separator.SPACE.getCharacter() + getIterativeFamilyName();
    }

    @Override
    public String getMaleIterativeFullName() {
        return getMaleIterativeForename() + Separator.SPACE.getCharacter() + getIterativeFamilyName();
    }

    @Override
    public String getIterativeFullName() {
        return r.getBoolean() ? getMaleIterativeFullName() : getFemaleIterativeFullName();
    }

    @Override
    public String getFemalePatternForename() {
        String ending = ResourceGetter.with(r).getString(Constant.FEMALE_NAME_SUFFIX);
        return TextProcessor.feminize(getPatternName(r), ending);
    }

    @Override
    public String getMalePatternForename() {
        return getPatternName(r);
    }

    @Override
    public String getPatternFamilyName() {
        return StringHelper.weld(getPatternName(r), ResourceGetter.with(r).getString(Constant.FAMILY_NAME_SUFFIX));
    }

    @Override
    public String getFemalePatternFullName() {
        return getFemalePatternForename() + Separator.SPACE.getCharacter() + getPatternFamilyName();
    }

    @Override
    public String getMalePatternFullName() {
        return getMalePatternForename() + Separator.SPACE.getCharacter() + getPatternFamilyName();
    }

    @Override
    public String getPatternFullName() {
        return r.getBoolean() ? getMalePatternFullName() : getFemalePatternFullName();
    }

    @Override
    public String getFemaleFrequencyForename() {
        return TextProcessor.feminizeWithDefaultWhen(getMaleFrequencyForename(), "e", "o");
    }

    @Override
    public String getFemaleFrequencyForename(int type) {
        return TextProcessor.feminizeWithDefaultWhen(getMaleFrequencyForename(type), "e", "o");
    }

    @Override
    public String getMaleFrequencyForename() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getMaleFrequencyForename(type);
    }

    @Override
    public String getMaleFrequencyForename(int type) {
        return getFrequencyName(Constant.WEIGHTED_LETTERS[type], r.getInt(3, 11), r);
    }

    @Override
    public String getFrequencyFamilyName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getFrequencyFamilyName(type);
    }

    @Override
    public String getFrequencyFamilyName(int type) {
        return getFrequencyName(Constant.WEIGHTED_LETTERS[type], r.getInt(2, 13), r);
    }

    @Override
    public String getFemaleFrequencyFullName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getFemaleFrequencyForename(type) + Separator.SPACE.getCharacter() + getFrequencyFamilyName(type);
    }

    @Override
    public String getMaleFrequencyFullName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getMaleFrequencyForename(type) + Separator.SPACE.getCharacter() + getFrequencyFamilyName(type);
    }

    @Override
    public String getFrequencyFullName() {
        return r.getBoolean() ? getMaleFrequencyFullName() : getFemaleFrequencyFullName();
    }

    @Override
    public String getFemalePreformedForename() {
        return TextProcessor.feminize(getMalePreformedForename());
    }

    @Override
    public String getFemalePreformedForename(Shaper shaper) {
        return TextProcessor.feminize(getMalePreformedForename(shaper));
    }

    @Override
    public String getMalePreformedForename() {
        Shaper shaper = r.getElement(Shaper.values());
        return getMalePreformedForename(shaper);
    }

    @Override
    public String getMalePreformedForename(Shaper shaper) {
        shaper = shaper != null ? shaper : Shaper.DEFAULT;
        return getPreformedName(shaper.getShape(), r.getInt(3, 11), r);
    }

    @Override
    public String getPreformedFamilyName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getPreformedFamilyName(shaper);
    }

    @Override
    public String getPreformedFamilyName(Shaper shaper) {
        shaper = shaper != null ? shaper : Shaper.DEFAULT;
        return getPreformedName(shaper.getShape(), r.getInt(3, 7), r);
    }

    @Override
    public String getFemalePreformedFullName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getFemalePreformedForename(shaper) + Separator.SPACE.getCharacter() + getPreformedFamilyName(shaper);
    }

    @Override
    public String getMalePreformedFullName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getMalePreformedForename(shaper) + Separator.SPACE.getCharacter() + getPreformedFamilyName(shaper);
    }

    @Override
    public String getPreformedFullName() {
        return r.getBoolean() ? getMalePreformedFullName() : getFemalePreformedFullName();
    }

    @Override
    public String getFemaleMarkovForename() {
        String ending = ResourceGetter.with(r).getString(Constant.FEMALE_NAME_SUFFIX);
        return TextProcessor.feminizeOnVowelEnd(getMarkovName(3, 10, r), ending);
    }

    @Override
    public String getMaleMarkovForename() {
        return getMarkovName(3, 10, r);
    }

    @Override
    public String getMarkovFamilyName() {
        return getMarkovName(3, 8, r);
    }

    @Override
    public String getFemaleMarkovFullName() {
        return getFemaleMarkovForename() + Separator.SPACE.getCharacter() + getMarkovFamilyName();
    }

    @Override
    public String getMaleMarkovFullName() {
        return getMaleMarkovForename() + Separator.SPACE.getCharacter() + getMarkovFamilyName();
    }

    @Override
    public String getMarkovFullName() {
        return r.getBoolean() ? getMaleMarkovFullName() : getFemaleMarkovFullName();
    }

    @Override
    public String getSecretName() {
        return getSecretName(r.getInt(2, 11), r);
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
