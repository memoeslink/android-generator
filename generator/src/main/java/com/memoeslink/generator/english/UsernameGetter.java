package com.memoeslink.generator.english;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.ResourceGetter;

import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

import java.util.Locale;
import java.util.Map;
import java.util.function.Supplier;

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
        Map<String, Supplier<String>> patternMapping = Map.of(
                "forename", () -> nameGetter.getForename().toLowerCase(),
                "surname", () -> nameGetter.getSurname().toLowerCase(),
                "job", () -> {
                    String job = ResourceGetter.with(r).getStrFromResBundle(Locale.ENGLISH, "job.position");
                    return StringHelper.normalize(job).toLowerCase();
                },
                "denominator", () -> {
                    String denominator = ResourceGetter.with(r).getStrFromResBundle(Locale.ENGLISH, "organization.denominator");
                    return StringHelper.normalize(denominator).toLowerCase();
                },
                "letter", () -> String.valueOf(ResourceGetter.with(r).getChar(com.memoeslink.generator.base.Constant.UPPERCASE_ALPHABET)),
                "number", () -> String.valueOf(r.getInt(1, 10)),
                "year", () -> {
                    int year = com.memoeslink.generator.common.Constant.STARTING_YEAR + r.getInt(-100, 101);
                    return String.valueOf(year);
                }
        );
        return getPatternUsername(ResourceGetter.with(r).getString(com.memoeslink.generator.base.Constant.USERNAME_PATTERNS), patternMapping);
    }

    @Override
    public String getWordBasedUsername() {
        String start = Database.selectEnglishWord(r.getIntInRange(1, Database.countEnglishWords()));
        String end = r.getBoolean() ? Database.selectEnglishWord(r.getIntInRange(1, Database.countEnglishWords())) : "";
        return getWordBasedUsername(r, start, end);
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
