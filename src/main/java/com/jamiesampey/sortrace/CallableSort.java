package com.jamiesampey.sortrace;

import java.util.List;
import java.util.concurrent.Callable;

interface CallableSort<T extends Comparable> extends Callable<SortResult<T>> {

    List<T> sort(final List<T> list);

    String getName();

    List<T> getRawData();

    @Override
    default SortResult<T> call() {
        long start = System.currentTimeMillis();
        List<T> sortedData = sort(getRawData());
        return new SortResult<>(getName(), System.currentTimeMillis() - start, sortedData);
    }
}
