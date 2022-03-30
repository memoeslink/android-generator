package com.memoeslink.generator.common;

import android.content.Context;

import com.memoeslink.common.Randomizer;

public class Binder extends ContextWrapper {
    protected final Randomizer r;

    public Binder(Context context) {
        this(context, null);
    }

    public Binder(Context context, Long seed) {
        super(context);
        r = new Randomizer(seed);
    }

    public void bindSeed(Long seed) {
        r.bindSeed(seed);
    }

    public void unbindSeed() {
        r.unbindSeed();
    }
}
