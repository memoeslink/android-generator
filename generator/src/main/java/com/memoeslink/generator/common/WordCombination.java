package com.memoeslink.generator.common;

public enum WordCombination {
    ONLY_SLASH('/', '/', '\0'),
    ONLY_VERTICAL_BAR('|', '|', '\0'),
    SLASH_AND_PARENTHESES('/', '(', ')'),
    SLASH_AND_SQUARE_BRACKETS('/', '[', ']'),
    VERTICAL_BAR_AND_PARENTHESES('|', '(', ')'),
    VERTICAL_BAR_AND_SQUARE_BRACKETS('|', '[', ']');

    private final char combinator;
    private final char starter;
    private final char finisher;

    private WordCombination(char combinator, char starter, char finisher) {
        this.combinator = combinator;
        this.starter = starter;
        this.finisher = finisher;
    }

    public char getCombinator() {
        return combinator;
    }

    public char getStarter() {
        return starter;
    }

    public char getFinisher() {
        return finisher;
    }
}
