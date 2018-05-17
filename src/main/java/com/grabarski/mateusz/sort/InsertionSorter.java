package com.grabarski.mateusz.sort;

import com.grabarski.mateusz.interfaces.Sorter;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class InsertionSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Insertion sort";
    }
}