package com.grabarski.mateusz.sort;

import com.grabarski.mateusz.interfaces.Sorter;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class BubbleSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Bubble sort";
    }
}