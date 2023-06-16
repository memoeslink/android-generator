package com.memoeslink.generator.common;

public enum Delimiter {
    FILE_SEPARATOR('␜'),
    GROUP_SEPARATOR('␝'),
    RECORD_SEPARATOR('␞'),
    UNIT_SEPARATOR('␟');

    private final char character;

    private Delimiter(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
