package algs4.Sorting;

public class HeapSort {
    public void sort(Comparable[] arr) {
        Heap heap = new Heap(arr.length);
        for (Comparable<Object> v : arr) {
            heap.offer(v);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }

    public static void main(String[] args) {
        String[] arr = new String[] { "4", "6", "2", "5", "8", "1", "9" };
        new HeapSort().sort((Comparable[]) arr);
        Sorts.show(arr);
    }
}