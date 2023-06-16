package com.memoeslink.generator.common;

import java.util.Objects;

public class ArrayHelper {

    public static <T> boolean isNullOrEmpty(T[] elements) {
        return elements == null || elements.length == 0;
    }

    public static <T> boolean isNotNullOrEmpty(T[] elements) {
        return !isNullOrEmpty(elements);
    }

    public static <T> boolean contains(T[] elements, Object occurrence) {
        if (isNullOrEmpty(elements))
            return false;

        for (Object element : elements) {
            if (element == null || occurrence == null) {
                if (element == null && occurrence == null) return true;
                continue;
            }

            if (element.getClass().equals(occurrence.getClass()) && Objects.equals(element, occurrence))
                return true;
        }
        return false;
    }
}
