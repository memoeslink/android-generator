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
import java.util.Locale;

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

    public char getChar(char[] chars) {
        return getChar(chars, r.getInt(chars.length));
    }

    public char getChar(char[] chars, int index) {
        if (chars != null && chars.length > 0) {
            index = IntegerHelper.defaultIndex(index, chars.length);
            return chars[index];
        }
        return CharHelper.EMPTY_CHAR;
    }

    public char getChar(String s) {
        return getChar(s, r.getInt(s.length()));
    }

    public char getChar(String s, int index) {
        if (StringHelper.isNotNullOrEmpty(s)) {
            index = IntegerHelper.defaultIndex(index, s.length());
            return s.charAt(index);
        }
        return CharHelper.EMPTY_CHAR;
    }

    public String getString(String s) {
        return String.valueOf(getChar(s));
    }

    public String getString(String s, int index) {
        return String.valueOf(getChar(s, index));
    }

    public String getString(String[] strings) {
        return getString(strings, r.getInt(strings.length));
    }

    public String getString(String[] strings, int index) {
        if (ArrayHelper.isNotNullOrEmpty(strings)) {
            index = IntegerHelper.defaultIndex(index, strings.length);
            return strings[index];
        }
        return StringHelper.EMPTY;
    }

    public String getSplitString(String s) {
        String[] parts = StringHelper.splitByParagraphMark(s);
        return getString(parts);
    }

    public String getSplitString(String s, int index) {
        String[] parts = StringHelper.splitByParagraphMark(s);
        return getString(parts, index);
    }

    public String getLineFromFile(String filename) {
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

                    for (int x = 0, limit = r.getIntInRange(1, lineCount); x < limit; x++) {
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

    public String getStrFromResBundle(Locale locale, String key) {
        if (StringHelper.isNullOrBlank(key))
            return StringHelper.EMPTY;
        String s = ResourceBundleManager.getInstance().getResourceBundle(locale).getString(key);

        if (StringHelper.contains(s, "\t")) {
            String[] parts = StringHelper.splitByTab(s);
            return getString(parts);
        }
        return s;
    }

    public static ResourceGetter with(Randomizer r) {
        return new ResourceGetter(r);
    }

    public static ResourceGetter without() {
        return with(null);
    }
}
