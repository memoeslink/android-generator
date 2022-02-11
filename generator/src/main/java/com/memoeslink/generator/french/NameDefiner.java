package com.memoeslink.generator.french;

public interface NameDefiner extends com.memoeslink.generator.common.NameDefiner {

    public String getDualFemaleForename();

    public String getDualFemaleForename(int startId, int endInd);

    public String getDualMaleForename();

    public String getDualMaleForename(int startId, int endInd);

    public String getDoubleBarrelledSurname();

    public String getDoubleBarrelledSurname(int startId, int endId);
}
