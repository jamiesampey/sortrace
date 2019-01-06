SortRace is a small application to run a handful of popular sorting algorithms in parallel and see which ones complete fastest

The raw data for sorting can be any type implementing Comparable and the unsorted data set can be of any size (specified as a command line arg). Because many of the sorting algorithms sort in place, a separate copy of the unsorted list is given to each sorting thread to aviod thread interference.

To run a sort race:

```$xslt
#> ./gradlew assemble
#>  ./gradlew run --args=50000

> Task :run

=====================================
Running race to sort 50000 elements

RACE RESULTS:
1) Heap Sort sorted 50000 elements in 47 milliseconds
2) Quick Sort sorted 50000 elements in 59 milliseconds
3) Merge Sort sorted 50000 elements in 145 milliseconds
4) Insertion Sort sorted 50000 elements in 6.26 seconds
5) Bubble Sort sorted 50000 elements in 10.78 seconds
=====================================

BUILD SUCCESSFUL in 11s
2 actionable tasks: 1 executed, 1 up-to-date

```