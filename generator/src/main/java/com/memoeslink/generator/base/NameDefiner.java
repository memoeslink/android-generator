package com.memoeslink.generator.base;

import com.memoeslink.common.Randomizer;
import com.memoeslink.common.WeightedChar;
import com.memoeslink.generator.common.CharHelper;
import com.memoeslink.generator.common.IntegerHelper;
import com.memoeslink.generator.common.ResourceGetter;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;
import com.memoeslink.generator.common.UsernameSeparator;
import com.memoeslink.generator.international.NameGen;

import net.andreinc.aleph.AlephFormatter;

import java.util.Locale;

public interface NameDefiner extends com.memoeslink.generator.common.NameDefiner {

    default String getDefinedForename(Randomizer r) {
        r = r != null ? r : new Randomizer();
        int type = r.getInt(Constant.GENERATED_NAME_START.length);
        return getDefinedForename(type, r);
    }

    default String getDefinedForename(int type, Randomizer r) {
        type = IntegerHelper.defaultIndex(type, Constant.GENERATED_NAME_START.length);
        r = r != null ? r : new Randomizer();
        return StringHelper.capitalizeFirst(ResourceGetter.with(r).getString(Constant.GENERATED_NAME_START[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_NAME_MIDDLE[type]) +
                ResourceGetter.with(r).getString(Constant.GENERATED_NAME_ENDING[type]));
    }

    default String getIterativeName(int iterations, Randomizer r) {
        iterations = IntegerHelper.defaultByRange(iterations, 1, 100);
        r = r != null ? r : new Randomizer();
        StringBuilder sb = new StringBuilder();
        float probability = r.getBoolean() ? 1.1F : 0.F; // Decide whether the name will start with vowel or consonant

        // Add consonants with vowel
        for (int i = 1; i <= iterations; i++) {
            if (probability <= 0.7F)
                sb.append(r.getCharBasedOnWeight(Constant.WEIGHTED_CONSONANTS));
            else if (probability <= 0.85F)
                sb.append(ResourceGetter.with(r).getString(Constant.MIDDLE_CONSONANTS));
            else if (probability <= 1.0F)
                sb.append(ResourceGetter.with(r).getString(Constant.CONSONANT_PAIRS));
            sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_VOWELS));
            probability = r.getFloat();
        }

        // Decide whether the name will end with consonant
        if (r.getInt(4) == 0 || sb.length() == 1)
            sb.append(ResourceGetter.with(r).getChar(Constant.LOWERCASE_ENDING_CONSONANTS));
        return StringHelper.capitalizeFirst(sb.toString());
    }

    default String getPatternName(Randomizer r) {
        r = r != null ? r : new Randomizer();
        String namePattern = ResourceGetter.with(r).getString(Constant.NAME_PATTERNS);
        StringBuilder sb = new StringBuilder();

        if (r.getBoolean())
            namePattern = StringHelper.replace(namePattern, 'e', 'ɘ');

        if (r.getBoolean())
            namePattern = StringHelper.replace(namePattern, 'k', 'q');

        for (char c : namePattern.toCharArray()) {
            switch (c) {
                case 'c' ->
                        sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_CONSONANTS));
                case 'e' ->
                        sb.append(ResourceGetter.with(r).getChar(Constant.LOWERCASE_ENDING_CONSONANTS));
                case 'ɘ' -> sb.append(ResourceGetter.with(r).getString(Constant.ENDING_CONSONANTS));
                case 'k' -> sb.append(ResourceGetter.with(r).getString(Constant.CONSONANT_PAIRS));
                case 'ɔ' ->
                        sb.append(ResourceGetter.with(r).getString(Constant.EXTENDED_CONSONANT_PAIRS));
                case 'm' -> sb.append(ResourceGetter.with(r).getString(Constant.MIDDLE_CONSONANTS));
                case 'q' ->
                        sb.append(ResourceGetter.with(r).getString(Constant.STARTING_CONSONANTS));
                case 'v' ->
                        sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_VOWELS));
                case 'w' -> sb.append(ResourceGetter.with(r).getString(Constant.VOWEL_PAIRS));
                case '?' -> {
                    if (r.getBoolean())
                        sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_CONSONANTS));
                    else
                        sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_VOWELS));
                }
                case ' ' -> sb.append(' ');
            }
        }
        return StringHelper.capitalize(sb.toString());
    }

    default String getFrequencyName(WeightedChar[] letters, int length, Randomizer r) {
        length = IntegerHelper.defaultByRange(length, 1, 9999);
        r = r != null ? r : new Randomizer();
        String s;
        StringBuilder sb = new StringBuilder();
        char previousChar = r.getCharBasedOnWeight(letters);
        char currentChar;
        boolean sameType = false;
        boolean allowed;
        boolean equal;
        boolean vowel, anotherVowel;
        float[] approvalRate = {0.5F, 0.125F, 0.0F};
        int count = 0;

        if (letters == null || letters.length == 0)
            letters = Constant.ENGLISH_WEIGHTED_LETTERS;

        for (int n = 0; n < length; n++) {
            if (sameType) {
                do {
                    currentChar = r.getCharBasedOnWeight(letters);
                    allowed = true;

                    if (previousChar == currentChar) {
                        equal = true;
                        allowed = r.getBoolean();
                    } else
                        equal = false;
                }
                while ((vowel = CharHelper.isUnaccentedVowel(previousChar)) ^ CharHelper.isUnaccentedVowel(currentChar) ||
                        ((!vowel || n == length - 1) && CharHelper.isAccentedConsonant(currentChar)) ||
                        (equal && CharHelper.isNonClusterConsonant(currentChar)) || !allowed);
            } else {
                do {
                    currentChar = r.getCharBasedOnWeight(letters);
                }
                while ((vowel = CharHelper.isUnaccentedVowel(previousChar)) == (anotherVowel = CharHelper.isUnaccentedVowel(currentChar)) ||
                        ((!vowel || n == length - 1) && CharHelper.isAccentedConsonant(currentChar)) ||
                        (CharHelper.isAccentedConsonant(currentChar) && !anotherVowel));
            }
            sb.append(currentChar);
            previousChar = sb.charAt(sb.length() - 1);

            if (r.getFloat() <= approvalRate[count] && !CharHelper.isAccentedConsonant(currentChar)) {
                count++;
                sameType = true;
            } else {
                count = 0;
                sameType = false;
            }
        }
        s = sb.toString();

        if (!StringHelper.hasVowel(s))
            s = getFrequencyName(letters, length, r);
        return StringHelper.capitalizeFirst(s);
    }

    default String getPreformedName(String letters, int length, Randomizer r) {
        length = IntegerHelper.defaultByRange(length, 0, letters.length());
        r = r != null ? r : new Randomizer();
        String name = StringHelper.EMPTY;

        if (StringHelper.isNotNullOrEmpty(letters)) {
            int firstMark = r.getInt(letters.length()), secondMark;

            if (firstMark + length - 1 > letters.length()) {
                secondMark = firstMark;
                firstMark = secondMark - length + 1;
            } else
                secondMark = firstMark + length - 1;
            name = StringHelper.substring(letters, firstMark, secondMark + 1);

            if (!StringHelper.hasVowel(name))
                name = getPreformedName(letters, length, r);
            else {
                name = r.getBoolean() ? StringHelper.reverse(name) : name;
                name = StringHelper.capitalizeFirst(name);
            }
        }
        return name;
    }

    default String getMarkovName(int minLength, int maxLength, Randomizer r) {
        minLength = IntegerHelper.defaultByMin(minLength, 1);
        maxLength = IntegerHelper.defaultByRange(maxLength, minLength, 9999);
        r = r != null ? r : new Randomizer();
        NameGen nameGen;

        if (r.getSeed() == null)
            nameGen = new NameGen(minLength, maxLength);
        else
            nameGen = new NameGen(minLength, maxLength, r.getSeed());
        return StringHelper.capitalizeFirst(nameGen.getName());
    }

    default String getSecretName(int length, Randomizer r) {
        length = IntegerHelper.defaultByRange(length, 1, 9999);
        r = r != null ? r : new Randomizer();
        StringBuilder sb = new StringBuilder();
        boolean on = r.getBoolean();

        for (int i = 0; i < length; i++) {
            if (i % 2 == 0 ^ on)
                sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_VOWELS));
            else
                sb.append(ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.LOWERCASE_CONSONANTS));
        }
        return StringHelper.capitalizeFirst(sb.toString());
    }

    default String getCompositeUsername(String a, String b, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.joinWithSpace(a, b).trim();
        username = StringHelper.normalize(username);

        if (StringHelper.isNullOrBlank(username))
            return username;

        // Append number, if required
        switch (r.getInt(6)) {
            case 0 ->
                    username = username + Separator.SPACE.getCharacter() + (com.memoeslink.generator.common.Constant.STARTING_YEAR + r.getInt(-100, 101));
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

    default String getDerivedUsername(String s, Randomizer r) {
        r = r != null ? r : new Randomizer();
        String username = StringHelper.normalizeAlpha(s);

        if (username.length() > 4)
            username = username.substring(0, 5);
        username = ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.UPPERCASE_ALPHABET) + username;
        username += r.getInt(101);
        return username;
    }

    default String getPatternUsername(String a, String b, Locale locale, Randomizer r) {
        r = r != null ? r : new Randomizer();

        if (StringHelper.isNullOrBlank(a) || StringHelper.isNullOrBlank(b))
            return com.memoeslink.generator.common.Constant.DEFAULT_USERNAME;
        a = StringHelper.normalizeAlpha(a).toLowerCase();
        b = StringHelper.normalizeAlpha(b).toLowerCase();
        String pattern = ResourceGetter.with(r).getString(com.memoeslink.generator.common.Constant.USERNAME_PATTERNS);
        return AlephFormatter.str(pattern)
                .arg("first", a)
                .arg("second", b)
                .arg("job", StringHelper.normalize(ResourceGetter.with(r).getStrFromResBundle(locale, "job.position")).toLowerCase())
                .arg("denominator", StringHelper.normalize(ResourceGetter.with(r).getStrFromResBundle(locale, "organization.denominator")).toLowerCase())
                .arg("letter", ResourceGetter.with(r).getChar(com.memoeslink.generator.english.Constant.UPPERCASE_ALPHABET))
                .arg("number", r.getInt(1, 10))
                .arg("year", com.memoeslink.generator.common.Constant.STARTING_YEAR + r.getInt(-100, 101))
                .fmt();
    }
}
