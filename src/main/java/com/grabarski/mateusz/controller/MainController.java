package com.grabarski.mateusz.controller;

import com.grabarski.mateusz.interfaces.Sorter;
import com.grabarski.mateusz.sort.*;
import com.grabarski.mateusz.utils.SortUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mateusz Grabarski on 17.05.2018.
 */
public class MainController {

    public static final int START_NUMBER = 0;

    @FXML
    private LineChart<Number, Number> lineChart;

    @FXML
    private CheckBox bubbleSortCB;

    @FXML
    private CheckBox insertSortCB;

    @FXML
    private CheckBox selectionSortCB;

    @FXML
    private CheckBox heapSortCB;

    @FXML
    private CheckBox mergeSortCB;

    @FXML
    private Button refreshChart;

    @FXML
    private Slider numberOfItemsS;

    @FXML
    private Slider maxRandomNumberS;

    @FXML
    private Slider stepS;

    @FXML
    private ProgressIndicator progressIndicator;

    private List<Integer> numberOfElements;
    private List<Long> bubbleSortingTime;
    private List<Long> insertSortingTime;
    private List<Long> selectionSortingTime;
    private List<Long> heapSortingTime;
    private List<Long> mergeSortingTime;

    public void initialize() {
        lineChart.setTitle("Sorting differences");
        Platform.runLater(() -> progressIndicator.setVisible(false));

        refreshChart.setOnAction(event -> initValues());
    }

    private void refreshData() {
        lineChart.getData().remove(0, lineChart.getData().size());

        addSelectedSeries(bubbleSortCB, new BubbleSorter(), bubbleSortingTime);
        addSelectedSeries(insertSortCB, new InsertionSorter(), insertSortingTime);
        addSelectedSeries(selectionSortCB, new SelectionSorter(), selectionSortingTime);
        addSelectedSeries(heapSortCB, new HeapSorter(), heapSortingTime);
        addSelectedSeries(mergeSortCB, new MergeSorter(), mergeSortingTime);

        Platform.runLater(() -> progressIndicator.setVisible(false));
    }

    private void addSelectedSeries(CheckBox checkBox, Sorter sorter, List<Long> times) {
        if (checkBox.isSelected()) {
            lineChart.getData().add(getSeries(sorter, times));
        }
    }

    private void initValues() {
        Platform.runLater(() -> progressIndicator.setVisible(true));

        new Thread(() -> {
            numberOfElements = new ArrayList<>();
            bubbleSortingTime = new ArrayList<>();
            insertSortingTime = new ArrayList<>();
            selectionSortingTime = new ArrayList<>();
            heapSortingTime = new ArrayList<>();
            mergeSortingTime = new ArrayList<>();

            for (int i = START_NUMBER; i <= numberOfItemsS.getValue(); i += stepS.getValue()) {
                System.out.println("Number of item: " + i);
                numberOfElements.add(i);
                bubbleSortingTime.add(SortUtils.getTimeOfSorting(i, (int) maxRandomNumberS.getValue(), new BubbleSorter()));
                insertSortingTime.add(SortUtils.getTimeOfSorting(i, (int) maxRandomNumberS.getValue(), new InsertionSorter()));
                selectionSortingTime.add(SortUtils.getTimeOfSorting(i, (int) maxRandomNumberS.getValue(), new SelectionSorter()));
                heapSortingTime.add(SortUtils.getTimeOfSorting(i, (int) maxRandomNumberS.getValue(), new HeapSorter()));
                mergeSortingTime.add(SortUtils.getTimeOfSorting(i, (int) maxRandomNumberS.getValue(), new MergeSorter()));
            }

            Platform.runLater(() -> refreshData());
        }).start();
    }

    private XYChart.Series<Number, Number> getSeries(Sorter sorter, List<Long> times) {
        XYChart.Series series = new XYChart.Series();
        series.setName(sorter.getAlgorithmName());

        for (int i = 0; i < numberOfElements.size(); i++) {
            series.getData().add(new XYChart.Data(numberOfElements.get(i), times.get(i)));
        }

        return series;
    }
}