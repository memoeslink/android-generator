package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;
import com.memoeslink.generator.international.Shaper;

import net.andreinc.aleph.AlephFormatter;

import java.util.Locale;

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

    default String getCompositeUsername(String a, String b, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.joinWithSpace(a, b).trim();
        username = StringHelper.normalize(username);

        if (StringHelper.isNullOrBlank(username))
            return username;

        // Append number, if required
        switch (r.getInt(6)) {
            case 0 ->
                    username = username + Separator.SPACE.getCharacter() + (Constant.STARTING_YEAR + r.getInt(-100, 101));
            case 1 -> {
                int extent = 10;
                int exp = r.getInt(1, 7);

                for (int n = 1; n < exp; n++) {
                    extent *= 10;
                }
                int number = r.getInt(extent);
                int count = IntegerHelper.countDigits(extent - 1);
                username = username + Separator.SPACE.getCharacter() +
                        StringHelper.padLeft(String.valueOf(number), count, '0');
            }
            case 2 -> {
                String[] numbers = {"0", "002", "007", "2", "69", "69", "69", "666", "777", "420", "420", "420", "911", "999"};
                username = username + Separator.SPACE.getCharacter() + r.getElement(numbers);
            }
        }

        // Replace or remove whitespaces
        switch (r.getInt(3)) {
            case 0 ->
                    username = StringHelper.remove(username, String.valueOf(Separator.SPACE.getCharacter()));
            case 1 -> {
                username = StringHelper.capitalize(username);
                username = StringHelper.remove(username, String.valueOf(Separator.SPACE.getCharacter()));
            }
            case 2 -> {
                char separator = r.getElement(UsernameSeparator.values()).getCharacter();
                username = StringHelper.replace(username, Separator.SPACE.getCharacter(), separator);
            }
        }
        return username;
    }

    public String getDerivedUsername();

    default String getDerivedUsername(String s, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.normalizeAlpha(s);

        if (username.length() > 4)
            username = username.substring(0, 5);
        username = ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.UPPERCASE_ALPHABET) + username;
        username += r.getInt(101);
        return username;
    }

    public String getPatternUsername();

    default String getPatternUsername(String a, String b, Locale locale, Randomizer r) {
        r = r != null ? r : new Randomizer();

        if (StringHelper.isNullOrBlank(a) || StringHelper.isNullOrBlank(b))
            return com.memoeslink.generator.common.Constant.DEFAULT_USERNAME;
        a = StringHelper.normalizeAlpha(a).toLowerCase();
        b = StringHelper.normalizeAlpha(b).toLowerCase();
        String pattern = ResourceGetter.with(r).getString(Constant.USERNAME_PATTERNS);
        return AlephFormatter.str(pattern)
                .arg("first", a)
                .arg("second", b)
                .arg("job", StringHelper.normalize(ResourceGetter.with(r).getStrFromResBundle(locale, "job.position")).toLowerCase())
                .arg("denominator", StringHelper.normalize(ResourceGetter.with(r).getStrFromResBundle(locale, "organization.denominator")).toLowerCase())
                .arg("letter", ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.UPPERCASE_ALPHABET))
                .arg("number", r.getInt(1, 10))
                .arg("year", Constant.STARTING_YEAR + r.getInt(-100, 101))
                .fmt();
    }

    public String getAnonymousName();

    public String getAnonymousAnimal();
}
