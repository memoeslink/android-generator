package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public class Maker {

    public static String getColor(String s) {
        StringGenerator stringGenerator = new StringGenerator(null, LongHelper.getSeed(s));
        return stringGenerator.getStrColor(s);
    }

    public static String getDefaultColor(String s) {
        Randomizer r = new Randomizer(LongHelper.getSeed(s));
        return ResourceGetter.with(r).getString(Constant.DEFAULT_COLORS);
    }
}
