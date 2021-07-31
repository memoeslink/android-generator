package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

public class Base {
    protected Article article;
    protected String base;
    protected Gender gender;
    protected boolean plural;

    public Base() {
        this(Article.INDEFINITE, StringHelper.EMPTY, Gender.UNDEFINED, false);
    }

    public Base(Article article, String base) {
        this(article, base, Gender.UNDEFINED, false);
    }

    public Base(Article article, String base, Gender gender, boolean plural) {
        this.article = article;
        this.base = base;
        this.gender = gender != null ? gender : Gender.UNDEFINED;
        this.plural = plural;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender != null ? gender : Gender.UNDEFINED;
    }

    public boolean isPlural() {
        return plural;
    }

    public void setPlural(boolean plural) {
        this.plural = plural;
    }

    public String getBaseWithArticle() {
        return StringHelper.prependIfNotBlank(base, article.getArticle() + Separator.SPACE.getCharacter());
    }

    public String getBaseWithIndefiniteArticle() {
        return StringHelper.prependIfNotBlank(base, article.getIndefiniteArticle() + Separator.SPACE.getCharacter());
    }
}