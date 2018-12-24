package com.jamiesampey.sortrace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SortRace {

    private final CallableSort[] sorters;

    SortRace(CallableSort ...sorters) {
        this.sorters = sorters;
    }

    public static void main(String[] args) {
        final List<Integer> data = generateList(50000);

        SortRace sortRace = new SortRace(
            new InsertionSort<>(new ArrayList<>(data)),
            new BubbleSort<>(new ArrayList<>(data)),
            new MergeSort<>(new ArrayList<>(data)),
            new QuickSort<>(new ArrayList<>(data))
        );

        System.out.println(String.format("\n====================================="));
        System.out.println(String.format("Running race to sort %d elements", data.size()));
        sortRace.run();

        System.exit(0);
    }

    private static List<Integer> generateList(int length) {
        List<Integer> list = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            list.add(new Double(Math.random() * 1000).intValue());
        }

        return list;
    }

    void run() {
        ExecutorService threadPool = Executors.newFixedThreadPool(sorters.length);
        CompletionService<SortResult> completionService = new ExecutorCompletionService<>(threadPool);

        for (CallableSort sorter : sorters) {
            completionService.submit(sorter);
        }

        List<SortResult> sortResults = new ArrayList<>();

        try {
            while (sortResults.size() < sorters.length) {
                Future<SortResult> sortResultFuture = completionService.take();
                sortResults.add(sortResultFuture.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nRACE RESULTS:");
        int i = 1;
        for (SortResult sortResult : sortResults) {
            if (isSorted(sortResult)) {
                System.out.println(String.format("%d) %s", i, sortResult));
            } else {
                System.out.println(String.format("%d) %s IS DISQUALIFIED!", i, sortResult.getSortName()));
            }

            i++;
        }
        System.out.println(String.format("=====================================\n"));
    }

    private boolean isSorted(SortResult sortResult) {
        List<Integer> sortedList = sortResult.getSortedData();
        for (int i = 1; i < sortedList.size(); i++) {
            if (sortedList.get(i) < sortedList.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
