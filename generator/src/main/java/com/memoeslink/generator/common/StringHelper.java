package com.memoeslink.generator.common;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringHelper {
    public static final String EMPTY = "";

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
        return defaultIfNull(s, Database.DEFAULT_VALUE);
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
        return defaultIfEmpty(s, Database.DEFAULT_VALUE);
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
        return defaultIfBlank(s, Database.DEFAULT_VALUE);
    }

    public static String defaultIfBlank(String s) {
        return defaultIfBlank(s, EMPTY);
    }

    public static String defaultIfBlank(String s, String defaultValue) {
        if (isNullOrBlank(s))
            return defaultValue == null ? EMPTY : defaultValue;
        return s;
    }

    public static String prependIfNotNull(String s, String prefix) {
        if (s == null || prefix == null)
            return null;
        return prefix + s;
    }

    public static String prependIfNotEmpty(String s, String prefix) {
        if (isNullOrEmpty(s) || prefix == null)
            return s;
        return prefix + s;
    }

    public static String prependIfNotBlank(String s, String prefix) {
        if (isNullOrBlank(s) || prefix == null)
            return s;
        return prefix + s;
    }

    public static String appendIfNotNull(String s, String suffix) {
        if (s == null || suffix == null)
            return null;
        return s + suffix;
    }

    public static String appendIfNotEmpty(String s, String suffix) {
        if (isNullOrEmpty(s) || suffix == null)
            return s;
        return s + suffix;
    }

    public static String appendIfNotBlank(String s, String suffix) {
        if (isNullOrBlank(s) || suffix == null)
            return s;
        return s + suffix;
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

    public static List<String> split(String s, char delimiter) {
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

    public static List<String> splitBySpace(String s) {
        return split(s, Separator.SPACE.getCharacter());
    }

    public static List<String> splitByHyphen(String s) {
        return split(s, Separator.HYPHEN.getCharacter());
    }

    public static String[] splitByParagraphMark(String s) {
        if (isNotNullOrEmpty(s))
            return s.split("¶[ ]*");
        return new String[]{};
    }

    public static String join(String a, String b) {
        return join(EMPTY, a, b);
    }

    public static String join(char c, String a, String b) {
        return join(String.valueOf(c), a, b);
    }

    public static String join(String separator, String a, String b) {
        separator = defaultIfNull(separator);
        a = defaultIfNull(a);
        b = defaultIfNull(b);

        if (isNotNullOrEmpty(a) && isNullOrEmpty(b))
            return a;

        if (isNullOrEmpty(a) && isNotNullOrEmpty(b))
            return b;
        return a + separator + b;
    }

    public static String joinWithSpace(String a, String b) {
        return join(String.valueOf(Separator.SPACE.getCharacter()), a, b);
    }

    public static String joinWithHyphen(String a, String b) {
        return join(String.valueOf(Separator.HYPHEN.getCharacter()), a, b);
    }

    public static String joinWithFileSeparator(String a, String b) {
        return join(File.separator, a, b);
    }

    public static String join(String... strings) {
        return join(EMPTY, strings);
    }

    public static String join(List<String> strings) {
        if (strings == null)
            return null;
        return join(strings.toArray(new String[0]));
    }

    public static String join(char c, String... strings) {
        return join(String.valueOf(c), strings);
    }

    public static String join(List<String> strings, char c) {
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

    public static String join(List<String> strings, String separator) {
        if (strings == null)
            return null;
        return join(separator, strings.toArray(new String[0]));
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
        return defaultIfEmpty(trimToEmpty(s), Database.DEFAULT_VALUE);
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
        if (isNullOrEmpty(s))
            return s;

        if (s.length() > 4)
            return s.replaceAll(".(?=.{4})", "*");
        return s.replaceAll(".", "*");
    }

    public static String substring(String s, int startIndex, int endIndex) {
        if (isNullOrEmpty(s))
            return s;

        if (startIndex < 0)
            startIndex = s.length() + startIndex;
        startIndex = IntegerHelper.defaultInt(startIndex, 0, s.length());

        if (endIndex < 0)
            endIndex = s.length() + endIndex;
        endIndex = IntegerHelper.defaultInt(endIndex, startIndex, s.length());
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
        return replace(s, String.valueOf(occurrence), String.valueOf(replacement));
    }

    public static String replace(String s, String occurrence, String replacement) {
        if (isNullOrEmpty(s) || occurrence == null || replacement == null)
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

    public static String replaceEach(String s, String[] occurrences, String replacements[]) {
        if (isNullOrEmpty(s) || occurrences == null || replacements == null)
            return s;

        for (int n = 0; n < occurrences.length && n < replacements.length; n++) {
            if (isNullOrEmpty(occurrences[n]) || replacements[n] == null)
                continue;
            s = replace(s, occurrences[n], replacements[n]);
        }
        return s;
    }

    public static String replaceAll(String s, String regex, String replacement) {
        if (isNotNullOrEmpty(s) && replacement != null)
            return s.replaceAll(regex, replacement);
        return s;
    }

    public static String remove(String s, String occurrence) {
        if (isNotNullOrEmpty(s) && isNotNullOrEmpty(occurrence))
            return replace(s, occurrence, EMPTY);
        return s;
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
        if (startsWith(s, prefix))
            return s.substring(prefix.length());
        return s;
    }

    public static String removeEnd(String s, String suffix) {
        if (endsWith(s, suffix))
            return s.substring(0, s.length() - suffix.length());
        return s;
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

        if (n == IntegerHelper.INDEX_NOT_FOUND || n <= s.length() - separator.length())
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

    public static boolean equalsIgnoreCase(String a, String b) {
        if (a == null || b == null)
            return Objects.equals(a, b);
        return a.equalsIgnoreCase(b);
    }

    public static boolean equalsAny(String s, String... occurrences) {
        if (s == null)
            return false;

        for (String occurrence : occurrences) {
            if (s.equals(occurrence)) return true;
        }
        return false;
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
                    case '\\':
                        ch = '\\';
                        break;
                    case 'b':
                        ch = '\b';
                        break;
                    case 'f':
                        ch = '\f';
                        break;
                    case 'n':
                        ch = '\n';
                        break;
                    case 'r':
                        ch = '\r';
                        break;
                    case 't':
                        ch = '\t';
                        break;
                    case '\"':
                        ch = '\"';
                        break;
                    case '\'':
                        ch = '\'';
                        break;
                    // Hex Unicode: u????
                    case 'u':
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
                i++;
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}
