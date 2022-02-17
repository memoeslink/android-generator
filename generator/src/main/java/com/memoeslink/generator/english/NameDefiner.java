package com.memoeslink.generator.english;

import com.memoeslink.generator.common.ResourceGetter;

import java.main.common.Randomizer;

public interface NameDefiner extends com.memoeslink.generator.common.NameDefiner {

    public String getFemaleMiddleName();

    public String getFemaleMiddleName(int id);

    public String getMaleMiddleName();

    public String getMaleMiddleName(int id);

    public String getMiddleNameInitial();

    public String getDoubleBarrelledSurname();

    public String getDoubleBarrelledSurname(int startId, int endId);

    default String getGenerationalSuffix(Randomizer r) {
        r = r != null ? r : new Randomizer();
        return r.getBoolean() ? ResourceGetter.with(r).getString(com.memoeslink.generator.common.Constant.ROMAN_NUMERALS) :
                ResourceGetter.with(r).getString(Constant.GENERATIONAL_SUFFIX);
    }

    public String getFemaleName();

    public String getMaleName();
}
