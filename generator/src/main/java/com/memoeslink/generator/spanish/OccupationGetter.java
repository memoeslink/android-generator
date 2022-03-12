package com.memoeslink.generator.spanish;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.Database;
import com.memoeslink.generator.common.Gender;
import com.memoeslink.generator.common.OccupationDefiner;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.common.TextProcessor;

public final class OccupationGetter extends com.memoeslink.generator.common.OccupationGetter implements OccupationDefiner {
    public static final String FANTASY_CLASS_FORMAT = "%s de nivel %d";

    public OccupationGetter() {
        super();
    }

    public OccupationGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getOccupation() {
        return getOccupation(r.getInt(1, Database.countSpanishOccupations()));
    }

    @Override
    public String getOccupation(int id) {
        return Database.selectSpanishOccupation(id);
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
        return String.join(String.valueOf(Separator.SPACE.getCharacter()), titleJob, "de", titleLevel, titleDescriptor);
    }

    @Override
    public String getFemaleJobTitle() {
        String title = getJobTitle();
        return TextProcessor.genderify(title, Gender.FEMININE);
    }

    @Override
    public String getMaleJobTitle() {
        String title = getJobTitle();
        return TextProcessor.genderify(title, Gender.MASCULINE);
    }

    @Override
    public String getGenderlessJobTitle() {
        String title = getJobTitle();
        return TextProcessor.genderify(title, Gender.UNDEFINED);
    }

    @Override
    public String getSimpleFantasyClass() {
        String fantasyClass = ResourceGetter.with(r).getSplitString(Constant.CLASSES);
        return StringHelper.capitalizeFirst(fantasyClass);
    }

    @Override
    public String getFemaleSimpleFantasyClass() {
        String fantasyClass = ResourceGetter.with(r).getSplitString(Constant.CLASSES);
        fantasyClass = TextProcessor.genderify(fantasyClass, Gender.FEMININE);
        return StringHelper.capitalizeFirst(fantasyClass);
    }

    @Override
    public String getMaleSimpleFantasyClass() {
        String fantasyClass = ResourceGetter.with(r).getSplitString(Constant.CLASSES);
        fantasyClass = TextProcessor.genderify(fantasyClass, Gender.FEMININE);
        return StringHelper.capitalizeFirst(fantasyClass);
    }

    @Override
    public String getGenderlessSimpleFantasyClass() {
        String fantasyClass = ResourceGetter.with(r).getSplitString(Constant.CLASSES);
        fantasyClass = TextProcessor.genderify(fantasyClass, Gender.UNDEFINED);
        return StringHelper.capitalizeFirst(fantasyClass);
    }

    @Override
    public String getFantasyClass() {
        String fantasyClass = getSimpleFantasyClass();
        int level = r.getInt(1, 99);
        return String.format(FANTASY_CLASS_FORMAT, fantasyClass, level);
    }

    @Override
    public String getFemaleFantasyClass() {
        String fantasyClass = getFemaleSimpleFantasyClass();
        int level = r.getInt(1, 99);
        return String.format(FANTASY_CLASS_FORMAT, fantasyClass, level);
    }

    @Override
    public String getMaleFantasyClass() {
        String fantasyClass = getMaleSimpleFantasyClass();
        int level = r.getInt(1, 99);
        return String.format(FANTASY_CLASS_FORMAT, fantasyClass, level);
    }

    @Override
    public String getGenderlessFantasyClass() {
        String fantasyClass = getGenderlessSimpleFantasyClass();
        int level = r.getInt(1, 99);
        return String.format(FANTASY_CLASS_FORMAT, fantasyClass, level);
    }

    public String getFemaleHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.FEMININE).getText();
    }

    @Override
    public String getMaleHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.MASCULINE).getText();
    }

    @Override
    public String getGenderlessHonorific() {
        String honorific = ResourceGetter.with(r).getString(Constant.HONORIFICS);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.UNDEFINED).getText();
    }

    @Override
    public String getFemaleRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.FEMININE).getText();
    }

    @Override
    public String getMaleRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.MASCULINE).getText();
    }

    @Override
    public String getGenderlessRoyalTitle() {
        String honorific = ResourceGetter.with(r).getString(Constant.ROYAL_TITLES);
        return com.memoeslink.generator.english.TextProcessor.genderifyStr(honorific, Gender.UNDEFINED).getText();
    }
}
