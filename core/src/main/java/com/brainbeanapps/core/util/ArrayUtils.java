package com.brainbeanapps.core.util;

public final class ArrayUtils {

    public static final int NO_RESULT = -1;

    private ArrayUtils(){}

    /**
     * Method that returns last item index in array, return -1 if
     *
     * @param array array of elements of specified type
     * @param item item of specified type that should be in array
     * @param <T> type of array elements
     *
     * @return index of item
     */
    public static <T> int lastIndexOf(T[] array, T item) {

        if (item == null) {

            for (int i = array.length - 1; i >= 0; i--)
                if (array[i] == null) return i;

        } else {

            for (int i = array.length - 1; i >= 0; i--)
                if (array[i] == item) return i;
        }

        return NO_RESULT;
    }
}
