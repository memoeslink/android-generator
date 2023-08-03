package com.memoeslink.generator.english;

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
        String adjective = StringHelper.removeAll(adjectiveGetter.getAdjective(), "[^a-zA-Z0-9\\\\s]");
        String noun = StringHelper.removeAll(nounGetter.getNoun(), "[^a-zA-Z0-9\\\\s]");
        return getCompositeUsername(adjective, noun, r);
    }

    @Override
    public String getDerivedUsername() {
        return getDerivedUsername(Database.selectFamilyName(r.getIntInRange(1, Database.countFamilyNames())), r);
    }

    @Override
    public String getPatternUsername() {
        return getPatternUsername(r.getBoolean() ? nameGetter.getMaleForename() : nameGetter.getFemaleForename(), nameGetter.getSurname(), Locale.ENGLISH, r);
    }

    @Override
    public String getAnonymousName() {
        String adjective = StringHelper.removeAll(adjectiveGetter.getAdjective(), "[^a-zA-Z0-9\\\\s]");
        String noun = StringHelper.removeAll(nounGetter.getNoun(), "[^a-zA-Z0-9\\\\s]");
        return StringHelper.joinWithSpace(adjective, noun);
    }

    @Override
    public String getAnonymousAnimal() {
        return "Anonymous" + Separator.SPACE.getCharacter() +
                StringHelper.capitalize(ResourceGetter.with(r).getString(Constant.ANONYMOUS_ANIMALS));
    }
}
