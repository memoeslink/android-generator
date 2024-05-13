package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import org.memoeslink.StringHelper;

public class UsernameGetter extends Getter implements UsernameDefiner {

    public UsernameGetter() {
        super();
    }

    public UsernameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getEmptyUsername() {
        return StringHelper.EMPTY;
    }

    @Override
    public String getUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getCompositeUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDerivedUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPatternUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAdminUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getWordBasedUsername() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAnonymousName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getAnonymousAnimal() {
        return Database.DEFAULT_VALUE;
    }
}
