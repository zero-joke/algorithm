package algs4.Sorting;

public class InsertionSort {
    public void sort(Comparable<Object>[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            for (int j = i; j > 0 && Sorts.less(arr[j], arr[j - 1]); --j) {
                Sorts.exch(arr, j, j - 1);
            }
        }
    }

    // 希尔排序
    public void shellSort(Comparable<Object>[] arr) {
        int n = arr.length;
        int h = 1;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h > 0) {
            for (int i = h; i < arr.length; ++i) {
                for (int j = i; j >= h && Sorts.less(arr[j], arr[j - h]); j -= h) {
                    Sorts.exch(arr, j, j - h);
                }
            }
            h /= 3;
        }
        assert Sorts.isSorted(arr) : "failure!";
    }
}