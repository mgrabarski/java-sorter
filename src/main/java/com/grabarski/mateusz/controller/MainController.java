package com.grabarski.mateusz.controller;

import com.grabarski.mateusz.sort.*;
import com.grabarski.mateusz.utils.SortUtils;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class MainController {

    public static final int START_NUMBER = 0;
    public static final int END_NUMBER = 30_000;
    public static final int STEP = 500;

    private List<Integer> numberOfElements;
    private List<Long> bubbleSortingTime;
    private List<Long> insertSortingTime;
    private List<Long> selectionSortingTime;
    private List<Long> heapSortingTime;
    private List<Long> mergeSortingTime;

    @FXML
    private LineChart lineChart;

    public void initialize() {
        initValues();
    }

    private void initValues() {
        numberOfElements = new ArrayList<>();
        bubbleSortingTime = new ArrayList<>();
        insertSortingTime = new ArrayList<>();
        selectionSortingTime = new ArrayList<>();
        heapSortingTime = new ArrayList<>();
        mergeSortingTime = new ArrayList<>();

        for (int i = START_NUMBER; i <= END_NUMBER; i += STEP) {
            System.out.println("Number of item: " + i);
            numberOfElements.add(i);
            bubbleSortingTime.add(SortUtils.getTimeOfSorting(i, END_NUMBER, new BubbleSorter()));
            insertSortingTime.add(SortUtils.getTimeOfSorting(i, END_NUMBER, new InsertionSorter()));
            selectionSortingTime.add(SortUtils.getTimeOfSorting(i, END_NUMBER, new SelectionSorter()));
            heapSortingTime.add(SortUtils.getTimeOfSorting(i, END_NUMBER, new HeapSorter()));
            mergeSortingTime.add(SortUtils.getTimeOfSorting(i, END_NUMBER, new MergeSorter()));
        }

        System.out.println("numberOfElements = " + numberOfElements.size());
        System.out.println("bubbleSortingTime = " + bubbleSortingTime.size());
        System.out.println("insertSortingTime = " + insertSortingTime.size());
        System.out.println("selectionSortingTime = " + selectionSortingTime.size());
        System.out.println("heapSortingTime = " + heapSortingTime.size());
        System.out.println("mergeSortingTime = " + mergeSortingTime.size());
    }
}