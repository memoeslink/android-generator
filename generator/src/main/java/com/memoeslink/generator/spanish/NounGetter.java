package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.NounDefiner;

import org.memoeslink.Separator;
import org.memoeslink.StringHelper;

public final class NounGetter extends com.memoeslink.generator.common.NounGetter implements NounDefiner, com.memoeslink.generator.spanish.NounDefiner {

    public NounGetter() {
        super();
    }

    public NounGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getNoun() {
        Noun noun = getRefinedNoun();
        return !noun.isPlural() ? noun.getBase() : getNoun();
    }

    @Override
    public String getPluralNoun() {
        Noun noun = getRefinedNoun();
        return noun.isPlural() ? noun.getBase() : getPluralNoun();
    }

    @Override
    public String getFemaleNoun() {
        return getRefinedFemaleNoun().getBase();
    }

    @Override
    public String getPluralFemaleNoun() {
        return getRefinedPluralFemaleNoun().getBase();
    }

    @Override
    public String getMaleNoun() {
        return getRefinedMaleNoun().getBase();
    }

    @Override
    public String getPluralMaleNoun() {
        return getRefinedPluralMaleNoun().getBase();
    }

    @Override
    public String getNounWithArticle() {
        return getRefinedNoun().getBaseWithArticle();
    }

    @Override
    public String getPluralNounWithArticle() {
        return getRefinedPluralNoun().getBaseWithArticle();
    }

    @Override
    public String getFemaleNounWithArticle() {
        return getRefinedFemaleNoun().getBaseWithArticle();
    }

    @Override
    public String getPluralFemaleNounWithArticle() {
        return getRefinedPluralFemaleNoun().getBaseWithArticle();
    }

    @Override
    public String getMaleNounWithArticle() {
        return getRefinedMaleNoun().getBaseWithArticle();
    }

    @Override
    public String getPluralMaleNounWithArticle() {
        return getRefinedPluralMaleNoun().getBaseWithArticle();
    }

    @Override
    public String getNounWithIndefiniteArticle() {
        return getRefinedNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralNounWithIndefiniteArticle() {
        return getRefinedPluralNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getFemaleNounWithIndefiniteArticle() {
        return getRefinedFemaleNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralFemaleNounWithIndefiniteArticle() {
        return getRefinedPluralFemaleNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getMaleNounWithIndefiniteArticle() {
        return getRefinedMaleNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public String getPluralMaleNounWithIndefiniteArticle() {
        return getRefinedPluralMaleNoun().getBaseWithIndefiniteArticle();
    }

    @Override
    public Noun getRefinedNoun() {
        String noun = Database.selectSpanishNoun(r.getIntInRange(1, Database.countSpanishNouns()));

        for (Article article : Article.values()) {
            if (StringHelper.startsWith(noun, article.getArticle() + Separator.SPACE.getCharacter())) {
                String temp = StringHelper.removeFirst(noun, article.getArticle() + "\\s+");
                noun = temp.trim();

                if (article.getGender() == Gender.MASCULINE && StringHelper.startsWithAny(noun, "a", "á") && StringHelper.endsWith(noun, "a"))
                    return new Noun(article, noun, Gender.FEMININE, article.isPlural());
                return new Noun(article, noun, article.getGender(), article.isPlural());
            }
        }
        return new Noun(Article.INDEFINITE, noun);
    }

    @Override
    public Noun getRefinedPluralNoun() {
        Noun noun = getRefinedNoun();

        if (!noun.isPlural()) {
            noun.setBase(Pluralizer.pluralize(noun).getBase());
            noun.setPlural(true);
        }
        return noun;
    }

    @Override
    public Noun getRefinedFemaleNoun() {
        Noun noun = getRefinedNoun();
        return noun.getGender() == Gender.FEMININE && !noun.isPlural() ? noun : getRefinedFemaleNoun();
    }

    @Override
    public Noun getRefinedPluralFemaleNoun() {
        Noun noun = getRefinedPluralNoun();
        return noun.getGender() == Gender.FEMININE && noun.isPlural() ? noun : getRefinedPluralFemaleNoun();
    }

    @Override
    public Noun getRefinedMaleNoun() {
        Noun noun = getRefinedNoun();
        return noun.getGender() == Gender.MASCULINE && !noun.isPlural() ? noun : getRefinedMaleNoun();
    }

    @Override
    public Noun getRefinedPluralMaleNoun() {
        Noun noun = getRefinedPluralNoun();
        return noun.getGender() == Gender.MASCULINE && noun.isPlural() ? noun : getRefinedPluralMaleNoun();
    }
}
