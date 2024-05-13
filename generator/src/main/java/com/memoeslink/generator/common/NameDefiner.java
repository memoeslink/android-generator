package com.memoeslink.generator.common;

import com.memoeslink.generator.international.Shaper;

public interface NameDefiner {

    public String getEmptyName();

    public String getFemaleForename();

    public String getFemaleForename(int id);

    public String getMaleForename();

    public String getMaleForename(int id);

    public String getForename();

    public String getFemalePatronymic();

    public String getFemalePatronymic(int id);

    public String getMalePatronymic();

    public String getMalePatronymic(int id);

    public String getFemaleDoubleBarrelledForename();

    public String getFemaleDoubleBarrelledForename(int startId, int endId);

    public String getMaleDoubleBarrelledForename();

    public String getMaleDoubleBarrelledForename(int startId, int endId);

    public String getFemaleDoubleForename();

    public String getFemaleDoubleForename(int startId, int endId);

    public String getMaleDoubleForename();

    public String getMaleDoubleForename(int startId, int endId);

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

    public String getFemaleMarkovianForename();

    public String getMaleMarkovianForename();

    public String getMarkovianFamilyName();

    public String getFemaleMarkovianFullName();

    public String getMaleMarkovianFullName();

    public String getMarkovianFullName();

    public String getSecretName();
}
