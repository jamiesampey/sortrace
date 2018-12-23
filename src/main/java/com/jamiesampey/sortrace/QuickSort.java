package com.jamiesampey.sortrace;

import java.util.List;

public class QuickSort<T extends Comparable> implements CallableSort<T> {
    private static final String NAME = "Quick Sort";
    private final List<T> data;

    QuickSort(List<T> data) {
        this.data = data;
    }

    @Override
    public SortResult<T> call() {
        return new SortResult<>(NAME, sort(data));
    }

    @Override
    public List<T> sort(List<T> data) {
        return sort(data, 0, data.size() - 1);
    }

    private List<T> sort(List<T> list, int low, int high) {
        if (low < high) {
            int partitionIndex = partition(list, low, high);
            sort(list, low, partitionIndex - 1);
            sort(list, partitionIndex + 1, high);
        }

        return list;
    }

    /**
     * The partition method picks a pivot and modifies the list (in place) so that all
     * elements < pivot are at the beginning of the list, followed by the pivot,
     * followed by all elements > pivot.
     */
    private int partition(List<T> list, int low, int high) {
        T pivotValue = list.get(high); // use the high index as the pivot
        int lowerValuesIndex = low;

        for (int i = low; i <= high - 1; i++) {
            if (list.get(i).compareTo(pivotValue) < 0) {
                swapInPlace(list, lowerValuesIndex, i);
                lowerValuesIndex++;
            }
        }

        // place the pivot immediately after all elements that were found to be < pivot
        swapInPlace(list, lowerValuesIndex, high);

//        System.out.println(String.format("Placing pivot value %d at index %d", pivotValue, lowerValuesIndex));

        // return the new index of the now re-positioned pivot value
        return lowerValuesIndex;
    }

    private void swapInPlace(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
