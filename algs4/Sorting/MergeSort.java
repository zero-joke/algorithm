package algs4.Sorting;

/**
 * 归并排序
 */
public class MergeSort {

    // 原地归并
    private void merge(int[] arr, int sta_1, int end_1, int sta_2, int end_2) {
        int i = sta_1;
        int j = sta_2;
        while (i < end_1 && j < end_2) {
            if (arr[i] <= arr[j]) {
                ++i;
            } else {
                int temp = arr[j];
                System.arraycopy(arr, i, arr, i + 1, end_1 - i);
                arr[i] = temp;
                ++i;
                ++end_1;
                ++j;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] { 1, 2, 5, 7, 9, 2, 4, 6, 8, 10 };
        new MergeSort().merge(arr, 0, 5, 5, 10);
        int tmep = arr.length;
    }
}
