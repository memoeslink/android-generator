package com.memoeslink.generator.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor {
    public static final String WORD_REGEX = "\\p{L}+";
    public static final Pattern WORD_PATTERN = Pattern.compile(WORD_REGEX);
    public static final String LETTER_REGEX = "[\\w\\p{L}\\s'ªº∅]";
    public static final String COMBINED_WORDS_REGEX = "(\\p{L}+)(\\[(\\p{L})\\]|\\((\\p{L})\\)|[\\|\\/\\-](\\p{L}+))?";
    public static final Pattern COMBINED_WORDS_PATTERN = Pattern.compile(COMBINED_WORDS_REGEX);
    public static final String EXTENDED_WORD_REGEX = "\\[[\\^]{0,2}" + LETTER_REGEX + "*(\\[" + LETTER_REGEX + "*(," + LETTER_REGEX + "*)?\\])?" + LETTER_REGEX + "*\\]";
    public static final Pattern EXTENDED_WORD_PATTERN = Pattern.compile(EXTENDED_WORD_REGEX);
    public static final String ROMAN_NUMERAL_REGEX = "(^|\\s+)M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})($|\\s+)";
    public static final Pattern ROMAN_NUMERAL_PATTERN = Pattern.compile(ROMAN_NUMERAL_REGEX);
    public static final String LATIN_OR_SPACE_REGEX = "[\\p{L}\\s]+";
    public static final Pattern LATIN_OR_SPACE_PATTERN = Pattern.compile(LATIN_OR_SPACE_REGEX);
    public static final Pattern GENDER_PATTERN = Pattern.compile("｢([0-2])｣");
    public static final String DOUBLE_FULL_STOP_REGEX = "(\\.(\\s*</?\\w+>\\s*)*)\\.";
    public static final Pattern DOUBLE_FULL_STOP_PATTERN = Pattern.compile(DOUBLE_FULL_STOP_REGEX);

    public static Word getFirstSeveredWord(String s) {
        List<Word> words = severWords(s, 1);
        return words.size() == 1 ? words.get(0) : new Word();
    }

    public static List<Word> getSeveredWords(String s) {
        return severWords(s, Integer.MAX_VALUE);
    }

    private static List<Word> severWords(String s, int amount) {
        s = StringHelper.defaultIfBlank(s);
        List<Word> words = new ArrayList<>();
        Matcher m = COMBINED_WORDS_PATTERN.matcher(s);
        int n = 1;

        while (m.find()) {
            Word word = new Word();
            word.setWord(m.group(1));
            word.setMasculineForm(m.group(1));

            assignation:
            {
                if (m.group(2) == null) {
                    word.setNeutralForm(m.group(1));
                    break assignation;
                }
                String ending = StringHelper.defaultIfNull(m.group(3), StringHelper.defaultIfNull(m.group(4)));

                if (!ending.isEmpty()) {
                    word.setFeminineForm(feminize(m.group(1), ending));
                    break assignation;
                }
                word.setFeminineForm(m.group(5));
            }
            word.setGender(Gender.NEUTRAL);
            words.add(word);

            if (n >= amount)
                break;
            n++;
        }
        return words;
    }

    public static String joinWords(List<Word> words, Gender gender) {
        if (words == null || words.size() == 0)
            return StringHelper.EMPTY;
        StringBuilder sb = new StringBuilder();
        gender = gender != null ? gender : Gender.UNDEFINED;

        for (Word word : words) {
            if (sb.length() > 0)
                sb.append(Separator.SPACE.getCharacter());

            switch (gender) {
                case MASCULINE:
                    sb.append(word.getMasculineForm());
                case FEMININE:
                    sb.append(word.getFeminineForm());
                case NEUTRAL:
                    sb.append(word.getNeutralForm());
                case UNDEFINED:
                default:
                    sb.append(word.getWord());
            }
        }
        return sb.toString();
    }

    public static String feminize(String s) {
        return feminize(s, "a");
    }

    public static String feminize(String s, String ending) {
        if (StringHelper.isNotOnlyLetters(s) || StringHelper.isNotOnlyLetters(ending))
            return s;

        if (StringHelper.endsWith(s, ending))
            return s;

        if (StringHelper.endsWithAny(s, com.memoeslink.generator.english.Constant.VOWELS.toCharArray()))
            return StringHelper.join(StringHelper.removeLastChar(s), ending);
        return StringHelper.join(s, ending);
    }

    public static String feminizeOnVowelEnd(String s) {
        return feminizeOnVowelEnd(s, "a");
    }

    public static String feminizeOnVowelEnd(String s, String ending) {
        if (StringHelper.isNotOnlyLetters(s) || StringHelper.isNotOnlyLetters(ending))
            return s;

        if (StringHelper.endsWith(s, ending))
            return s;

        if (StringHelper.endsWithAny(s, com.memoeslink.generator.english.Constant.VOWELS.toCharArray()))
            return StringHelper.join(StringHelper.removeLastChar(s), ending);
        return s;
    }

    public static String feminizeWithDefaultWhen(String s, String... occurrences) {
        return feminizeWhen(s, "a", occurrences);
    }

    public static String feminizeWhen(String s, String ending, String... occurrences) {
        if (StringHelper.isNotOnlyLetters(s) || StringHelper.isNotOnlyLetters(ending))
            return s;

        if (StringHelper.endsWith(s, ending))
            return s;

        for (String occurrence : occurrences) {
            if (StringHelper.endsWith(s, occurrence))
                return StringHelper.removeEnd(s, occurrence) + ending;
        }
        return s;
    }

    public static String genderify(String s, Gender gender) {
        return genderify(s, gender, WordCombination.ONLY_SLASH);
    }

    public static String genderify(String s, Gender gender, WordCombination combination) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        StringBuffer sb = new StringBuffer();
        gender = gender != null ? gender : Gender.UNDEFINED;
        combination = combination != null ? combination : WordCombination.SLASH_AND_SQUARE_BRACKETS;
        Matcher m = COMBINED_WORDS_PATTERN.matcher(s);

        while (m.find()) {
            switch (gender) {
                case MASCULINE:
                    m.appendReplacement(sb, m.group(1));
                    break;
                case FEMININE:
                    if (StringHelper.isNullOrEmpty(m.group(2))) {
                        m.appendReplacement(sb, m.group(1));
                        continue;
                    }
                    String ending = StringHelper.defaultIfNull(m.group(3), StringHelper.defaultIfNull(m.group(4)));

                    if (!ending.isEmpty())
                        m.appendReplacement(sb, feminize(m.group(1), ending));
                    else
                        m.appendReplacement(sb, m.group(5));
                    break;
                case NEUTRAL:
                    m.appendReplacement(sb, getFirstSeveredWord(m.group(0)).getNeutralForm());
                    break;
                case UNDEFINED:
                default:
                    if (combination == WordCombination.SLASH_AND_SQUARE_BRACKETS)
                        m.appendReplacement(sb, m.group(0));
                    else
                        m.appendReplacement(sb, getFirstSeveredWord(m.group(0)).getCombinedForm(combination));
                    break;
            }
        }
        m.appendTail(sb);
        return sb.toString();
    }

    public static TextComponent genderifyStr(String s, Gender gender) {
        gender = gender != null ? gender : Gender.UNDEFINED;
        TextComponent component = new TextComponent();
        component.setText(s);
        Matcher matcher = EXTENDED_WORD_PATTERN.matcher(s);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            String replacement;
            String substring = StringHelper.substring(matcher.group(), 1, matcher.group().length() - 1);
            boolean capitalized = !substring.equals(substring = StringHelper.removeStart(substring, "^"));
            boolean fullyCapitalized = capitalized && !substring.equals(substring = StringHelper.removeStart(substring, "^"));

            if (matcher.group(1) != null) {
                String prefix = StringHelper.substringBefore(substring, "[");
                String suffix = StringHelper.substringAfter(substring, "]");
                substring = StringHelper.substringBetween(substring, "[", "]");
                List<String> items = Arrays.asList(substring.split(",\\s*"));
                boolean shortened = false;

                if (StringHelper.isNotNullOrEmpty(substring) && StringHelper.isNullOrBlank(suffix)) {
                    List<String> sortedItems = new ArrayList<>(items);

                    Collections.sort(sortedItems, (item, otherItem) -> {
                        if (item.length() > otherItem.length())
                            return 1;
                        else
                            return item.compareTo(otherItem);
                    });

                    if (sortedItems.size() >= 2) {
                        if (sortedItems.get(0).length() <= 1 && sortedItems.get(sortedItems.size() - 1).length() <= 1)
                            shortened = true;
                    }
                }

                if (items.size() == 0)
                    replacement = prefix + suffix;
                else if (items.size() == 1)
                    replacement = prefix + items.get(0) + suffix;
                else if (gender == Gender.UNDEFINED || gender == Gender.NEUTRAL) {
                    if (shortened)
                        replacement = prefix + items.get(0) + "(" + StringHelper.join(items.subList(1, items.size()), ", ") + ")";
                    else {
                        for (int n = 0; n < items.size(); n++) {
                            items.set(n, prefix + items.get(n) + suffix);
                        }
                        replacement = StringHelper.join(items, "/");
                    }
                } else
                    replacement = prefix + (gender == Gender.MASCULINE ? items.get(0) : items.get(1)) + suffix;
            } else
                replacement = substring;

            if (fullyCapitalized)
                replacement = StringHelper.capitalize(replacement);
            else if (capitalized)
                replacement = StringHelper.capitalizeFirst(replacement);
            matcher.appendReplacement(sb, replacement);
        }
        matcher.appendTail(sb);

        if (StringHelper.isNotNullOrBlank(sb.toString()))
            component.setText(sb.toString());
        component.setHegemonicGender(gender);
        return component;
    }

    public static String demonize(String s, String defaultName) {
        if (StringHelper.isNullOrBlank(s))
            return s;
        s = ROMAN_NUMERAL_PATTERN.matcher(s).replaceAll(String.valueOf(Separator.SPACE.getCharacter())).trim();

        if (StringHelper.isOnlyAlphaSpace(s) || LATIN_OR_SPACE_PATTERN.matcher(s).matches()) {
        } else
            s = defaultName;
        s = s.toLowerCase();
        s = StringHelper.normalize(s);
        s = StringHelper.reverse(s);
        s = StringHelper.capitalize(s);
        return s;
    }

    public static String turnIntoGibberish(String s) {
        if (StringHelper.isNullOrEmpty(s))
            return s;
        StringBuilder sb = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isSpaceChar(c))
                sb.append(Separator.SPACE.getCharacter());
            else
                sb.append(ResourceGetter.without().getChar(Constant.ACCENTED_LETTERS));
        }
        return sb.toString();
    }

    public static String removeDoubleFullStop(String s) {
        Matcher matcher = DOUBLE_FULL_STOP_PATTERN.matcher(s);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
