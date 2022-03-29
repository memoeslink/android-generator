package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public class Maker {

    public static String makeColor(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return String.format("#%06X", (0xFFFFFF & -7829368));
        Randomizer r = new Randomizer(LongHelper.getSeed(s));
        return String.format("#%06x", r.getInt(0xFFFFFF + 1));
    }

    public static String getColor(String s) {
        Randomizer r = new Randomizer(LongHelper.getSeed(s));
        return ResourceGetter.with(r).getString(Constant.COLORS);
    }
}
