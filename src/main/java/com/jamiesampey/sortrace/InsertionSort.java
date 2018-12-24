package com.jamiesampey.sortrace;

import java.util.List;

public class InsertionSort<T extends Comparable> implements CallableSort<T> {
    private final String name = "Insertion Sort";

    private final List<T> rawData;

    InsertionSort(List<T> rawData) {
        this.rawData = rawData;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<T> getRawData() {
        return rawData;
    }

    @Override
    public List<T> sort(final List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            T value = list.get(i); // insert value down into the sorted (left) portion of the list

            // 'bubble' value down (via index j) through the sorted portion of the list, which are indexes 0-->i
            for (int j = i - 1; j >= 0; j--) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    swapInPlace(list, j, j + 1);
                }
            }
        }

        return list;
    }

    private void swapInPlace(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
