package com.jamiesampey.sortrace;

import java.util.List;

public class SortResult<T> {

    private final String sortName;
    private final long milliseconds;
    private final List<T> sortedData;

    public SortResult(String sortName, long milliseconds, List<T> sortedData) {
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
        final String time = milliseconds >= 1000 ?
            String.format("%.2f seconds", new Float(milliseconds) / 1000) :
            String.format("%d milliseconds", milliseconds);
        return String.format("%s sorted %d elements in %s", sortName, sortedData.size(), time);
    }
}
