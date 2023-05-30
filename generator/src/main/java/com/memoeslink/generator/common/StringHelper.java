package com.memoeslink.generator.common;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    public static final String EMPTY = "";
    public static final String DEFAULT_VALUE = "?";

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrEmpty(String[] strings) {
        return strings == null || strings.length == 0;
    }

    public static boolean isNotNullOrEmpty(String s) {
        return !isNullOrEmpty(s);
    }

    public static boolean isNotNullOrEmpty(String[] strings) {
        return !isNullOrEmpty(strings);
    }

    public static boolean isNullOrBlank(String s) {
        if (isNullOrEmpty(s))
            return true;

        for (char c : s.toCharArray()) {
            if (!Character.isWhitespace(c)) return false;
        }
        return true;
    }

    public static boolean isNotNullOrBlank(String s) {
        return !isNullOrBlank(s);
    }

    public static int indexOf(String s, char c) {
        return indexOf(s, String.valueOf(c));
    }

    public static int indexOf(String s, String occurrence) {
        if (isNullOrEmpty(s))
            return IntegerHelper.INDEX_NOT_FOUND;
        return s.indexOf(occurrence);
    }

    public static String defaultWhenNull(String s) {
        return defaultIfNull(s, DEFAULT_VALUE);
    }

    public static String defaultIfNull(String s) {
        return s == null ? EMPTY : s;
    }

    public static String defaultIfNull(String s, String defaultValue) {
        if (s == null)
            return defaultValue == null ? EMPTY : defaultValue;
        return s;
    }

    public static String defaultWhenEmpty(String s) {
        return defaultIfEmpty(s, DEFAULT_VALUE);
    }

    public static String defaultIfEmpty(String s) {
        return defaultIfEmpty(s, EMPTY);
    }

    public static String defaultIfEmpty(String s, String defaultValue) {
        if (s == null || s.isEmpty())
            return defaultValue == null ? EMPTY : defaultValue;
        return s;
    }

    public static String defaultWhenBlank(String s) {
        return defaultIfBlank(s, DEFAULT_VALUE);
    }

    public static String defaultIfBlank(String s) {
        return defaultIfBlank(s, EMPTY);
    }

    public static String defaultIfBlank(String s, String defaultValue) {
        if (isNullOrBlank(s))
            return defaultValue == null ? EMPTY : defaultValue;
        return s;
    }

    public static String getFirstNonNull(String... strings) {
        for (String s : strings) {
            if (s != null)
                return s;
        }
        return null;
    }

    public static String getFirstNonEmpty(String... strings) {
        for (String s : strings) {
            if (isNotNullOrEmpty(s))
                return s;
        }
        return null;
    }

    public static String getFirstNonBlank(String... strings) {
        for (String s : strings) {
            if (isNotNullOrBlank(s))
                return s;
        }
        return null;
    }

    public static String getFirstNonNullElseDefault(String... strings) {
        return getFirstNonNullOrDefault(DEFAULT_VALUE, strings);
    }

    public static String getFirstNonEmptyElseDefault(String... strings) {
        return getFirstNonEmptyOrDefault(DEFAULT_VALUE, strings);
    }

    public static String getFirstNonBlankElseDefault(String... strings) {
        return getFirstNonBlankOrDefault(DEFAULT_VALUE, strings);
    }

    public static String getFirstNonNullOrDefault(String defaultValue, String... strings) {
        for (String s : strings) {
            if (s != null)
                return s;
        }
        return defaultValue;
    }

    public static String getFirstNonEmptyOrDefault(String defaultValue, String... strings) {
        for (String s : strings) {
            if (isNotNullOrEmpty(s))
                return s;
        }
        return defaultValue;
    }

    public static String getFirstNonBlankOrDefault(String defaultValue, String... strings) {
        for (String s : strings) {
            if (isNotNullOrBlank(s))
                return s;
        }
        return defaultValue;
    }

    public static String prependIfNotNull(String s, String prefix) {
        if (s == null)
            return null;
        return defaultIfNull(prefix) + s;
    }

    public static String prependIfNotEmpty(String s, String prefix) {
        if (isNullOrEmpty(s))
            return s;
        return defaultIfNull(prefix) + s;
    }

    public static String prependIfNotBlank(String s, String prefix) {
        if (isNullOrBlank(s))
            return s;
        return defaultIfNull(prefix) + s;
    }

    public static String prependEvenDefaultIfNotNull(String s, String prefix) {
        return prependEvenDefaultIfNotNull(s, prefix, DEFAULT_VALUE);
    }

    public static String prependEvenDefaultIfNotEmpty(String s, String prefix) {
        return prependEvenDefaultIfNotEmpty(s, prefix, DEFAULT_VALUE);
    }

    public static String prependEvenDefaultIfNotBlank(String s, String prefix) {
        return prependEvenDefaultIfNotBlank(s, prefix, DEFAULT_VALUE);
    }

    public static String prependEvenDefaultIfNotNull(String s, String prefix, String defaultPrefix) {
        if (s == null)
            return null;
        return defaultIfNull(prefix, defaultPrefix) + s;
    }

    public static String prependEvenDefaultIfNotEmpty(String s, String prefix, String defaultPrefix) {
        if (isNullOrEmpty(s))
            return s;
        return defaultIfNull(prefix, defaultPrefix) + s;
    }

    public static String prependEvenDefaultIfNotBlank(String s, String prefix, String defaultPrefix) {
        if (isNullOrBlank(s))
            return s;
        return defaultIfNull(prefix, defaultPrefix) + s;
    }

    public static String prependSpaceIfNotNull(String s) {
        return prependIfNotNull(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String prependSpaceIfNotEmpty(String s) {
        return prependIfNotEmpty(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String prependSpaceIfNotBlank(String s) {
        return prependIfNotBlank(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String prependHyphenIfNotNull(String s) {
        return prependIfNotNull(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String prependHyphenIfNotEmpty(String s) {
        return prependIfNotEmpty(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String prependHyphenIfNotBlank(String s) {
        return prependIfNotBlank(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String prependLineBreakIfNotNull(String s) {
        return prependIfNotNull(s, System.getProperty("line.separator"));
    }

    public static String prependLineBreakIfNotEmpty(String s) {
        return prependIfNotEmpty(s, System.getProperty("line.separator"));
    }

    public static String prependLineBreakIfNotBlank(String s) {
        return prependIfNotBlank(s, System.getProperty("line.separator"));
    }

    public static String appendIfNotNull(String s, String suffix) {
        if (s == null)
            return null;
        return s + defaultIfNull(suffix);
    }

    public static String appendIfNotEmpty(String s, String suffix) {
        if (isNullOrEmpty(s))
            return s;
        return s + defaultIfNull(suffix);
    }

    public static String appendIfNotBlank(String s, String suffix) {
        if (isNullOrBlank(s))
            return s;
        return s + defaultIfNull(suffix);
    }

    public static String appendEvenDefaultIfNotNull(String s, String suffix) {
        return appendEvenDefaultIfNotNull(s, suffix, DEFAULT_VALUE);
    }

    public static String appendEvenDefaultIfNotEmpty(String s, String suffix) {
        return appendEvenDefaultIfNotEmpty(s, suffix, DEFAULT_VALUE);
    }

    public static String appendEvenDefaultIfNotBlank(String s, String suffix) {
        return appendEvenDefaultIfNotBlank(s, suffix, DEFAULT_VALUE);
    }

    public static String appendEvenDefaultIfNotNull(String s, String suffix, String defaultSuffix) {
        if (s == null)
            return null;
        return s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String appendEvenDefaultIfNotEmpty(String s, String suffix, String defaultSuffix) {
        if (isNullOrEmpty(s))
            return s;
        return s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String appendEvenDefaultIfNotBlank(String s, String suffix, String defaultSuffix) {
        if (isNullOrBlank(s))
            return s;
        return s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String appendSpaceIfNotNull(String s) {
        return appendIfNotNull(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String appendSpaceIfNotEmpty(String s) {
        return appendIfNotEmpty(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String appendSpaceIfNotBlank(String s) {
        return appendIfNotBlank(s, String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String appendHyphenIfNotNull(String s) {
        return appendIfNotNull(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String appendHyphenIfNotEmpty(String s) {
        return appendIfNotEmpty(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String appendHyphenIfNotBlank(String s) {
        return appendIfNotBlank(s, String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String appendLineBreakIfNotNull(String s) {
        return appendIfNotNull(s, System.getProperty("line.separator"));
    }

    public static String appendLineBreakIfNotEmpty(String s) {
        return appendIfNotEmpty(s, System.getProperty("line.separator"));
    }

    public static String appendLineBreakIfNotBlank(String s) {
        return appendIfNotBlank(s, System.getProperty("line.separator"));
    }

    public static String affixIfNotNull(String s, String prefix, String suffix) {
        if (s == null)
            return null;
        return defaultIfNull(prefix) + s + defaultIfNull(suffix);
    }

    public static String affixIfNotEmpty(String s, String prefix, String suffix) {
        if (isNullOrEmpty(s))
            return s;
        return defaultIfNull(prefix) + s + defaultIfNull(suffix);
    }

    public static String affixIfNotBlank(String s, String prefix, String suffix) {
        if (isNullOrBlank(s))
            return s;
        return defaultIfNull(prefix) + s + defaultIfNull(suffix);
    }

    public static String affixEvenDefaultIfNotNull(String s, String prefix, String suffix) {
        return affixEvenDefaultIfNotNull(s, prefix, suffix, DEFAULT_VALUE, DEFAULT_VALUE);
    }

    public static String affixEvenDefaultIfNotEmpty(String s, String prefix, String suffix) {
        return affixEvenDefaultIfNotEmpty(s, prefix, suffix, DEFAULT_VALUE, DEFAULT_VALUE);
    }

    public static String affixEvenDefaultIfNotBlank(String s, String prefix, String suffix) {
        return affixEvenDefaultIfNotBlank(s, prefix, suffix, DEFAULT_VALUE, DEFAULT_VALUE);
    }

    public static String affixEvenDefaultIfNotNull(String s, String prefix, String suffix, String defaultPrefix, String defaultSuffix) {
        if (s == null)
            return null;
        return defaultIfNull(prefix, defaultPrefix) + s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String affixEvenDefaultIfNotEmpty(String s, String prefix, String suffix, String defaultPrefix, String defaultSuffix) {
        if (isNullOrEmpty(s))
            return s;
        return defaultIfNull(prefix, defaultPrefix) + s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String affixEvenDefaultIfNotBlank(String s, String prefix, String suffix, String defaultPrefix, String defaultSuffix) {
        if (isNullOrBlank(s))
            return s;
        return defaultIfNull(prefix, defaultPrefix) + s + defaultIfNull(suffix, defaultSuffix);
    }

    public static String affixSpacesIfNotNull(String s) {
        return affixIfNotNull(s, String.valueOf(Separator.SPACE.getCharacter()),
                String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String affixSpacesIfNotEmpty(String s) {
        return affixIfNotEmpty(s, String.valueOf(Separator.SPACE.getCharacter()),
                String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String affixSpacesIfNotBlank(String s) {
        return affixIfNotBlank(s, String.valueOf(Separator.SPACE.getCharacter()),
                String.valueOf(Separator.SPACE.getCharacter()));
    }

    public static String affixHyphensIfNotNull(String s) {
        return affixIfNotNull(s, String.valueOf(Separator.HYPHEN.getCharacter()),
                String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String affixHyphensIfNotEmpty(String s) {
        return affixIfNotEmpty(s, String.valueOf(Separator.HYPHEN.getCharacter()),
                String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String affixHyphensIfNotBlank(String s) {
        return affixIfNotBlank(s, String.valueOf(Separator.HYPHEN.getCharacter()),
                String.valueOf(Separator.HYPHEN.getCharacter()));
    }

    public static String affixLineBreaksIfNotNull(String s) {
        return affixIfNotNull(s, System.getProperty("line.separator"),
                System.getProperty("line.separator"));
    }

    public static String affixLineBreaksIfNotEmpty(String s) {
        return affixIfNotEmpty(s, System.getProperty("line.separator"),
                System.getProperty("line.separator"));
    }

    public static String affixLineBreaksIfNotBlank(String s) {
        return affixIfNotBlank(s, System.getProperty("line.separator"),
                System.getProperty("line.separator"));
    }

    public static String quote(String s) {
        return affixIfNotBlank(s, "“", "”");
    }

    public static List<String> split(String s, char delimiter) {
        if (s == null)
            return new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        ArrayList<String> parts = new ArrayList<>();
        parts.ensureCapacity(s.length() / 5);
        char[] chars = s.toCharArray();

        for (char c : chars) {
            if (c == delimiter) {
                parts.add(sb.toString());
                sb.delete(0, sb.length());
            } else
                sb.append(c);
        }

        if (sb.length() > 0)
            parts.add(sb.toString());
        return parts;
    }

    public static List<String> split(String s, String delimiter) {
        ArrayList<String> parts = new ArrayList<>();

        if (s == null)
            return parts;

        if (delimiter == null || s.length() < delimiter.length()) {
            parts.add(s);
            return parts;
        }
        parts.ensureCapacity(s.length() / 5);
        int index = s.indexOf(delimiter);
        int startIndex = 0;
        int endIndex;

        while (index >= 0) {
            endIndex = index;

            if (startIndex < endIndex)
                parts.add(s.substring(startIndex, endIndex));
            startIndex = endIndex + delimiter.length();
            index = s.indexOf(delimiter, index + 1);
        }

        if (startIndex < s.length() - 1)
            parts.add(s.substring(startIndex));
        return parts;
    }

    public static List<String> splitBySpace(String s) {
        return split(s, Separator.SPACE.getCharacter());
    }

    public static List<String> splitByHyphen(String s) {
        return split(s, Separator.HYPHEN.getCharacter());
    }

    public static List<String> splitByLineBreak(String s) {
        return split(s, System.getProperty("line.separator"));
    }

    public static List<String> splitByParagraphMark(String s) {
        if (isNotNullOrEmpty(s))
            return new ArrayList<>(Arrays.asList(s.split("¶[ ]*")));
        return new ArrayList<>();
    }

    public static String connect(String a, String b) {
        a = defaultIfNull(a);
        b = defaultIfNull(b);
        return a + b;
    }

    public static String join(char c, String... strings) {
        return join(String.valueOf(c), strings);
    }

    public static String join(char c, List<String> strings) {
        if (strings == null)
            return null;
        return join(String.valueOf(c), strings.toArray(new String[0]));
    }

    public static String join(String separator, String... strings) {
        StringBuilder sb = new StringBuilder();
        separator = defaultIfNull(separator);

        for (String s : strings) {
            if (isNotNullOrEmpty(s)) {
                if (sb.length() > 0)
                    sb.append(separator);
                sb.append(s);
            }
        }
        return sb.toString();
    }

    public static String join(String separator, List<String> strings) {
        if (strings == null)
            return null;
        return join(separator, strings.toArray(new String[0]));
    }

    public static String joinWithoutSeparator(String... strings) {
        return join(EMPTY, strings);
    }

    public static String joinWithoutSeparator(List<String> strings) {
        if (strings == null)
            return null;
        return joinWithoutSeparator(strings.toArray(new String[0]));
    }

    public static String joinWithSpace(String... strings) {
        return join(String.valueOf(Separator.SPACE.getCharacter()), strings);
    }

    public static String joinWithSpace(List<String> strings) {
        if (strings == null)
            return null;
        return join(String.valueOf(Separator.SPACE.getCharacter()), strings.toArray(new String[0]));
    }

    public static String joinWithHyphen(String... strings) {
        return join(String.valueOf(Separator.HYPHEN.getCharacter()), strings);
    }

    public static String joinWithHyphen(List<String> strings) {
        if (strings == null)
            return null;
        return join(String.valueOf(Separator.HYPHEN.getCharacter()), strings.toArray(new String[0]));
    }

    public static String joinWithLineBreak(String... strings) {
        return join(System.getProperty("line.separator"), strings);
    }

    public static String joinWithLineBreak(List<String> strings) {
        if (strings == null)
            return null;
        return join(System.getProperty("line.separator"), strings.toArray(new String[0]));
    }

    public static String joinWithSlash(String... strings) {
        return join(String.valueOf('/'), strings);
    }

    public static String joinWithSlash(List<String> strings) {
        if (strings == null)
            return null;
        return join(String.valueOf('/'), strings.toArray(new String[0]));
    }

    public static String joinWithBackslash(String... strings) {
        return join(String.valueOf('\\'), strings);
    }

    public static String joinWithBackslash(List<String> strings) {
        if (strings == null)
            return null;
        return join(String.valueOf('\\'), strings.toArray(new String[0]));
    }

    public static String joinWithFileSeparator(String... strings) {
        return join(File.separator, strings);
    }

    public static String joinWithFileSeparator(List<String> strings) {
        if (strings == null)
            return null;
        return join(File.separator, strings.toArray(new String[0]));
    }

    public static String weld(String a, String b) {
        if (isNullOrEmpty(a) || isNullOrEmpty(b))
            return EMPTY;
        char ending = getLastChar(a);
        char start = getFirstChar(b);
        boolean vowel;

        if (ending == start)
            return removeLastChar(a) + b;

        if (!Character.isLetter(ending) || !Character.isLetter(start))
            return a + b;

        if ((vowel = CharHelper.isVowel(ending)) ^ CharHelper.isVowel(start))
            return a + b;
        return vowel ? (a + removeFirstChar(b)) : (removeLastChar(a) + b);
    }

    public static String repeat(String s, int count) {
        if (s == null || count < 0)
            return null;
        StringBuilder sb = new StringBuilder(s.length() * count);

        for (int i = 0; i < count; i++) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static String trim(String s) {
        return s == null ? null : s.trim();
    }

    public static String trimToNull(String s) {
        s = trim(s);
        return isNullOrEmpty(s) ? null : s;
    }

    public static String trimToEmpty(String s) {
        return s == null ? EMPTY : s.trim();
    }

    public static String trimToDefault(String s) {
        return defaultWhenEmpty(trimToEmpty(s));
    }

    public static String trimToDefault(String s, String defaultValue) {
        return defaultIfEmpty(trimToEmpty(s), defaultValue);
    }

    public static String normalize(String s) {
        if (isNotNullOrEmpty(s))
            return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", EMPTY);
        return s;
    }

    public static String stripAccents(String s) {
        if (isNotNullOrEmpty(s))
            return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]", EMPTY);
        return s;
    }

    public static String capitalizeFirst(String s) {
        if (isNullOrEmpty(s))
            return s;

        if (Character.isLowerCase(s.charAt(0))) {
            char c = Character.toUpperCase(s.charAt(0));

            if (Character.isLetter(c))
                return c + s.substring(1);
        }
        return s;
    }

    public static String capitalize(String s) {
        if (isNullOrEmpty(s))
            return s;
        String[] parts = s.split("\\s+");

        for (int n = 0; n < parts.length; n++) {
            parts[n] = capitalizeFirst(parts[n]);
        }
        s = String.join(String.valueOf(Separator.SPACE.getCharacter()), parts);
        return s;
    }

    public static String uncapitalizeFirst(String s) {
        if (isNullOrEmpty(s))
            return s;

        if (Character.isUpperCase(s.charAt(0))) {
            char c = Character.toLowerCase(s.charAt(0));

            if (Character.isLetter(c))
                return c + s.substring(1);
        }
        return s;
    }

    public static String uncapitalize(String s) {
        if (isNullOrEmpty(s))
            return s;
        String[] parts = s.split("\\s+");

        for (int n = 0; n < parts.length; n++) {
            parts[n] = uncapitalizeFirst(parts[n]);
        }
        s = String.join(String.valueOf(Separator.SPACE.getCharacter()), parts);
        return s;
    }

    public static String reverse(String s) {
        if (isNullOrEmpty(s))
            return s;
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();
        return sb.toString();
    }

    public static String mask(String s) {
        return mask(s, '*');
    }

    public static String mask(String s, char replacement) {
        if (isNullOrEmpty(s))
            return s;
        replacement = replacement != '\0' ? replacement : Separator.SPACE.getCharacter();
        return s.replaceAll("\\S", String.valueOf(replacement));
    }

    public static String maskStart(String s) {
        return maskStart(s, '*');
    }

    public static String maskStart(String s, char replacement) {
        if (isNullOrEmpty(s))
            return s;
        replacement = replacement != '\0' ? replacement : Separator.SPACE.getCharacter();

        if (s.length() > 4)
            return s.replaceAll("(?<=.{4})\\S", String.valueOf(replacement));
        return String.valueOf(replacement).repeat(s.length());
    }

    public static String maskMiddle(String s) {
        return maskMiddle(s, '*');
    }

    public static String maskMiddle(String s, char replacement) {
        if (isNullOrEmpty(s))
            return s;
        replacement = replacement != '\0' ? replacement : Separator.SPACE.getCharacter();

        if (s.length() <= 4)
            return String.valueOf(replacement).repeat(s.length());

        if (s.length() <= 8)
            return s.replaceAll("(?<=.)\\S(?=.)", String.valueOf(replacement));

        if (s.length() <= 12)
            return s.replaceAll("(?<=.{2})\\S(?=.{2})", String.valueOf(replacement));
        return s.replaceAll("(?<=.{3})\\S(?=.{3})", String.valueOf(replacement));
    }

    public static String maskEnd(String s) {
        return maskEnd(s, '*');
    }

    public static String maskEnd(String s, char replacement) {
        if (isNullOrEmpty(s))
            return s;
        replacement = replacement != '\0' ? replacement : Separator.SPACE.getCharacter();

        if (s.length() > 4)
            return s.replaceAll("\\S(?=.{4})", String.valueOf(replacement));
        return String.valueOf(replacement).repeat(s.length());
    }

    public static String substring(String s, int startIndex, int endIndex) {
        if (isNullOrEmpty(s))
            return s;

        if (startIndex < 0)
            startIndex = s.length() + startIndex;
        startIndex = IntegerHelper.defaultByRange(startIndex, 0, s.length());

        if (endIndex < 0)
            endIndex = s.length() + endIndex;
        endIndex = IntegerHelper.defaultByRange(endIndex, startIndex, s.length());
        return s.substring(startIndex, endIndex);
    }

    public static String substringBefore(String s, String separator) {
        if (isNullOrEmpty(s) || separator == null)
            return s;

        if (separator.isEmpty())
            return EMPTY;
        final int index = s.indexOf(separator);

        if (index == IntegerHelper.INDEX_NOT_FOUND)
            return s;
        return s.substring(0, index);
    }

    public static String substringBetween(String s, String open, String close) {
        if (isNullOrEmpty(s) || isNullOrEmpty(open) || isNullOrEmpty(close))
            return null;
        int start = s.indexOf(open);

        if (start != IntegerHelper.INDEX_NOT_FOUND) {
            final int end = s.indexOf(close, start + open.length());

            if (end != IntegerHelper.INDEX_NOT_FOUND)
                return s.substring(start + open.length(), end);
        }
        return null;
    }

    public static String substringAfter(String s, String separator) {
        if (isNullOrEmpty(s))
            return s;

        if (separator == null)
            return EMPTY;
        final int index = s.indexOf(separator);

        if (index == IntegerHelper.INDEX_NOT_FOUND)
            return EMPTY;
        return s.substring(index + separator.length());
    }

    public static String replace(String s, char occurrence, char replacement) {
        if (replacement == CharHelper.NULL_CHAR)
            return replace(s, String.valueOf(occurrence), EMPTY);
        return replace(s, String.valueOf(occurrence), String.valueOf(replacement));
    }

    public static String replace(String s, String occurrence, String replacement) {
        if (isNullOrEmpty(s) || isNullOrEmpty(occurrence) || replacement == null)
            return s;
        int i = 0;

        if ((i = s.indexOf(occurrence, i)) >= 0) {
            char[] strChars = s.toCharArray();
            char[] replacementChars = replacement.toCharArray();
            int occurrenceLength = occurrence.length();
            StringBuilder sb = new StringBuilder(strChars.length);
            sb.append(strChars, 0, i).append(replacementChars);
            i += occurrenceLength;
            int j = i;

            while ((i = s.indexOf(occurrence, i)) > 0) {
                sb.append(strChars, j, i - j).append(replacementChars);
                i += occurrenceLength;
                j = i;
            }
            sb.append(strChars, j, strChars.length - j);
            s = sb.toString();
            sb.setLength(0);
        }
        return s;
    }

    public static String replaceByIndex(String s, int startIndex, int endIndex, String replacement) {
        if (isNullOrEmpty(s))
            return s;

        if (startIndex < 0)
            startIndex = s.length() + startIndex;
        startIndex = IntegerHelper.defaultByRange(startIndex, 0, s.length());

        if (endIndex < 0)
            endIndex = s.length() + endIndex;
        endIndex = IntegerHelper.defaultByRange(endIndex, startIndex, s.length());
        return new StringBuilder(s).replace(startIndex, endIndex, replacement).toString();
    }

    public static String replaceFirstChar(String s, String replacement) {
        if (isNotNullOrEmpty(s) && replacement != null)
            s = replacement + s.substring(1);
        return s;
    }

    public static String replaceLastChar(String s, String replacement) {
        if (isNotNullOrEmpty(s) && replacement != null)
            s = s.substring(0, s.length() - 1) + replacement;
        return s;
    }

    public static String replaceOnce(String s, String occurrence, String replacement) {
        int index = indexOf(s, occurrence);

        if (index == -1)
            return s;
        return s.substring(0, index).concat(replacement).concat(s.substring(index + occurrence.length()));
    }

    public static String replaceFinal(String s, String occurrence, String replacement) {
        if (isNullOrEmpty(s))
            return s;
        int lastIndex = s.lastIndexOf(occurrence);
        if (lastIndex < 0) return s;
        String tail = s.substring(lastIndex).replaceFirst(occurrence, replacement);
        return s.substring(0, lastIndex) + tail;
    }

    public static String replaceFirst(String s, String regex, String replacement) {
        if (isNotNullOrEmpty(s) && replacement != null)
            return s.replaceFirst(regex, replacement);
        return s;
    }

    public static String replaceLast(String s, String regex, String replacement) {
        return s.replaceFirst("(?s)(.*)" + regex, "$1" + replacement);
    }

    public static String replaceEach(String s, String[] occurrences, String[] replacements) {
        if (occurrences == null || replacements == null)
            return s;

        for (int n = 0; n < occurrences.length && n < replacements.length; n++) {
            s = replace(s, occurrences[n], replacements[n]);
        }
        return s;
    }

    public static String replaceAll(String s, String regex, String replacement) {
        if (isNotNullOrEmpty(s) && replacement != null)
            return s.replaceAll(regex, replacement);
        return s;
    }

    public static String replaceGroup(String s, String regex, int groupIndex, String replacement) {
        return replaceGroup(s, regex, groupIndex, 1, replacement);
    }

    public static String replaceGroup(String s, String regex, int groupIndex, int groupOccurrence, String replacement) {
        if (s == null || replacement == null)
            return s;
        Matcher matcher = Pattern.compile(regex).matcher(s);

        for (int i = 0; i < groupOccurrence; i++) {
            if (!matcher.find()) return s;
        }

        if (groupIndex > matcher.groupCount() || matcher.group(groupIndex) == null)
            return s;
        return new StringBuilder(s).replace(matcher.start(groupIndex), matcher.end(groupIndex), replacement).toString();
    }

    public static String replaceStart(String s, String prefix, String replacement) {
        if (startsWith(s, prefix) && replacement != null)
            return replacement + s.substring(prefix.length());
        return s;
    }

    public static String replaceAnyStart(String s, String replacement, String... prefixes) {
        for (String prefix : prefixes) {
            if (startsWith(s, prefix))
                return replaceStart(s, prefix, replacement);
        }
        return s;
    }

    public static String replaceEachStart(String s, String[] prefixes, String[] replacements) {
        if (prefixes == null || replacements == null)
            return s;

        for (int n = 0; n < prefixes.length && n < replacements.length; n++) {
            String result = replaceStart(s, prefixes[n], replacements[n]);

            if (!equals(s, result))
                return result;
        }
        return s;
    }

    public static String replaceEnd(String s, String suffix, String replacement) {
        if (endsWith(s, suffix) && replacement != null)
            return s.substring(0, s.length() - suffix.length()) + replacement;
        return s;
    }

    public static String replaceAnyEnd(String s, String replacement, String... suffixes) {
        for (String suffix : suffixes) {
            if (endsWith(s, suffix))
                return replaceEnd(s, suffix, replacement);
        }
        return s;
    }

    public static String replaceEachEnd(String s, String[] suffixes, String[] replacements) {
        if (suffixes == null || replacements == null)
            return s;

        for (int n = 0; n < suffixes.length && n < replacements.length; n++) {
            String result = replaceEnd(s, suffixes[n], replacements[n]);

            if (!equals(s, result))
                return result;
        }
        return s;
    }

    public static String replaceBetweenChars(String s, char opening, char closing, String replacement) {
        if (opening == CharHelper.NULL_CHAR || closing == CharHelper.NULL_CHAR)
            return s;
        String regex = String.format("\\Q%1$s\\E[^\\Q%1$s\\E\\Q%2$s\\E]*\\Q%2$s\\E", opening, closing);
        return replaceAll(s, regex, replacement);
    }

    public static String replaceBetweenChars(String s, char delimiter, String replacement) {
        return replaceBetweenChars(s, delimiter, delimiter, replacement);
    }

    public static String replaceBetweenParentheses(String s, String replacement) {
        return replaceBetweenChars(s, '(', ')', replacement);
    }

    public static String replaceBetweenSpaces(String s, String replacement) {
        String regex = "\\s[^\\s]*\\s";
        return replaceAll(s, regex, replacement);
    }

    public static String replaceBetweenZeroWidthSpaces(String s, String replacement) {
        return replaceBetweenChars(s, ZeroWidthChar.ZERO_WIDTH_SPACE.getCharacter(), replacement);
    }

    public static String remove(String s, char occurrence) {
        return replace(s, occurrence, CharHelper.NULL_CHAR);
    }

    public static String remove(String s, String occurrence) {
        return replace(s, occurrence, EMPTY);
    }

    public static String removeByIndex(String s, int startIndex, int endIndex) {
        return replaceByIndex(s, startIndex, endIndex, EMPTY);
    }

    public static String removeEach(String s, String... occurrences) {
        if (isNullOrEmpty(s) || occurrences.length == 0)
            return s;

        for (String occurrence : occurrences) {
            if (isNotNullOrEmpty(occurrence))
                s = replace(s, occurrence, EMPTY);
        }
        return s;
    }

    public static String removeAll(String s, String regex) {
        if (isNotNullOrEmpty(s) && isNotNullOrEmpty(regex))
            return s.replaceAll(regex, EMPTY);
        return s;
    }

    public static String removeGroup(String s, String regex, int groupIndex) {
        return replaceGroup(s, regex, groupIndex, 1, EMPTY);
    }

    public static String removeGroup(String s, String regex, int groupIndex, int groupOccurrence) {
        return replaceGroup(s, regex, groupIndex, groupOccurrence, EMPTY);
    }

    public static String removeFirstChar(String s) {
        if (isNotNullOrEmpty(s))
            s = s.substring(1);
        return s;
    }

    public static String removeLastChar(String s) {
        if (isNotNullOrEmpty(s))
            s = s.substring(0, s.length() - 1);
        return s;
    }

    public static String removeOnce(String s, String occurrence) {
        return replaceOnce(s, occurrence, EMPTY);
    }

    public static String removeFinal(String s, String occurrence) {
        return replaceFinal(s, occurrence, EMPTY);
    }

    public static String removeFirst(String s, String regex) {
        return replaceFirst(s, regex, EMPTY);
    }

    public static String removeLast(String s, String regex) {
        return replaceLast(s, regex, EMPTY);
    }

    public static String removeStart(String s, String prefix) {
        return replaceStart(s, prefix, EMPTY);
    }

    public static String removeAnyStart(String s, String... prefixes) {
        return replaceAnyStart(s, EMPTY, prefixes);
    }

    public static String removeEnd(String s, String suffix) {
        return replaceEnd(s, suffix, EMPTY);
    }

    public static String removeAnyEnd(String s, String... suffixes) {
        return replaceAnyEnd(s, EMPTY, suffixes);
    }

    public static String removeBetweenChars(String s, char opening, char closing) {
        return replaceBetweenChars(s, opening, closing, EMPTY);
    }

    public static String removeBetweenChars(String s, char delimiter) {
        return replaceBetweenChars(s, delimiter, delimiter, EMPTY);
    }

    public static String removeBetweenParentheses(String s) {
        return replaceBetweenParentheses(s, EMPTY);
    }

    public static String removeBetweenSpaces(String s) {
        return replaceBetweenSpaces(s, EMPTY);
    }

    public static String removeBetweenZeroWidthSpaces(String s) {
        return replaceBetweenZeroWidthSpaces(s, EMPTY);
    }

    //Remove zero width spaces (​)
    public static String removeZeroWidthSpaces(String s) {
        return remove(s, "\u200B");
    }

    //Remove invisible control characters and unused code points: \p{Cc} or \p{Other}
    public static String removeOtherChars(String s) {
        String regex = "\\p{Other}";
        return removeAll(s, regex);
    }

    //Remove an ASCII or Latin-1 control character (Ox00–0x1F, 0x7F–0x9F): \p{Cc} or \p{Control}
    public static String removeControlChars(String s) {
        String regex = "\\p{Control}";
        return removeAll(s, regex);
    }

    //Remove invisible formatting indicators: \p{Cf} or \p{Format}
    public static String removeFormattingChars(String s) {
        String regex = "\\p{Format}";
        return removeAll(s, regex);
    }

    public static char getFirstChar(String s) {
        if (isNotNullOrEmpty(s))
            return s.charAt(0);
        return CharHelper.NULL_CHAR;
    }

    public static char getLastChar(String s) {
        if (isNotNullOrEmpty(s))
            return s.charAt(s.length() - 1);
        return CharHelper.NULL_CHAR;
    }

    public static String getStart(String s) {
        if (isNotNullOrEmpty(s))
            return String.valueOf(s.charAt(0));
        return s;
    }

    public static String getEnd(String s) {
        if (isNotNullOrEmpty(s))
            return String.valueOf(s.charAt(s.length() - 1));
        return s;
    }

    public static String left(String s, int n) {
        if (s == null || n < 0)
            return s;
        else if (s.length() <= n)
            return s;
        return s.substring(0, n);
    }

    public static String right(String s, int n) {
        if (s == null || n < 0)
            return s;
        else if (s.length() <= n)
            return s;
        return s.substring(s.length() - n);
    }

    public static String substringBeforeLast(final String s, final String separator) {
        if (isNullOrEmpty(s) || isNullOrEmpty(separator))
            return s;
        int n = s.lastIndexOf(separator);

        if (n == IntegerHelper.INDEX_NOT_FOUND)
            return s;
        return s.substring(0, n);
    }

    public static String substringAfterLast(final String s, final String separator) {
        if (isNullOrEmpty(s) || isNullOrEmpty(separator))
            return s;
        int n = s.lastIndexOf(separator);

        if (n == IntegerHelper.INDEX_NOT_FOUND || n == s.length() - separator.length())
            return s;
        return s.substring(n + 1);
    }

    public static boolean startsWith(String s, char c) {
        if (isNotNullOrEmpty(s) && c != CharHelper.NULL_CHAR)
            return s.charAt(0) == c;
        return c == CharHelper.NULL_CHAR;
    }

    public static boolean startsWith(String s, String prefix) {
        if (isNotNullOrEmpty(s) && isNotNullOrEmpty(prefix) && s.length() >= prefix.length())
            return s.indexOf(prefix) == 0;
        return prefix == null;
    }

    public static boolean startsWithAny(String s, String... prefixes) {
        for (String prefix : prefixes) {
            if (startsWith(s, prefix)) return true;
        }
        return false;
    }

    public static boolean endsWith(String s, char c) {
        if (isNotNullOrEmpty(s) && c != CharHelper.NULL_CHAR)
            return s.charAt(s.length() - 1) == c;
        return c == CharHelper.NULL_CHAR;
    }

    public static boolean endsWith(String s, String suffix) {
        if (isNotNullOrEmpty(s) && isNotNullOrEmpty(suffix) && s.length() >= suffix.length())
            return s.endsWith(suffix);
        return suffix == null;
    }

    public static boolean endsWithAny(String s, char... characters) {
        for (char c : characters) {
            if (endsWith(s, c)) return true;
        }
        return false;
    }

    public static boolean endsWithAny(String s, String... suffixes) {
        for (String suffix : suffixes) {
            if (endsWith(s, suffix)) return true;
        }
        return false;
    }

    public static boolean equals(String a, String b) {
        return Objects.equals(a, b);
    }

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return equals(a, b);
        return a.equalsIgnoreCase(b);
    }

    public static boolean equalsAny(String s, String... occurrences) {
        if (s == null)
            return false;

        for (String occurrence : occurrences) {
            if (equals(s, occurrence)) return true;
        }
        return false;
    }

    public static boolean equalsDefault(String s) {
        return equals(s, DEFAULT_VALUE);
    }

    public static boolean containsAny(String s, String... affixes) {
        if (isNullOrEmpty(s) || isNullOrEmpty(affixes))
            return false;

        for (String affix : affixes) {
            if (affix != null && s.contains(affix))
                return true;
        }
        return false;
    }

    public static boolean hasAny(String s, char... chars) {
        if (isNullOrEmpty(s))
            return false;

        for (char character : chars) {
            for (char c : s.toCharArray()) {
                if (c == character) return true;
            }
        }
        return false;
    }

    public static boolean hasLetter(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isLetter(c)) return true;
        }
        return false;
    }

    public static boolean hasUnaccentedVowel(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isUnaccentedVowel(c)) return true;
        }
        return false;
    }

    public static boolean hasVowel(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isVowel(c)) return true;
        }
        return false;
    }

    public static boolean hasRegisteredVowel(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isRegisteredVowel(c)) return true;
        }
        return false;
    }

    public static boolean hasConsonant(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isConsonant(c)) return true;
        }
        return false;
    }

    public static boolean hasNonVowelLetter(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (!CharHelper.isVowel(c) && Character.isLetter(c)) return true;
        }
        return false;
    }

    public static boolean hasDiacritic(String s) {
        if (isNullOrBlank(s))
            return false;
        return Normalizer.normalize(s, Normalizer.Form.NFD).matches("(?s).*\\p{InCombiningDiacriticalMarks}.*");
    }

    public static boolean containsSpace(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (CharHelper.isSpace(c)) return true;
        }
        return false;
    }

    public static boolean isOnlyAlphaSpace(String s) {
        if (s == null)
            return false;

        for (char c : s.toCharArray()) {
            if (c != Separator.SPACE.getCharacter() && !Character.isLetter(c))
                return false;
        }
        return true;
    }

    public static boolean isOnlyLetters(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    public static boolean isNotOnlyLetters(String s) {
        return !isOnlyLetters(s);
    }

    public static boolean isAllUpperCase(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) || !Character.isUpperCase(c)) return false;
        }
        return true;
    }

    public static boolean isNotAllUpperCase(String s) {
        return !isAllUpperCase(s);
    }

    public static boolean isAllLowerCase(String s) {
        if (isNullOrBlank(s))
            return false;

        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c) || !Character.isLowerCase(c)) return false;
        }
        return true;
    }

    public static boolean isNotAllLowerCase(String s) {
        return !isAllLowerCase(s);
    }

    public static String escapeJavaString(String s) {
        if (s == null)
            return null;
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
                case '\\' -> sb.append("\\\\");
                case '\b' -> sb.append("\\b");
                case '\f' -> sb.append("\\f");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '\'' -> sb.append("\\'");
                case '\"' -> sb.append("\\\"");
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Unescapes a string that contains standard Java escape sequences.
     * <ul>
     * <li><strong>&#92;b &#92;f &#92;n &#92;r &#92;t &#92;" &#92;'</strong> :
     * BS, FF, NL, CR, TAB, double and single quote.</li>
     * <li><strong>&#92;X &#92;XX &#92;XXX</strong> : Octal character
     * specification (0 - 377, 0x00 - 0xFF).</li>
     * <li><strong>&#92;uXXXX</strong> : Hexadecimal based Unicode character.</li>
     * </ul>
     *
     * @param st A string optionally containing standard java escape sequences.
     * @return The translated string.
     */
    public static String unescapeJavaString(String st) {
        if (isNullOrEmpty(st))
            return st;
        StringBuilder sb = new StringBuilder(st.length());

        for (int i = 0; i < st.length(); i++) {
            char ch = st.charAt(i);

            if (ch == '\\') {
                char nextChar = (i == st.length() - 1) ? '\\' : st
                        .charAt(i + 1);

                // Octal escape?
                if (nextChar >= '0' && nextChar <= '7') {
                    String code = EMPTY + nextChar;
                    i++;
                    if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                            && st.charAt(i + 1) <= '7') {
                        code += st.charAt(i + 1);
                        i++;
                        if ((i < st.length() - 1) && st.charAt(i + 1) >= '0'
                                && st.charAt(i + 1) <= '7') {
                            code += st.charAt(i + 1);
                            i++;
                        }
                    }
                    sb.append((char) Integer.parseInt(code, 8));
                    continue;
                }

                switch (nextChar) {
                    case '\\' -> ch = '\\';
                    case 'b' -> ch = '\b';
                    case 'f' -> ch = '\f';
                    case 'n' -> ch = '\n';
                    case 'r' -> ch = '\r';
                    case 't' -> ch = '\t';
                    case '\"' -> ch = '\"';
                    case '\'' -> ch = '\'';

                    // Hex Unicode: u????
                    case 'u' -> {
                        if (i >= st.length() - 5) {
                            ch = 'u';
                            break;
                        }
                        int code = Integer.parseInt(EMPTY + st.charAt(i + 2) + st.charAt(i + 3) +
                                st.charAt(i + 4) + st.charAt(i + 5), 16);
                        sb.append(Character.toChars(code));
                        i += 5;
                        continue;
                    }
                }
                i++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }

    public static String md5(String s) {
        try {
            final MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            final byte[] array = md.digest(s.getBytes());
            final StringBuffer sb = new StringBuffer();

            for (byte b : array) {
                sb.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    public static String sha256(final String base) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            final byte[] array = md.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuffer sb = new StringBuffer();

            for (byte b : array) {
                final String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1)
                    sb.append('0');
                sb.append(hex);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCharacter(String... codePoints) {
        StringBuilder sb = new StringBuilder();

        for (String codePoint : codePoints) {
            if (StringHelper.isNotNullOrBlank(codePoint) && Validation.isUtf(codePoint))
                sb.appendCodePoint(Integer.decode(codePoint.replace("U+", "0x")));
        }
        return sb.toString();
    }
}
