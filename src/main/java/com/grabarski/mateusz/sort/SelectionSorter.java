package com.grabarski.mateusz.sort;

import com.grabarski.mateusz.interfaces.Sorter;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class SelectionSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {

            int indexOfSelectedNumber = i;

            for (int j = i; j < array.length; j++) {
                if (array[j] < array[indexOfSelectedNumber]) {
                    indexOfSelectedNumber = j;
                }
            }

            int leastNumber = array[indexOfSelectedNumber];
            array[indexOfSelectedNumber] = array[i];
            array[i] = leastNumber;
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Selection sort";
    }
}