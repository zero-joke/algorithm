package Exercises.array;

/**
 * 数组反转
 */
public class ArrayReverse {
    // 反转整个数组
    public void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while(i < j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
            i++;
            j--;
        }
    }

    // k个一组反转数组，如果剩余少于k个，按原顺序输出
    public void reverse(int[] arr, int k) {
        int i = 0, j = i + k - 1;
        while(j < arr.length) {
            reverse(arr, i, j);
            i = j + 1;
            j = i + k - 1;
        }
    }

    private void reverse(int[] arr, int i, int j) {
        while(i < j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
            i++;
            j--;
        }
    }
}