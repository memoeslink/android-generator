package com.memoeslink.generator.common;

import android.graphics.Color;

import com.memoeslink.common.Randomizer;

import org.memoeslink.StringHelper;

public class Maker {

    private Maker() {
    }

    public static String getColor(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return String.format("#%06x", (0xFFFFFF & Color.GRAY));
        Randomizer r = new Randomizer(StringHelper.hashcode(s));
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }

    public static String getDefaultColor(String s) {
        Randomizer r = new Randomizer(StringHelper.hashcode(s));
        return ResourceGetter.with(r).getString(Constant.DEFAULT_COLORS);
    }
}
