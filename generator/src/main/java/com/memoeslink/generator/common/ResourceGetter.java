package com.memoeslink.generator.common;

import com.memoeslink.common.Randomizer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.HashMap;

public class ResourceGetter {
    private static Randomizer r;
    private static final HashMap<String, Integer> COUNT_REGISTRY = new HashMap<>();
    private static final String RESOURCES_PATH = "src/main/resources/%s";

    static {
        r = new Randomizer();
    }

    private ResourceGetter(Randomizer r) {
        validation:
        {
            if (r == null || ResourceGetter.r == null) {
                ResourceGetter.r = r != null ? r : new Randomizer();
                break validation;
            }

            if (r.getSeed() == null && ResourceGetter.r.getSeed() == null)
                break validation;

            if (r.getSeed() == null ^ ResourceGetter.r.getSeed() == null) {
                ResourceGetter.r = new Randomizer(r.getSeed());
                break validation;
            }

            if (!r.getSeed().equals(ResourceGetter.r.getSeed()))
                ResourceGetter.r = r;
        }
    }

    public static char getChar(char[] chars) {
        return getChar(chars, r.getInt(chars.length));
    }

    public static char getChar(char[] chars, int index) {
        if (chars != null && chars.length > 0) {
            index = IntegerHelper.defaultIndex(index, chars.length);
            return chars[index];
        }
        return CharHelper.NULL_CHAR;
    }

    public static char getChar(String s) {
        return getChar(s, r.getInt(s.length()));
    }

    public static char getChar(String s, int index) {
        if (StringHelper.isNotNullOrEmpty(s)) {
            index = IntegerHelper.defaultIndex(index, s.length());
            return s.charAt(index);
        }
        return CharHelper.NULL_CHAR;
    }

    public static String getString(String s) {
        return String.valueOf(getChar(s));
    }

    public static String getString(String s, int index) {
        return String.valueOf(getChar(s, index));
    }

    public static String getString(String[] strings) {
        return getString(strings, r.getInt(strings.length));
    }

    public static String getString(String[] strings, int index) {
        if (StringHelper.isNotNullOrEmpty(strings)) {
            index = IntegerHelper.defaultIndex(index, strings.length);
            return strings[index];
        }
        return StringHelper.EMPTY;
    }

    public static String getSplitString(String s) {
        String[] parts = StringHelper.splitByParagraphMark(s);

        if (parts.length > 0)
            return parts[r.getInt(parts.length)];
        return StringHelper.EMPTY;
    }

    public static String getSplitString(String s, int index) {
        String[] parts = StringHelper.splitByParagraphMark(s);

        if (parts.length > 0) {
            index = IntegerHelper.defaultIndex(index, parts.length);
            return parts[index];
        }
        return StringHelper.EMPTY;
    }

    public static String getLineFromFile(String filename) {
        String s = StringHelper.EMPTY;
        File file = new File(String.format(RESOURCES_PATH, filename));

        if (file.exists()) {
            FileInputStream is;

            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return s;
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            LineNumberReader lnr = new LineNumberReader(br);
            Integer lineCount = 0;

            if (COUNT_REGISTRY.containsKey(filename))
                lineCount = COUNT_REGISTRY.get(filename);
            else {
                try {
                    lnr.skip(Long.MAX_VALUE);
                    lineCount = lnr.getLineNumber() + 1;
                    COUNT_REGISTRY.put(filename, lineCount);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (lineCount != null && lineCount > 0) {
                try {
                    is.getChannel().position(0);

                    for (int x = 0, limit = r.getInt(1, lineCount); x < limit; x++) {
                        s = br.readLine();
                    }
                    br.close();
                    lnr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            br = null;
            lnr = null;
        }
        return s;
    }

    public static char getLetter() {
        return getChar(com.memoeslink.generator.english.Constant.LETTERS);
    }

    public static char getUpperCase() {
        return getChar(com.memoeslink.generator.english.Constant.UPPERCASE_ALPHABET);
    }

    public static char getLowerCase() {
        return getChar(com.memoeslink.generator.english.Constant.LOWERCASE_ALPHABET);
    }

    public static ResourceGetter with(Randomizer r) {
        return new ResourceGetter(r);
    }
}
