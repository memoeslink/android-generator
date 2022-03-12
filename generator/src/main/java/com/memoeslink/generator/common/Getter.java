package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public abstract class Getter {
    protected Randomizer r;

    protected Getter() {
        r = new Randomizer();
    }

    protected Getter(Randomizer r) {
        this.r = r != null ? r : new Randomizer();
    }
}
