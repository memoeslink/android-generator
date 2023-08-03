package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

import java.util.Locale;

public class UsernameGetter extends com.memoeslink.generator.international.UsernameGetter {
    private final NameGetter nameGetter;
    private final NounGetter nounGetter;
    private final AdjectiveGetter adjectiveGetter;

    public UsernameGetter() {
        super();
        nameGetter = new NameGetter();
        nounGetter = new NounGetter();
        adjectiveGetter = new AdjectiveGetter();
    }

    public UsernameGetter(Randomizer r) {
        super(r);
        nameGetter = new NameGetter(r);
        nounGetter = new NounGetter(r);
        adjectiveGetter = new AdjectiveGetter(r);
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
        return getPatternUsername(r.getBoolean() ? nameGetter.getMaleForename() : nameGetter.getFemaleForename(), nameGetter.getSurname(), new Locale("es"), r);
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
}
