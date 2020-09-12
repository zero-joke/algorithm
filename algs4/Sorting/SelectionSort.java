package algs4.Sorting;

/**
 * 选择排序
 */
public class SelectionSort {
    public void sort(Comparable<Object>[] arr) {
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (Sorts.less(arr[j], arr[i])) {
                    Sorts.exch(arr, i, j);
                }
            }
        }
    }
}