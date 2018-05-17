package com.grabarski.mateusz.utils;

import com.grabarski.mateusz.interfaces.Sorter;

import java.util.Random;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class SortUtils {

    /**
     * Method return time of sorting
     *
     * @param numberOfItems number of items to sort
     * @param maxNumber     max number
     * @param sorter        {@link Sorter} sorting method
     * @return time of sorting
     */
    public static long getTimeOfSorting(int numberOfItems, int maxNumber, Sorter sorter) {
        int[] array = getArrayWithNumbers(numberOfItems, maxNumber);

        long timeBeforeStartSorting = System.currentTimeMillis();

        sorter.sort(array);

        return System.currentTimeMillis() - timeBeforeStartSorting;
    }

    private static int[] getArrayWithNumbers(int numberOfItems, int maxNumber) {
        Random random = new Random(maxNumber);
        int[] array = new int[numberOfItems];

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }
}