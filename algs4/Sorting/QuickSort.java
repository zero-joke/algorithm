package algs4.Sorting;

public class QuickSort {

    // 普通快排
    public void sort(Comparable<Object>[] arr, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = partital(arr, lo, hi);
        sort(arr, lo, mid - 1);
        sort(arr, mid + 1, hi);
    }

    private int partital(Comparable<Object>[] arr, int lo, int hi) {
        Comparable<Object> v = arr[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (Sorts.less(arr[++i], v))
                if (i == hi)
                    break;
            while (Sorts.less(v, arr[--j]))
                if (j == lo)
                    break;
            if (i >= j)
                break;
            Sorts.exch(arr, i, j);
        }
        Sorts.exch(arr, lo, j);
        return j;
    }

    // 三分快排
    public void sort3way(Comparable<Object>[] arr, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable<Object> v = arr[lo];
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0)
                Sorts.exch(arr, lt++, i++);
            else if (cmp > 0)
                Sorts.exch(arr, i, gt--);
            else
                i++;
        }
        sort3way(arr, lo, lt - 1);
        sort3way(arr, gt + 1, hi);
    }
}