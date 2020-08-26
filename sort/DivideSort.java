package sort;

import java.util.Arrays;

/**
 * 分治排序
 */
public class DivideSort {
    public void sort(int[] nums) {
        if (nums.length > 1) {
            int mid = nums.length / 2;
            int[] nums1 = Arrays.copyOfRange(nums, 0, mid);
            int[] nums2 = Arrays.copyOfRange(nums, mid, nums.length);
            // 分而
            sort(nums1);
            sort(nums2);
            // 合之
            System.arraycopy(merge(nums1, nums2), 0, nums, 0, nums.length);
        }
    }

    public int[] merge(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] res = new int[len1 + len2];
        int i = 0, j = 0;
        for (; i < len1 && j < len2;) {
            if (nums1[i] <= nums2[j]) {
                res[i + j] = nums1[i];
                i++;
            } else {
                res[i + j] = nums2[j];
                j++;
            }
        }
        if (i < len1) {
            System.arraycopy(nums1, i, res, i + j, res.length - i - j);
        } else {
            System.arraycopy(nums2, j, res, i + j, res.length - i - j);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 3, 2, 5, 6, 9, 8, 1, 4, 7, 7, 2 };
        new DivideSort().sort(nums);
    }
}