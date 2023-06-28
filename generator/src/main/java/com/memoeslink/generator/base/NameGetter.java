package com.memoeslink.generator.base;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.common.IntegerHelper;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.common.TextProcessor;
import com.memoeslink.generator.international.Shaper;

public class NameGetter extends com.memoeslink.generator.common.NameGetter implements NameDefiner {

    public NameGetter() {
        super();
    }

    public NameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getFemaleDefinedForename() {
        return TextProcessor.feminize(getDefinedForename(r));
    }

    @Override
    public String getFemaleDefinedForename(int type) {
        return TextProcessor.feminize(getDefinedForename(type, r));
    }

    @Override
    public String getMaleDefinedForename() {
        return getDefinedForename(r);
    }

    @Override
    public String getMaleDefinedForename(int type) {
        return getDefinedForename(type, r);
    }

    @Override
    public String getDefinedFamilyName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getDefinedFamilyName(type);
    }

    @Override
    public String getDefinedFamilyName(int type) {
        type = IntegerHelper.defaultIndex(type, Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return StringHelper.capitalizeFirst(ResourceGetter.with(r).getString(Constant.GENERATED_NAME_START[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_NAME_MIDDLE[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_FAMILY_NAME_SUFFIX[type]));
    }

    @Override
    public String getFemaleDefinedFullName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getFemaleDefinedForename(type) + Separator.SPACE.getCharacter() + getDefinedFamilyName(type);
    }

    @Override
    public String getMaleDefinedFullName() {
        int type = r.getInt(Constant.GENERATED_FAMILY_NAME_SUFFIX.length);
        return getMaleDefinedForename(type) + Separator.SPACE.getCharacter() + getDefinedFamilyName(type);
    }

    @Override
    public String getDefinedFullName() {
        return r.getBoolean() ? getMaleDefinedFullName() : getFemaleDefinedFullName();
    }

    @Override
    public String getFemaleIterativeForename() {
        return TextProcessor.feminize(getIterativeName(r.getInt(1, 6), r));
    }

    @Override
    public String getMaleIterativeForename() {
        return getIterativeName(r.getInt(1, 6), r);
    }

    @Override
    public String getIterativeFamilyName() {
        return getIterativeName(r.getInt(1, 4), r);
    }

    @Override
    public String getFemaleIterativeFullName() {
        return getFemaleIterativeForename() + Separator.SPACE.getCharacter() + getIterativeFamilyName();
    }

    @Override
    public String getMaleIterativeFullName() {
        return getMaleIterativeForename() + Separator.SPACE.getCharacter() + getIterativeFamilyName();
    }

    @Override
    public String getIterativeFullName() {
        return r.getBoolean() ? getMaleIterativeFullName() : getFemaleIterativeFullName();
    }

    @Override
    public String getFemalePatternForename() {
        String ending = ResourceGetter.with(r).getString(Constant.FEMALE_NAME_SUFFIX);
        return TextProcessor.feminize(getPatternName(r), ending);
    }

    @Override
    public String getMalePatternForename() {
        return getPatternName(r);
    }

    @Override
    public String getPatternFamilyName() {
        return StringHelper.weld(getPatternName(r), ResourceGetter.with(r).getString(Constant.FAMILY_NAME_SUFFIX));
    }

    @Override
    public String getFemalePatternFullName() {
        return getFemalePatternForename() + Separator.SPACE.getCharacter() + getPatternFamilyName();
    }

    @Override
    public String getMalePatternFullName() {
        return getMalePatternForename() + Separator.SPACE.getCharacter() + getPatternFamilyName();
    }

    @Override
    public String getPatternFullName() {
        return r.getBoolean() ? getMalePatternFullName() : getFemalePatternFullName();
    }

    @Override
    public String getFemaleFrequencyForename() {
        return TextProcessor.feminizeWithDefaultWhen(getMaleFrequencyForename(), "e", "o");
    }

    @Override
    public String getFemaleFrequencyForename(int type) {
        return TextProcessor.feminizeWithDefaultWhen(getMaleFrequencyForename(type), "e", "o");
    }

    @Override
    public String getMaleFrequencyForename() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getMaleFrequencyForename(type);
    }

    @Override
    public String getMaleFrequencyForename(int type) {
        return getFrequencyName(Constant.WEIGHTED_LETTERS[type], r.getInt(3, 11), r);
    }

    @Override
    public String getFrequencyFamilyName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getFrequencyFamilyName(type);
    }

    @Override
    public String getFrequencyFamilyName(int type) {
        return getFrequencyName(Constant.WEIGHTED_LETTERS[type], r.getInt(2, 13), r);
    }

    @Override
    public String getFemaleFrequencyFullName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getFemaleFrequencyForename(type) + Separator.SPACE.getCharacter() + getFrequencyFamilyName(type);
    }

    @Override
    public String getMaleFrequencyFullName() {
        int type = r.getInt(Constant.WEIGHTED_LETTERS.length);
        return getMaleFrequencyForename(type) + Separator.SPACE.getCharacter() + getFrequencyFamilyName(type);
    }

    @Override
    public String getFrequencyFullName() {
        return r.getBoolean() ? getMaleFrequencyFullName() : getFemaleFrequencyFullName();
    }

    @Override
    public String getFemalePreformedForename() {
        return TextProcessor.feminize(getMalePreformedForename());
    }

    @Override
    public String getFemalePreformedForename(Shaper shaper) {
        return TextProcessor.feminize(getMalePreformedForename(shaper));
    }

    @Override
    public String getMalePreformedForename() {
        Shaper shaper = r.getElement(Shaper.values());
        return getMalePreformedForename(shaper);
    }

    @Override
    public String getMalePreformedForename(Shaper shaper) {
        shaper = shaper != null ? shaper : Shaper.DEFAULT;
        return getPreformedName(shaper.getShape(), r.getInt(3, 11), r);
    }

    @Override
    public String getPreformedFamilyName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getPreformedFamilyName(shaper);
    }

    @Override
    public String getPreformedFamilyName(Shaper shaper) {
        shaper = shaper != null ? shaper : Shaper.DEFAULT;
        return getPreformedName(shaper.getShape(), r.getInt(3, 7), r);
    }

    @Override
    public String getFemalePreformedFullName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getFemalePreformedForename(shaper) + Separator.SPACE.getCharacter() + getPreformedFamilyName(shaper);
    }

    @Override
    public String getMalePreformedFullName() {
        Shaper shaper = r.getElement(Shaper.values());
        return getMalePreformedForename(shaper) + Separator.SPACE.getCharacter() + getPreformedFamilyName(shaper);
    }

    @Override
    public String getPreformedFullName() {
        return r.getBoolean() ? getMalePreformedFullName() : getFemalePreformedFullName();
    }

    @Override
    public String getFemaleMarkovianForename() {
        String ending = ResourceGetter.with(r).getString(Constant.FEMALE_NAME_SUFFIX);
        return TextProcessor.feminizeOnVowelEnd(getMarkovianName(3, 10, r), ending);
    }

    @Override
    public String getMaleMarkovianForename() {
        return getMarkovianName(3, 10, r);
    }

    @Override
    public String getMarkovianFamilyName() {
        return getMarkovianName(3, 8, r);
    }

    @Override
    public String getFemaleMarkovianFullName() {
        return getFemaleMarkovianForename() + Separator.SPACE.getCharacter() + getMarkovianFamilyName();
    }

    @Override
    public String getMaleMarkovianFullName() {
        return getMaleMarkovianForename() + Separator.SPACE.getCharacter() + getMarkovianFamilyName();
    }

    @Override
    public String getMarkovianFullName() {
        return r.getBoolean() ? getMaleMarkovianFullName() : this.getFemaleMarkovianFullName();
    }

    @Override
    public String getSecretName() {
        return getSecretName(r.getInt(2, 11), r);
    }
}
