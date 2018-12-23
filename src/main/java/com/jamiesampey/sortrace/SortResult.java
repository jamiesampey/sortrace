package com.jamiesampey.sortrace;

import java.util.List;

class SortResult<T> {

    private final String sortName;
    private final long milliseconds;
    private final List<T> sortedData;

    SortResult(String sortName, long milliseconds, List<T> sortedData) {
        this.sortName = sortName;
        this.milliseconds = milliseconds;
        this.sortedData = sortedData;
    }

    public String getSortName() {
        return sortName;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public List<T> getSortedData() {
        return sortedData;
    }

    @Override
    public String toString() {
        return String.format("%s ran in %d milliseconds", sortName, milliseconds);
    }
}
