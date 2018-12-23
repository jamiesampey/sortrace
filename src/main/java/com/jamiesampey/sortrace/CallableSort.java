package com.jamiesampey.sortrace;

import java.util.List;
import java.util.concurrent.Callable;

interface CallableSort<T extends Comparable> extends Callable<SortResult<T>> {
    List<T> sort(List<T> list);
}
