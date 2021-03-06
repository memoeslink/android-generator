package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.international.Shaper;

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
        return getFemaleForename(r.getInt(1, Database.countHispanicFemaleNames()));
    }

    @Override
    public String getFemaleForename(int id) {
        String name = Database.selectHispanicFemaleName(id);
        return TextProcessor.removeNameStart(name);
    }

    @Override
    public String getMaleForename() {
        return getMaleForename(r.getInt(1, Database.countHispanicMaleNames()));
    }

    @Override
    public String getMaleForename(int id) {
        String name = Database.selectHispanicMaleName(id);
        return TextProcessor.removeNameStart(name);
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
        switch (r.getInt(3)) {
            case 1:
                return getDoubleFemaleForename();
            case 2:
                return getFemaleForenames();
            case 0:
            default:
                return getFemaleForename();
        }
    }

    @Override
    public String getMaleGivenName() {
        switch (r.getInt(3)) {
            case 1:
                return getDoubleMaleForename();
            case 2:
                return getMaleForenames();
            case 0:
            default:
                return getMaleForename();
        }
    }

    @Override
    public String getSurname() {
        return getSurname(r.getInt(1, Database.countHispanicSurnames()));
    }

    @Override
    public String getSurname(int id) {
        return Database.selectHispanicSurname(id);
    }

    @Override
    public String getDualSurname() {
        return getDoubleSurname();
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return getDoubleSurname(startId, endId);
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
        switch (r.getInt(4)) {
            case 1:
                return getFemaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 2:
                return getDoubleFemaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 3:
                return getFemaleForenames() + Separator.SPACE.getCharacter() + getSurnames();
            case 0:
            default:
                return getFemaleSimpleName();
        }
    }

    @Override
    public String getMaleFullName() {
        switch (r.getInt(4)) {
            case 1:
                return getMaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 2:
                return getDoubleMaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 3:
                return getMaleForenames() + Separator.SPACE.getCharacter() + getSurnames();
            case 0:
            default:
                return getMaleSimpleName();
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
    public String getUsername() {
        return Database.selectUsername(r.getInt(1, Database.countUsernames()));
    }

    @Override
    public String getCompositeUsername() {
        String adjective;
        String noun;

        switch (r.getInt(6)) {
            case 0:
                adjective = adjectiveGetter.getAdjective();
                noun = nounGetter.getNoun();
                break;
            case 1:
                adjective = adjectiveGetter.getPluralAdjective();
                noun = nounGetter.getPluralNoun();
                break;
            case 2:
                adjective = adjectiveGetter.getFemaleAdjective();
                noun = nounGetter.getFemaleNoun();
                break;
            case 3:
                adjective = adjectiveGetter.getPluralFemaleAdjective();
                noun = nounGetter.getPluralFemaleNoun();
                break;
            case 4:
                adjective = adjectiveGetter.getMaleAdjective();
                noun = nounGetter.getMaleNoun();
                break;
            case 5:
                adjective = adjectiveGetter.getPluralMaleAdjective();
                noun = nounGetter.getPluralMaleNoun();
                break;
            default:
                adjective = Database.DEFAULT_VALUE;
                noun = Database.DEFAULT_VALUE;
                break;
        }
        return getCompositeUsername(noun, adjective, r);
    }

    @Override
    public String getDerivedUsername() {
        return getDerivedUsername(Database.selectFamilyName(r.getInt(1, Database.countFamilyNames())), r);
    }

    @Override
    public String getAnonymousName() {
        String adjective;
        String noun;

        switch (r.getInt(3)) {
            case 0:
                adjective = adjectiveGetter.getAdjective();
                noun = nounGetter.getNoun();
                break;
            case 1:
                adjective = adjectiveGetter.getFemaleAdjective();
                noun = nounGetter.getFemaleNoun();
                break;
            case 2:
                adjective = adjectiveGetter.getMaleAdjective();
                noun = nounGetter.getMaleNoun();
                break;
            default:
                adjective = Database.DEFAULT_VALUE;
                noun = Database.DEFAULT_VALUE;
                break;
        }
        return StringHelper.joinWithSpace(noun, adjective);
    }

    @Override
    public String getAnonymousAnimal() {
        String animal = ResourceGetter.with(r).getString(Constant.ANONYMOUS_ANIMALS);
        animal = StringHelper.capitalizeFirst(animal);
        return animal + Separator.SPACE.getCharacter() + (StringHelper.endsWith(animal, "a") ? "an??nima" : "an??nimo");
    }

    @Override
    public String getForenames(Gender gender) {
        StringBuilder sb = new StringBuilder();
        float[] probabilities = {1.0F, 0.8F, 0.125F, 0.05F, 0.0125F};

        for (float probability : probabilities) {
            if (probability >= r.getFloat()) {
                if (sb.length() > 0)
                    sb.append(Separator.SPACE.getCharacter());
                sb.append(getForename(gender));
            } else
                break;
        }
        return sb.toString();
    }

    @Override
    public String getFemaleForenames() {
        return getForenames(Gender.FEMININE);
    }

    @Override
    public String getMaleForenames() {
        return getForenames(Gender.MASCULINE);
    }

    @Override
    public String getDoubleSurname() {
        return getSurname() + Separator.SPACE.getCharacter() + getSurname();
    }

    @Override
    public String getDoubleSurname(int startId, int endId) {
        return getSurname(startId) + Separator.SPACE.getCharacter() + getSurname(endId);
    }

    @Override
    public String getCompoundSurname() {
        return getCompoundSurname(r.getInt(1, Database.countHispanicCompoundSurnames()));
    }

    @Override
    public String getCompoundSurname(int id) {
        return Database.selectHispanicCompoundSurname(id);
    }

    @Override
    public String getSurnames() {
        switch (r.getInt(100)) {
            case 0:
                return getCompoundSurname() + Separator.SPACE.getCharacter() + getSurname();
            case 1:
                return getSurname() + Separator.SPACE.getCharacter() + getCompoundSurname();
            default:
                return getDualSurname();
        }
    }
}
