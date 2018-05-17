package com.grabarski.mateusz.sort;

import com.grabarski.mateusz.interfaces.Sorter;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class MergeSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        sort(array, 0, array.length - 1);
    }

    @Override
    public String getAlgorithmName() {
        return "Merge sort";
    }

    private void sort(int[] array, int startIndex, int endIndex) {

        int size = endIndex - startIndex + 1;

        if (size == 2) {
            if (array[startIndex] > array[endIndex]) {
                swap(array, startIndex, endIndex);
            }
        } else if (size > 2) {
            int middleIndex = (startIndex + endIndex) / 2;

            sort(array, startIndex, middleIndex);
            sort(array, middleIndex + 1, endIndex);

            merge(array, startIndex, middleIndex, endIndex);
        }
    }

    private void merge(int[] array, int leftPartStartIndex, int leftPartEndIndex, int rightPartEndIndex) {
        int[] mergeResult = new int[rightPartEndIndex - leftPartStartIndex + 1];
        int leftPartIndex = leftPartStartIndex;
        int rightPartIndex = leftPartEndIndex + 1;
        int mergeResultIndex = 0;

        while (leftPartIndex <= leftPartEndIndex && rightPartIndex <= rightPartEndIndex) {
            if (array[leftPartIndex] <= array[rightPartIndex]) {
                mergeResult[mergeResultIndex] = array[leftPartIndex];
                leftPartIndex++;
            } else {
                mergeResult[mergeResultIndex] = array[rightPartIndex];
                rightPartIndex++;
            }
            mergeResultIndex++;
        }

        for (; leftPartIndex <= leftPartEndIndex; leftPartIndex++) {
            mergeResult[mergeResultIndex] = array[leftPartIndex];
            mergeResultIndex++;
        }

        for (; rightPartIndex <= rightPartEndIndex; rightPartIndex++) {
            mergeResult[mergeResultIndex] = array[rightPartIndex];
            mergeResultIndex++;
        }

        for (mergeResultIndex = 0; mergeResultIndex < mergeResult.length; mergeResultIndex++) {
            array[leftPartStartIndex + mergeResultIndex] = mergeResult[mergeResultIndex];
        }
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}