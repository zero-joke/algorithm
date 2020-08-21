public class kEmptySlots {

    public static int doLogic(int[] bulbs, int K) {
        if (bulbs == null || bulbs.length < 2)
            return -1;
        int[] arr = new int[2];
        arr[0] = Math.min(bulbs[0], bulbs[1]);
        arr[1] = Math.max(bulbs[0], bulbs[1]);
        if (arr[1] - arr[0] - 1 == K)
            return arr.length;
        for (int i = 2; i < bulbs.length; ++i) {
            int k = bulbs[i];
            int index = findInsertIndex(arr, k, 0, arr.length); // 找到插入位置
            arr = insert(arr, k);
            if (index - 1 >= 0 && arr[index] - arr[index - 1] - 1 == K)
                return arr.length;
            if (index + 1 < arr.length && arr[index + 1] - arr[index] - 1 == K)
                return arr.length;
        }
        return -1;
    }

    // 往有序数组合适的位置插入一个新元素，返回新的有序数组
    public static int[] insert(int[] sortArr, int k) {
        int[] newArr = new int[sortArr.length + 1];
        int index = findInsertIndex(sortArr, k, 0, sortArr.length);
        newArr[index] = k;
        if (index > 0) {
            System.arraycopy(sortArr, 0, newArr, 0, index);
        }
        if (index < sortArr.length) {
            System.arraycopy(sortArr, index, newArr, index + 1, sortArr.length - index);
        }
        return newArr;
    }

    public static int findInsertIndex(int[] sortArr, int k, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            if (sortArr[mid] == k) {
                return mid;
            } else if (sortArr[mid] < k) {
                if (mid + 1 < sortArr.length) {
                    if (sortArr[mid + 1] >= k) {
                        return mid + 1;
                    } else {
                        return findInsertIndex(sortArr, k, mid + 1, right);
                    }
                } else {
                    return mid + 1;
                }
            } else {
                if (mid - 1 >= 0) {
                    if (sortArr[mid - 1] <= k) {
                        return mid;
                    } else {
                        return findInsertIndex(sortArr, k, left, mid - 1);
                    }
                } else {
                    return 0;
                }
            }
        } else {
            return left;
        }
    }

    public static void main(String args[]) {
        int[] bulbs = new int[] { 9, 1, 4, 2, 8, 7, 5, 3, 6, 10 };
        System.out.println(doLogic(bulbs, 3));
    }
}