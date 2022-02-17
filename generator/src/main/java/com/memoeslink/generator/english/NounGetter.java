package com.memoeslink.generator.english;

import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.NounDefiner;
import com.memoeslink.generator.common.Separator;

import java.main.common.Randomizer;

import io.github.encryptorcode.pluralize.Pluralize;

public final class NounGetter extends com.memoeslink.generator.common.NounGetter implements NounDefiner {

    public NounGetter() {
        super();
    }

    public NounGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getNoun() {
        return Database.selectEnglishNoun(r.getInt(1, Database.countEnglishNouns()));
    }

    @Override
    public String getPluralNoun() {
        String noun = getNoun();

        if (Pluralize.isSingular(noun))
            noun = Pluralize.plural(noun);
        return noun;
    }

    @Override
    public String getFemaleNoun() {
        return getNoun();
    }

    @Override
    public String getPluralFemaleNoun() {
        return getPluralNoun();
    }

    @Override
    public String getMaleNoun() {
        return getNoun();
    }

    @Override
    public String getPluralMaleNoun() {
        return getPluralNoun();
    }

    @Override
    public String getNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getNoun();
    }

    @Override
    public String getPluralNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralNoun();
    }

    @Override
    public String getFemaleNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getFemaleNoun();
    }

    @Override
    public String getPluralFemaleNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralFemaleNoun();
    }

    @Override
    public String getMaleNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getMaleNoun();
    }

    @Override
    public String getPluralMaleNounWithArticle() {
        return "the" + Separator.SPACE.getCharacter() + getPluralMaleNoun();
    }

    @Override
    public String getNounWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getNoun());
    }

    @Override
    public String getPluralNounWithIndefiniteArticle() {
        return getPluralNoun();
    }

    @Override
    public String getFemaleNounWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getFemaleNoun());
    }

    @Override
    public String getPluralFemaleNounWithIndefiniteArticle() {
        return getPluralFemaleNoun();
    }

    @Override
    public String getMaleNounWithIndefiniteArticle() {
        return TextProcessor.adjustIndefiniteArticles("a/an" + Separator.SPACE.getCharacter() + getMaleNoun());
    }

    @Override
    public String getPluralMaleNounWithIndefiniteArticle() {
        return getPluralMaleNoun();
    }
}
