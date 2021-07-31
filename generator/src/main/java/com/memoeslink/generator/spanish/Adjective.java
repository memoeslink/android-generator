package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.Gender;

public class Adjective extends Base {

    public Adjective() {
        super();
    }

    public Adjective(Article article, String adjective) {
        super(article, adjective);
    }

    public Adjective(Article article, String adjective, Gender gender, boolean plural) {
        super(article, adjective, gender, plural);
    }
}