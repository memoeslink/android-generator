package com.memoeslink.generator.english;

import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.OccupationDefiner;
import com.memoeslink.generator.common.Randomizer;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

public final class OccupationGetter extends com.memoeslink.generator.common.OccupationGetter implements OccupationDefiner {

    public OccupationGetter() {
        super();
    }

    public OccupationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getOccupation() {
        return getOccupation(r.getInt(1, Database.countEnglishOccupations()));
    }

    @Override
    public String getOccupation(int id) {
        return Database.selectEnglishOccupation(id);
    }

    @Override
    public String getFemaleOccupation() {
        return TextProcessor.genderify(getOccupation(), Gender.FEMININE);
    }

    @Override
    public String getFemaleOccupation(int id) {
        return TextProcessor.genderify(getOccupation(id), Gender.FEMININE);
    }

    @Override
    public String getMaleOccupation() {
        return TextProcessor.genderify(getOccupation(), Gender.MASCULINE);
    }

    @Override
    public String getMaleOccupation(int id) {
        return TextProcessor.genderify(getOccupation(id), Gender.MASCULINE);
    }

    @Override
    public String getGenderlessOccupation() {
        return TextProcessor.genderify(getOccupation(), Gender.UNDEFINED);
    }

    @Override
    public String getGenderlessOccupation(int id) {
        return TextProcessor.genderify(getOccupation(id), Gender.UNDEFINED);
    }

    @Override
    public String getJobTitle() {
        String titleDescriptor = ResourceGetter.with(r).getString(Constant.TITLE_DESCRIPTOR);
        String titleJob = ResourceGetter.with(r).getString(Constant.TITLE_JOB);
        String titleLevel = ResourceGetter.with(r).getString(Constant.TITLE_LEVEL);
        return String.join(String.valueOf(Separator.SPACE.getCharacter()), titleDescriptor, titleLevel, titleJob);
    }

    @Override
    public String getFemaleJobTitle() {
        return getJobTitle();
    }

    @Override
    public String getMaleJobTitle() {
        return getJobTitle();
    }

    @Override
    public String getGenderlessJobTitle() {
        return getJobTitle();
    }

    @Override
    public String getFantasyClass() {
        String fantasyClass = ResourceGetter.with(r).getSplitString(Constant.CLASSES);
        fantasyClass = StringHelper.capitalizeFirst(fantasyClass);
        int level = r.getInt(1, 99);
        return String.format("LVL %d %s", level, fantasyClass);
    }

    @Override
    public String getFemaleFantasyClass() {
        String fantasyClass = getFantasyClass();
        return TextProcessor.genderify(fantasyClass, Gender.FEMININE);
    }

    @Override
    public String getMaleFantasyClass() {
        String fantasyClass = getFantasyClass();
        return TextProcessor.genderify(fantasyClass, Gender.MASCULINE);
    }

    @Override
    public String getGenderlessFantasyClass() {
        String fantasyClass = getFantasyClass();
        return TextProcessor.genderify(fantasyClass, Gender.UNDEFINED);
    }

    @Override
    public String getFemaleHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return TextProcessor.genderifyStr(honorific, Gender.FEMININE).getText();
    }

    @Override
    public String getMaleHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return TextProcessor.genderifyStr(honorific, Gender.MASCULINE).getText();
    }

    @Override
    public String getGenderlessHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return TextProcessor.genderifyStr(honorific, Gender.UNDEFINED).getText();
    }

    @Override
    public String getFemaleRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return TextProcessor.genderifyStr(honorific, Gender.FEMININE).getText();
    }

    @Override
    public String getMaleRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return TextProcessor.genderifyStr(honorific, Gender.MASCULINE).getText();
    }

    @Override
    public String getGenderlessRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return TextProcessor.genderifyStr(honorific, Gender.UNDEFINED).getText();
    }
}
