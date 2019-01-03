package com.jamiesampey.sortrace.sorts;

import com.jamiesampey.sortrace.CallableSort;

import java.util.ArrayList;
import java.util.List;

public class MergeSort<T extends Comparable> implements CallableSort<T> {
    private final String name = "Merge Sort";
    private final List<T> rawData;

    public MergeSort(List<T> rawData) {
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
