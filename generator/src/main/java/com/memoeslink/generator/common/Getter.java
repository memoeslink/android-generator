package com.memoeslink.generator.common;

import java.main.common.Randomizer;

public abstract class Getter {
    protected Randomizer r;

    protected Getter() {
        r = new Randomizer();
    }

    protected Getter(Randomizer r) {
        this.r = r != null ? r : new Randomizer();
    }
}
