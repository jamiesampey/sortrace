package com.jamiesampey.sortrace.sorts;

import com.jamiesampey.sortrace.CallableSort;

import java.util.List;

public class HeapSort<T extends Comparable> implements CallableSort<T> {
    private final String name = "Heap Sort";

    private final List<T> rawData;

    public HeapSort(List<T> rawData) {
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
//        System.out.println(String.format("Unsorted list is %s", list));
        // build the initial Max Heap starting with the lowest parent index (n/2 - 1) in the tree and working upward.
        // all indexes in the list less than ((n/2) - 1) must also be parents
        for (int i = list.size() / 2 - 1; i >= 0; i--) {
//            System.out.println(String.format("Heapifying parent at %d", i));
            heapify(list, list.size(), i);
        }

        // The list is now a Max Heap
//        System.out.println(String.format("Heapified list is %s", list));

        // Sort the list be continually moving the root (max) element to the end of the list
        // and calling heapify on a new sublist[0, n-1] thus building the sorted list from the right to the left
        for (int n = list.size() - 1; n >= 0; n--) {
            swapInPlace(list, 0, n);
            heapify(list, n, 0);
        }

        return list;
    }

    /**
     * Heapify works on a SLICE [0 --> n] of the list to move a single element down the (Max Heap) tree
     * until it is < all of its parents.
     */
    private void heapify(List<T> list, int n, int parent) {
//        System.out.println(String.format("heapifying at %d", parent));

        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;

        // find the max of list[parent], list[leftChild], and list[rightChild]
        int maxElement = parent; // default the maxElement to the parent
        if (leftChild < n && list.get(leftChild).compareTo(list.get(maxElement)) > 0)
            maxElement = leftChild; // the left child is > the parent
        if (rightChild < n && list.get(rightChild).compareTo(list.get(maxElement)) > 0)
            maxElement = rightChild; // the right child is > the parent AND the left child

        if (maxElement != parent) {
            swapInPlace(list, parent, maxElement);
            // the old parent is now a child at the index stored in maxElement, continue moving it down the tree if needed
            heapify(list, n, maxElement);
        }
    }

    private void swapInPlace(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
