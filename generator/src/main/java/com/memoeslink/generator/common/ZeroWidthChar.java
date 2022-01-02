package com.memoeslink.generator.common;

public enum ZeroWidthChar {
    ZERO_WIDTH_SPACE('\u200B'),
    ZERO_WIDTH_NON_JOINER('\u200C'),
    ZERO_WIDTH_JOINER('\u200D'),
    WORD_JOINER('\u2060'),
    ZERO_WIDTH_NO_BREAK_SPACE('\uFEFF');

    private final char character;

    private ZeroWidthChar(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
