package com.grabarski.mateusz.sort;

import com.grabarski.mateusz.interfaces.Sorter;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class HeapSorter implements Sorter {

    @Override
    public void sort(int[] array) {
        buildHeap(array);
        sortUsingHeap(array);
    }

    @Override
    public String getAlgorithmName() {
        return "Heap sort";
    }

    private void buildHeap(int[] array) {
        for (int i = 1; i < array.length; i++) {
            fixHeapUpwards(array, i);
        }
    }

    private void fixHeapUpwards(int[] array, int index) {

        if (index > 0) {
            int parentIndex = (index - 1) / 2;

            if (array[parentIndex] < array[index]) {
                swap(array, index, parentIndex);
                fixHeapUpwards(array, parentIndex);
            }
        }
    }

    private void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private void sortUsingHeap(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            swap(array, 0, array.length - 1 - i);
            fixHeapDownwards(array, 0, array.length - 1 - i);
        }
    }

    private void fixHeapDownwards(int[] array, int index, int headSize) {
        Integer greaterChildIndex = getGreaterChildIndex(array, index, headSize);

        if (greaterChildIndex != null && array[greaterChildIndex] > array[index]) {
            swap(array, index, greaterChildIndex);
            fixHeapDownwards(array, greaterChildIndex, headSize);
        }
    }

    private Integer getGreaterChildIndex(int[] array, int index, int headSize) {
        int leftChildIndex = 2 * index + 1;

        if (leftChildIndex == headSize - 1) {
            return leftChildIndex;
        } else if (leftChildIndex < headSize - 1) {
            int rightChildIndex = leftChildIndex + 1;
            if (array[leftChildIndex] > array[rightChildIndex]) {
                return leftChildIndex;
            } else {
                return rightChildIndex;
            }
        } else {
            return null;
        }
    }
}