package com.jamiesampey.sortrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class SortRace {

    private final List<CallableSort> sorters;

    SortRace(List<CallableSort> sorters) {
        this.sorters = sorters;
    }

    void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(sorters.size());
        CompletionService<SortResult> completionService = new ExecutorCompletionService<>(threadPool);

        for (CallableSort sorter : sorters) {
            completionService.submit(sorter);
        }

        List<SortResult> sortResults = new ArrayList<>();

        try {
            while (sortResults.size() < sorters.size()) {
                Future<SortResult> sortResultFuture = completionService.take();
                sortResults.add(sortResultFuture.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nRACE RESULTS:");
        int i = 1;
        for (SortResult sortResult : sortResults) {
            System.out.println(String.format("%d) %s", i, sortResult));
            i++;
        }
    }

    public static void main(String[] args) {
        final List<Integer> data = Arrays.asList(4,5,1,45,6,32,21,9,2,21);

        final List<CallableSort> sorters = Arrays.asList(
                new MergeSort<>(new ArrayList<>(data)),
                new QuickSort<>(new ArrayList<>(data))
        );

        SortRace sortRace = new SortRace(sorters);
        sortRace.run();

        System.exit(0);
    }
}
