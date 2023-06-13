package com.memoeslink.generator.common;

public class IntegerHelper {
    public static final int INDEX_NOT_FOUND = -1;
    public static final int INITIAL_INDEX = 0;

    public static boolean isBetween(int n, int min, int max) {
        return min <= n && n <= max;
    }

    public static int defaultIndex(int index, int length) {
        if (index >= INITIAL_INDEX && index <= length - 1)
            return index;
        return INITIAL_INDEX;
    }

    public static int defaultByMin(int n, int min) {
        if (min == Integer.MIN_VALUE || n < min)
            return min;
        return n;
    }

    public static int defaultByMax(int n, int max) {
        if (max == Integer.MAX_VALUE || n > max)
            return max;
        return n;
    }

    public static int defaultByRange(int n, int min, int max) {
        if (min > max)
            return 0;

        if (isBetween(n, min, max))
            return n;
        return n < min ? min : max;
    }

    public static int tryParse(String s, int defaultValue) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int tryParseOrZero(String s) {
        return tryParse(s, 0);
    }

    public static Integer tryParseOrNull(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static int countDigits(int n) {
        if (n < 0)
            n *= -1;

        if (n < 100000) {
            if (n < 100) {
                if (n < 10) return 1;
                else return 2;
            } else {
                if (n < 1000) return 3;
                else {
                    if (n < 10000) return 4;
                    else return 5;
                }
            }
        } else {
            if (n < 10000000) {
                if (n < 1000000) return 6;
                else return 7;
            } else {
                if (n < 100000000) return 8;
                else {
                    if (n < 1000000000) return 9;
                    else return 10;
                }
            }
        }
    }
}
