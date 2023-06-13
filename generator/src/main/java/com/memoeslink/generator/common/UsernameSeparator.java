package com.memoeslink.generator.common;

public enum UsernameSeparator {
    HYPHEN('-'),
    UNDERSCORE('_'),
    DOT('.');

    private final char character;

    private UsernameSeparator(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
