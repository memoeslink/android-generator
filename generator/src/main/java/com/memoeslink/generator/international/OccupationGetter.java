package com.memoeslink.generator.international;

import com.memoeslink.generator.common.OccupationDefiner;

import java.main.common.Randomizer;

public final class OccupationGetter extends com.memoeslink.generator.common.OccupationGetter implements OccupationDefiner {

    public OccupationGetter() {
        super();
    }

    public OccupationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getOccupation() {
        return getAnyGetter().getOccupation();
    }

    @Override
    public String getOccupation(int id) {
        return getAnyGetter().getOccupation(id);
    }

    @Override
    public String getFemaleOccupation() {
        return getAnyGetter().getFemaleOccupation();
    }

    @Override
    public String getFemaleOccupation(int id) {
        return getAnyGetter().getFemaleOccupation(id);
    }

    @Override
    public String getMaleOccupation() {
        return getAnyGetter().getMaleOccupation();
    }

    @Override
    public String getMaleOccupation(int id) {
        return getAnyGetter().getMaleOccupation(id);
    }

    @Override
    public String getGenderlessOccupation() {
        return getAnyGetter().getGenderlessOccupation();
    }

    @Override
    public String getGenderlessOccupation(int id) {
        return getAnyGetter().getGenderlessOccupation(id);
    }

    @Override
    public String getJobTitle() {
        return getAnyGetter().getJobTitle();
    }

    @Override
    public String getFemaleJobTitle() {
        return getAnyGetter().getFemaleJobTitle();
    }

    @Override
    public String getMaleJobTitle() {
        return getAnyGetter().getMaleJobTitle();
    }

    @Override
    public String getGenderlessJobTitle() {
        return getAnyGetter().getGenderlessJobTitle();
    }

    @Override
    public String getSimpleFantasyClass() {
        return getAnyGetter().getSimpleFantasyClass();
    }

    @Override
    public String getFemaleSimpleFantasyClass() {
        return getAnyGetter().getFemaleSimpleFantasyClass();
    }

    @Override
    public String getMaleSimpleFantasyClass() {
        return getAnyGetter().getMaleSimpleFantasyClass();
    }

    @Override
    public String getGenderlessSimpleFantasyClass() {
        return getAnyGetter().getGenderlessSimpleFantasyClass();
    }

    @Override
    public String getFantasyClass() {
        return getAnyGetter().getFantasyClass();
    }

    @Override
    public String getFemaleFantasyClass() {
        return getAnyGetter().getFemaleFantasyClass();
    }

    @Override
    public String getMaleFantasyClass() {
        return getAnyGetter().getMaleFantasyClass();
    }

    @Override
    public String getGenderlessFantasyClass() {
        return getAnyGetter().getGenderlessFantasyClass();
    }

    @Override
    public String getFemaleHonorific() {
        return getAnyGetter().getFemaleHonorific();
    }

    @Override
    public String getMaleHonorific() {
        return getAnyGetter().getMaleHonorific();
    }

    @Override
    public String getGenderlessHonorific() {
        return getAnyGetter().getGenderlessHonorific();
    }

    @Override
    public String getFemaleRoyalTitle() {
        return getAnyGetter().getFemaleRoyalTitle();
    }

    @Override
    public String getMaleRoyalTitle() {
        return getAnyGetter().getMaleRoyalTitle();
    }

    @Override
    public String getGenderlessRoyalTitle() {
        return getAnyGetter().getGenderlessRoyalTitle();
    }

    public com.memoeslink.generator.common.OccupationGetter getAnyGetter() {
        switch (r.getInt(2)) {
            case 0:
                return new com.memoeslink.generator.english.OccupationGetter(r);
            case 1:
                return new com.memoeslink.generator.spanish.OccupationGetter(r);
        }
        return new com.memoeslink.generator.common.OccupationGetter(r);
    }
}
