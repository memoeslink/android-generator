package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

import java.util.Locale;

public class NameGetter extends com.memoeslink.generator.base.NameGetter implements NameDefiner {
    private final NounGetter nounGetter;
    private final AdjectiveGetter adjectiveGetter;

    public NameGetter() {
        super();
        nounGetter = new NounGetter();
        adjectiveGetter = new AdjectiveGetter();
    }

    public NameGetter(Randomizer r) {
        super(r);
        nounGetter = new NounGetter(r);
        adjectiveGetter = new AdjectiveGetter(r);
    }

    @Override
    public String getFemaleForename() {
        return getFemaleForename(r.getIntInRange(1, Database.countHispanicFemaleNames()));
    }

    @Override
    public String getFemaleForename(int id) {
        String name = Database.selectHispanicFemaleName(id);
        return TextProcessor.removeNameStart(name);
    }

    @Override
    public String getMaleForename() {
        return getMaleForename(r.getIntInRange(1, Database.countHispanicMaleNames()));
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
        return switch (r.getInt(3)) {
            case 1 -> getDoubleFemaleForename();
            case 2 -> getFemaleForenames();
            default -> getFemaleForename();
        };
    }

    @Override
    public String getMaleGivenName() {
        return switch (r.getInt(3)) {
            case 1 -> getDoubleMaleForename();
            case 2 -> getMaleForenames();
            default -> getMaleForename();
        };
    }

    @Override
    public String getSurname() {
        return r.getInt(100) == 0 ? getSurnameFromName() : getSurname(r.getIntInRange(1, Database.countHispanicSurnames()));
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
    public String getSimpleName() {
        return r.getBoolean() ? getMaleSimpleName() : getFemaleSimpleName();
    }

    @Override
    public String getFemaleFullName() {
        return switch (r.getInt(4)) {
            case 1 -> getFemaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 2 -> getDoubleFemaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 3 -> getFemaleForenames() + Separator.SPACE.getCharacter() + getSurnames();
            default -> getFemaleSimpleName();
        };
    }

    @Override
    public String getMaleFullName() {
        return switch (r.getInt(4)) {
            case 1 -> getMaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 2 -> getDoubleMaleForename() + Separator.SPACE.getCharacter() + getSurnames();
            case 3 -> getMaleForenames() + Separator.SPACE.getCharacter() + getSurnames();
            default -> getMaleSimpleName();
        };
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
    public String getCompositeUsername() {
        String adjective;
        String noun;

        switch (r.getInt(4)) {
            case 0 -> {
                adjective = r.getBoolean() ? adjectiveGetter.getFemaleAdjective() :
                        adjectiveGetter.getCommonAdjective();
                noun = nounGetter.getFemaleNoun();
            }
            case 1 -> {
                adjective = r.getBoolean() ? adjectiveGetter.getPluralFemaleAdjective() :
                        adjectiveGetter.getPluralCommonAdjective();
                noun = nounGetter.getPluralFemaleNoun();
            }
            case 2 -> {
                adjective = r.getBoolean() ? adjectiveGetter.getMaleAdjective() :
                        adjectiveGetter.getCommonAdjective();
                noun = nounGetter.getMaleNoun();
            }
            case 3 -> {
                adjective = r.getBoolean() ? adjectiveGetter.getPluralMaleAdjective() :
                        adjectiveGetter.getPluralCommonAdjective();
                noun = nounGetter.getPluralMaleNoun();
            }
            default -> {
                adjective = Database.DEFAULT_VALUE;
                noun = Database.DEFAULT_VALUE;
            }
        }
        return getCompositeUsername(noun, adjective, r);
    }

    @Override
    public String getDerivedUsername() {
        return getDerivedUsername(Database.selectFamilyName(r.getIntInRange(1, Database.countFamilyNames())), r);
    }

    @Override
    public String getPatternUsername() {
        return getPatternUsername(r.getBoolean() ? getMaleForename() : getFemaleForename(), getSurname(), new Locale("es"), r);
    }

    @Override
    public String getAnonymousName() {
        String adjective;
        String noun;

        switch (r.getInt(3)) {
            case 0 -> {
                adjective = adjectiveGetter.getCommonAdjective();
                noun = nounGetter.getNoun();
            }
            case 1 -> {
                adjective = adjectiveGetter.getFemaleAdjective();
                noun = nounGetter.getFemaleNoun();
            }
            case 2 -> {
                adjective = adjectiveGetter.getMaleAdjective();
                noun = nounGetter.getMaleNoun();
            }
            default -> {
                adjective = Database.DEFAULT_VALUE;
                noun = Database.DEFAULT_VALUE;
            }
        }
        return StringHelper.joinWithSpace(noun, adjective);
    }

    @Override
    public String getAnonymousAnimal() {
        String animal = ResourceGetter.with(r).getString(Constant.ANONYMOUS_ANIMALS);
        animal = StringHelper.capitalizeFirst(animal);
        return animal + Separator.SPACE.getCharacter() + (StringHelper.endsWith(animal, "a") ? "anónima" : "anónimo");
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
        return getCompoundSurname(r.getIntInRange(1, Database.countHispanicCompoundSurnames()));
    }

    @Override
    public String getCompoundSurname(int id) {
        return Database.selectHispanicCompoundSurname(id);
    }

    @Override
    public String getSurnames() {
        return switch (r.getInt(100)) {
            case 0 -> getCompoundSurname() + Separator.SPACE.getCharacter() + getSurname();
            case 1 -> getSurname() + Separator.SPACE.getCharacter() + getCompoundSurname();
            default -> getDualSurname();
        };
    }
}
