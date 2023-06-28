package com.memoeslink.generator.common;

import com.memoeslink.generator.international.Shaper;

public interface NameDefiner {

    public String getEmptyName();

    public String getDefaultName();

    public String getTestName();

    public String getFemaleForename();

    public String getFemaleForename(int id);

    public String getMaleForename();

    public String getMaleForename(int id);

    public String getFemalePatronymic();

    public String getFemalePatronymic(int id);

    public String getMalePatronymic();

    public String getMalePatronymic(int id);

    public String getDoubleBarrelledFemaleForename();

    public String getDoubleBarrelledFemaleForename(int startId, int endId);

    public String getDoubleBarrelledMaleForename();

    public String getDoubleBarrelledMaleForename(int startId, int endId);

    public String getDoubleFemaleForename();

    public String getDoubleFemaleForename(int startId, int endId);

    public String getDoubleMaleForename();

    public String getDoubleMaleForename(int startId, int endId);

    public String getGivenName();

    public String getFemaleGivenName();

    public String getMaleGivenName();

    public String getSurname();

    public String getSurname(int id);

    public String getDualSurname();

    public String getDualSurname(int startId, int endId);

    public String getFemaleSimpleName();

    public String getFemaleSimpleName(int forenameId, int surnameId);

    public String getMaleSimpleName();

    public String getMaleSimpleName(int forenameId, int surnameId);

    public String getSimpleName();

    public String getFemaleFullName();

    public String getMaleFullName();

    public String getFullName();

    public String getFemaleDefinedForename();

    public String getFemaleDefinedForename(int type);

    public String getMaleDefinedForename();

    public String getMaleDefinedForename(int type);

    public String getDefinedFamilyName();

    public String getDefinedFamilyName(int type);

    public String getFemaleDefinedFullName();

    public String getMaleDefinedFullName();

    public String getDefinedFullName();

    public String getFemaleIterativeForename();

    public String getMaleIterativeForename();

    public String getIterativeFamilyName();

    public String getFemaleIterativeFullName();

    public String getMaleIterativeFullName();

    public String getIterativeFullName();

    public String getFemalePatternForename();

    public String getMalePatternForename();

    public String getPatternFamilyName();

    public String getFemalePatternFullName();

    public String getMalePatternFullName();

    public String getPatternFullName();

    public String getFemaleFrequencyForename();

    public String getFemaleFrequencyForename(int type);

    public String getMaleFrequencyForename();

    public String getMaleFrequencyForename(int type);

    public String getFrequencyFamilyName();

    public String getFrequencyFamilyName(int type);

    public String getFemaleFrequencyFullName();

    public String getMaleFrequencyFullName();

    public String getFrequencyFullName();

    public String getFemalePreformedForename();

    public String getFemalePreformedForename(Shaper shaper);

    public String getMalePreformedForename();

    public String getMalePreformedForename(Shaper shaper);

    public String getPreformedFamilyName();

    public String getPreformedFamilyName(Shaper shaper);

    public String getFemalePreformedFullName();

    public String getMalePreformedFullName();

    public String getPreformedFullName();

    public String getFemaleMarkovForename();

    public String getMaleMarkovForename();

    public String getMarkovFamilyName();

    public String getFemaleMarkovFullName();

    public String getMaleMarkovFullName();

    public String getMarkovFullName();

    public String getSecretName();

    public String getUsername();

    public String getCompositeUsername();

    public String getDerivedUsername();

    public String getPatternUsername();

    public String getAnonymousName();

    public String getAnonymousAnimal();
}
