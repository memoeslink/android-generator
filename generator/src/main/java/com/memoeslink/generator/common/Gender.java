package com.memoeslink.generator.common;

import android.content.Context;

import com.memoeslink.generator.R;

import org.memoeslink.IntegerHelper;
import org.memoeslink.StringHelper;

import java.util.HashMap;

public enum Gender {
    UNDEFINED(-1, "⁇", "⁇", "⍰", "�", "□", "?"),
    MASCULINE(1, "♂", "⚨", "⚩", "⁇", "⍰", "�", "□", "?"),
    FEMININE(2, "♀", "⚨", "⚩", "⁇", "⍰", "�", "□", "?"),
    NEUTRAL(0, "⚲", "⚪", "⁇", "⍰", "�", "□", "?");

    private final int value;
    private final String[] glyphs;
    private static final HashMap<Integer, Gender> LOOKUP = new HashMap<>();

    static {
        for (Gender gender : Gender.values()) {
            LOOKUP.put(gender.getValue(), gender);
        }
    }

    private Gender(int value, String... glyphs) {
        this.value = value;
        this.glyphs = glyphs;
    }

    public int getValue() {
        return value;
    }

    public String getGlyph() {
        return glyphs[0];
    }

    public String getName(Context context, int type) {
        type = IntegerHelper.defaultByRange(type, 1, 4);
        String genderName = context.getResources().getStringArray(R.array.gender)[this.ordinal()];

        return switch (type) {
            case 1 -> genderName;
            case 2 -> StringHelper.capitalizeFirst(genderName);
            case 3 -> genderName.toUpperCase();
            case 4 -> StringHelper.getStart(genderName).toUpperCase();
            default -> this.toString();
        };
    }

    public static Gender get(int value) {
        return LOOKUP.getOrDefault(value, UNDEFINED);
    }
}
