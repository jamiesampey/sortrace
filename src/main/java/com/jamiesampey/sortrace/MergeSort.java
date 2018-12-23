package com.jamiesampey.sortrace;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable> implements CallableSort<T> {
    private static final String NAME = "Merge Sort";
    private final List<T> data;

    MergeSort(List<T> data) {
        this.data = data;
    }

    @Override
    public SortResult<T> call() {
        long start = System.currentTimeMillis();
        List<T> sortedData = sort(data);
        return new SortResult<>(NAME, System.currentTimeMillis() - start, sortedData);
    }

    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) return list;

        int middle = Math.floorDiv(list.size(), 2);
        return merge(
            sort(list.subList(0, middle)),
            sort(list.subList(middle, list.size()))
        );
    }

    private List<T> merge(List<T> list1, List<T> list2) {
        int i1 = 0;
        int i2 = 0;
        List<T> merged = new ArrayList<>();

        while (i1 < list1.size() || i2 < list2.size()) {
            if (i1 == list1.size()) {
                merged.add(list2.get(i2));
                i2++;
            } else if (i2 == list2.size()) {
                merged.add(list1.get(i1));
                i1++;
            } else if (list1.get(i1).compareTo(list2.get(i2)) < 0) {
                merged.add(list1.get(i1));
                i1++;
            } else {
                merged.add(list2.get(i2));
                i2++;
            }
        }

//        System.out.println(String.format("Merging %s and %s into %s",
//            Arrays.toString(list1.toArray()),
//            Arrays.toString(list2.toArray()),
//            Arrays.toString(merged.toArray()
//        )));

        return merged;
    }
}
