package com.memoeslink.generator.spanish;

import com.memoeslink.generator.common.CharHelper;
import com.memoeslink.generator.common.Separator;
import com.memoeslink.generator.common.StringHelper;

import java.text.Normalizer;
import java.util.List;

/**
 * Initially coded by Edmond Duke, improved by Memoeslink
 */

public class SyllableSeparator {
    private String word;
    private String remains;
    private List<String> syllables;

    public SyllableSeparator() {
        this(StringHelper.EMPTY);
    }

    public SyllableSeparator(String word) {
        this.setWord(word);
    }

    public SyllableSeparator(int[] ascii) {
        this();

        for (int i : ascii) {
            remains += String.valueOf((char) ascii[i]);
        }
    }

    public SyllableSeparator(char[] chars) {
        this(String.valueOf(chars));
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = StringHelper.defaultIfBlank(word);
        this.remains = word;
        this.syllables = null;
    }

    private static int getLetterVal(char c) {
        switch ((int) c) {
            case -1:
                return -1;
            case 97: //a
            case 225: //á
                return 1;
            case 101: //e
            case 233: //é
                return 2;
            case 104: //h
                return 6;
            case 105: //i
            case 237: //í
                return 4;
            case 111: //o
            case 243: //ó
                return 3;
            case 117: //u
            case 252: //ü
            case 250: //ú
                return 5;
            default:
                return 19;
        }
    }

    private static String getSyllable(String s) {
        char x, y, z;

        if (s.length() <= 1)
            return s;
        else if (s.length() == 2) {
            x = s.charAt(0);
            y = s.charAt(1);

            if (getLetterVal(x) < 6 && getLetterVal(y) < 6 && isHiatus(x, y))
                return s.substring(0, 1);
            return s;
        } else {
            x = s.charAt(0);
            y = s.charAt(1);
            z = s.charAt(2);

            if (getLetterVal(x) < 6) { //V ? ?
                if (getLetterVal(y) < 6) { //V V ?
                    if (getLetterVal(z) < 6) { //V V V
                        if (isHiatus(x, y))
                            return s.substring(0, 1);
                        else if (isHiatus(y, z))
                            return s.substring(0, 2);
                        return s.substring(0, 3);
                    } else if (isHiatus(x, y)) //V V C
                        return s.substring(0, 1);
                    return s.substring(0, 2);
                } else { //V C ?
                    if (getLetterVal(z) < 6) { //V C V
                        if (getLetterVal(y) == 6) { //V H C
                            if (isHiatus(x, z))
                                return s.substring(0, 1);
                            return s.substring(0, 3);
                        }
                        return s.substring(0, 1);
                    } else { //V C C
                        if (isPairOfConsonants(y, z))
                            return s.substring(0, 1);
                        return s.substring(0, 2);
                    }
                }
            } else { //C ? ?
                if (getLetterVal(y) < 6) { //C V ?
                    if (getLetterVal(z) < 6) { //C V V
                        if (StringHelper.equalsAny(s.substring(0, 3), "gue", "gui", "que", "qui"))
                            return s.substring(0, 3);
                        else if (isHiatus(y, z))
                            return s.substring(0, 2);
                        return s.substring(0, 3);
                    }
                    return s.substring(0, 2); //C V C
                } else { //C C ?
                    if (getLetterVal(z) < 6 && isPairOfConsonants(x, y)) //C C V
                        return s.substring(0, 3);
                    return s.substring(0, 1); //C C C
                }
            }
        }
    }

    private static String getRemains(String s) {
        return s.substring(getSyllable(s).length());
    }

    private static boolean isHiatus(char a, char b) {
        if (getLetterVal(a) < 4) { //VA + ?
            if (getLetterVal(b) < 4) //VA + VA
                return true;
            else return CharHelper.equalsAny(b, 'í', 'ú'); //VA + VC
        } else { //VC + ?
            if (getLetterVal(b) < 4 && CharHelper.equalsAny(a, 'í', 'ú')) //VC + VA
                return true;
            return a == b; //VC + VC
        }
    }

    private static boolean isPairOfConsonants(char a, char b) {
        if (CharHelper.equalsAny(a, 'b', 'c', 'd', 'f', 'g', 'p', 'r', 't') && b == 'r')
            return true;

        if (CharHelper.equalsAny(a, 'b', 'c', 'f', 'g', 'l', 'p', 't') && b == 'l')
            return true;
        return a == 'c' && b == 'h';
    }

    private static boolean hasOnlyConsonants(String s) {
        char[] c = s.toCharArray();
        int k = 0;

        for (int i = 0; i < s.length(); i++) {
            if (getLetterVal(c[i]) > 5)
                k = k + 1;
        }
        return k == s.length();
    }

    private static boolean hasNonHiatusVowels(String a, String b) {
        char c1 = a.charAt(a.length() - 1);
        char c2 = b.charAt(0);

        if (getLetterVal(c1) < 6 && getLetterVal(c2) < 6)
            return !isHiatus(c1, c2);
        return false;
    }

    public String getSyllableBySyllable() {
        String s = StringHelper.EMPTY;
        String temp;

        for (int i = 0, k = remains.length(); i < k; i++) {
            temp = getSyllable(remains);

            if (i == 0)
                s = s + temp;
            else {
                if (hasOnlyConsonants(temp))
                    s = s + temp;
                else {
                    if (hasNonHiatusVowels(s, temp))
                        s = s + temp;
                    else {
                        if (hasOnlyConsonants(s))
                            s = s + temp;
                        else
                            s = s + Separator.HYPHEN.getCharacter() + temp;
                    }
                }
            }
            i = i + temp.length() - 1;
            remains = getRemains(remains);
        }
        return s;
    }

    public List<String> getSyllables() {
        if (syllables == null)
            syllables = StringHelper.splitByHyphen(getSyllableBySyllable());
        return syllables;
    }

    public boolean isOxytone() {
        if (getSyllables().size() == 0)
            return false;
        return StringHelper.hasAny(syllables.get(syllables.size() - 1), 'á', 'é', 'í', 'ó', 'ú') ==
                StringHelper.endsWithAny(syllables.get(syllables.size() - 1), 'n', 's');
    }

    public boolean isParoxytone() {
        if (getSyllables().size() < 2)
            return false;
        return StringHelper.hasAny(syllables.get(syllables.size() - 2), 'á', 'é', 'í', 'ó', 'ú') ==
                !StringHelper.endsWithAny(syllables.get(syllables.size() - 1), 'n', 's');
    }

    public boolean isProparoxytone() {
        if (getSyllables().size() < 3)
            return false;
        return StringHelper.hasAny(syllables.get(syllables.size() - 3), 'á', 'é', 'í', 'ó', 'ú');
    }

    public boolean isPreproparoxytone() {
        if (getSyllables().size() < 4)
            return false;
        return StringHelper.hasAny(syllables.get(syllables.size() - 4), 'á', 'é', 'í', 'ó', 'ú');
    }

    public String convertToProparoxytone() {
        if (!isParoxytone())
            return StringHelper.EMPTY;
        List<String> syllables = this.syllables;
        syllables.set(syllables.size() - 3, StringHelper.stripAccents(syllables.get(syllables.size() - 3)));
        syllables.add("es");
        char[] letters = syllables.get(syllables.size() - 3).toCharArray();

        for (int n = letters.length - 1; n >= 0; n--) {
            char letter = letters[n];

            if (!CharHelper.isUnaccentedVowel(letter))
                continue;
            String accentedLetter = String.format("%s\u0301", CharHelper.getUnicode(letter));
            accentedLetter = StringHelper.unescapeJavaString(accentedLetter);
            letters[n] = Normalizer.normalize(accentedLetter, Normalizer.Form.NFC).charAt(0);
        }
        syllables.set(syllables.size() - 3, String.valueOf(letters));
        return String.join(StringHelper.EMPTY, syllables);
    }
}
