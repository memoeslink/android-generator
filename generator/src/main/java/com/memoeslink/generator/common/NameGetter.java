package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.international.Shaper;

import org.memoeslink.StringHelper;

public class NameGetter extends Getter implements NameDefiner {

    public NameGetter() {
        super();
    }

    public NameGetter(Randomizer r) {
        super(r);
    }

    @Override
    public String getEmptyName() {
        return StringHelper.EMPTY;
    }

    @Override
    public String getFemaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleForename(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleForename(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePatronymic() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePatronymic(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePatronymic() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePatronymic(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleBarrelledForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleBarrelledForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleBarrelledForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleBarrelledForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDoubleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDoubleForename(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getGivenName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleGivenName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleGivenName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getSurname() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getSurname(int id) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDualSurname() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDualSurname(int startId, int endId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleSimpleName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleSimpleName(int forenameId, int surnameId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleSimpleName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleSimpleName(int forenameId, int surnameId) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getSimpleName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDefinedForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDefinedForename(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDefinedForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDefinedForename(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDefinedFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDefinedFamilyName(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleDefinedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleDefinedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getDefinedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleIterativeForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleIterativeForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getIterativeFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleIterativeFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleIterativeFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getIterativeFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePatternForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePatternForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPatternFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePatternFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePatternFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPatternFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleFrequencyForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleFrequencyForename(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleFrequencyForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleFrequencyForename(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFrequencyFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFrequencyFamilyName(int type) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleFrequencyFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleFrequencyFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFrequencyFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePreformedForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePreformedForename(Shaper shaper) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePreformedForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePreformedForename(Shaper shaper) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPreformedFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPreformedFamilyName(Shaper shaper) {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemalePreformedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMalePreformedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getPreformedFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleMarkovianForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleMarkovianForename() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMarkovianFamilyName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getFemaleMarkovianFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMaleMarkovianFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getMarkovianFullName() {
        return Database.DEFAULT_VALUE;
    }

    @Override
    public String getSecretName() {
        return Database.DEFAULT_VALUE;
    }
}
