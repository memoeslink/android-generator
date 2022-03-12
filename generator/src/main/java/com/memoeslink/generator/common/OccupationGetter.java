package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

public class OccupationGetter extends Getter implements OccupationDefiner {

    public OccupationGetter() {
        super();
    }

    public OccupationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getOccupation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getOccupation(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleOccupation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleOccupation(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleOccupation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleOccupation(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessOccupation() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessOccupation(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getJobTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleJobTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleJobTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessJobTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getSimpleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleSimpleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleSimpleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessSimpleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessFantasyClass() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleHonorific() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleHonorific() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessHonorific() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleRoyalTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleRoyalTitle() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGenderlessRoyalTitle() {
        return Database.DEFAULT_VALUE;
    }
}
