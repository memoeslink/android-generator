package com.memoeslink.generator.common;

import java.util.Arrays;

public class CharHelper {
    public static final char NULL_CHAR = '\0';

    public static boolean isNull(char c) {
        return c == NULL_CHAR;
    }

    public static char defaultIfNull(char c) {
        if (c == NULL_CHAR)
            return Character.SPACE_SEPARATOR;
        else return c;
    }

    public static char defaultIfNull(char c, char defaultValue) {
        if (c == NULL_CHAR)
            return defaultIfNull(defaultValue);
        return c;
    }

    public static boolean equalsAny(char c, char... chars) {
        for (char character : chars) {
            if (c == character) return true;
        }
        return false;
    }

    public static boolean isLetter(char c) {
        if (c == NULL_CHAR)
            return false;
        return Character.isLetter(c);
    }

    public static boolean isVowel(char c) {
        if (c == NULL_CHAR)
            return false;
        String s = StringHelper.stripAccents(String.valueOf(c));
        return com.memoeslink.generator.english.Constant.VOWELS.contains(s);
    }

    public static boolean isUnaccentedVowel(char c) {
        if (c == NULL_CHAR)
            return false;
        return com.memoeslink.generator.english.Constant.VOWELS.indexOf(c) != -1;
    }

    public static boolean isRegisteredVowel(char c) {
        if (c == NULL_CHAR)
            return false;
        return Constant.ACCENTED_VOWELS.indexOf(c) != -1;
    }

    public static boolean isConsonant(char c) {
        if (c == NULL_CHAR)
            return false;
        String s = StringHelper.stripAccents(String.valueOf(c));
        return com.memoeslink.generator.english.Constant.CONSONANTS.contains(s);
    }

    public static boolean isAccentedConsonant(char c) {
        if (c == NULL_CHAR)
            return false;
        String originalChar = String.valueOf(c);
        String modifiedChar = StringHelper.stripAccents(originalChar);
        return com.memoeslink.generator.english.Constant.CONSONANTS.contains(modifiedChar) && !originalChar.equals(modifiedChar);
    }

    public static boolean isNonClusterConsonant(char c) {
        if (c == NULL_CHAR)
            return false;
        c = Character.toLowerCase(c);

        for (char consonant : com.memoeslink.generator.international.Constant.LOWERCASE_NON_CLUSTER_CONSONANTS) {
            if (c == consonant) return true;
        }
        return false;
    }

    public static boolean isSpace(char c) {
        if (c == NULL_CHAR)
            return false;
        return Character.isSpaceChar(c);
    }

    public static boolean isPrintable(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return (!Character.isISOControl(c)) &&
                block != null &&
                block != Character.UnicodeBlock.SPECIALS;
    }

    public static boolean isCharacterAvailableInFont(char c) {
        if (Character.isWhitespace(c))
            return false;
        char missingCharacter = '\u0978';
        byte[] b1 = BitmapHelper.getCharPixels(c);
        byte[] b2 = BitmapHelper.getCharPixels(missingCharacter);
        return !Arrays.equals(b1, b2);
    }

    public static boolean isGlyphDisplayable(char c) {
        return isPrintable(c) && isCharacterAvailableInFont(c);
    }

    public static char getFirstDisplayableGlyph(char... chars) {
        for (char c : chars) {
            if (isGlyphDisplayable(c))
                return c;
        }
        return '\0';
    }

    public static String getUnicode(char c) {
        if (c == NULL_CHAR)
            return StringHelper.EMPTY;
        return String.format("\\u%04x", (int) c);
    }

    public static char getHexDigit(int value) {
        value = IntegerHelper.defaultByMin(value, 0);
        return Integer.toHexString(value % 16).charAt(0);
    }
}
