package algs4.Sorting;

public class Sorts {
    public static boolean less(Comparable<Object> v, Comparable<Object> w) {
        if (v == w)
            return false; // optimization when reference equals
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable<Object>[] arr, int i, int j) {
        Comparable<Object> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean isSorted(Comparable<Object>[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            if (less(arr[i], arr[i - 1]))
                return false;
        }
        return true;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}