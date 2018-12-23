package com.jamiesampey.sortrace;

import java.util.Arrays;
import java.util.List;

class SortResult<T> {

    private final String sortName;
    private final List<T> sortedData;

    SortResult(String sortName, List<T> sortedData) {
        this.sortName = sortName;
        this.sortedData = sortedData;
    }

    public String getSortName() {
        return sortName;
    }

    public List<T> getSortedData() {
        return sortedData;
    }

    @Override
    public String toString() {
        return String.format("%s returned %s", sortName, Arrays.toString(sortedData.toArray()));
    }
}
