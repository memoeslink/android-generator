package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.Gender;

public class Noun extends Base {

    public Noun() {
        super();
    }

    public Noun(Article article, String noun) {
        super(article, noun);
    }

    public Noun(Article article, String noun, Gender gender, boolean plural) {
        super(article, noun, gender, plural);
    }
}