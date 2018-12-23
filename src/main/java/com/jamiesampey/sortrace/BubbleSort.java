package com.jamiesampey.sortrace;

import java.util.List;

public class BubbleSort<T extends Comparable> implements CallableSort<T> {
    private final String name = "Bubble Sort";

    private final List<T> rawData;

    BubbleSort(List<T> rawData) {
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
            for (int j = 0; j < list.size() - i - 1; j++) {
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
