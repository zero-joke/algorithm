package Exercises.array;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 递增子序列
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
示例:
输入: [4, 6, 7, 7]
输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]

说明:
给定数组的长度不会超过15。
数组中的整数范围是 [-100,100]。
给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class Subsequence {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        find(nums, new LinkedList<>(), 0);
        return res;
    }

    private void find(int[] nums, LinkedList<Integer> list, int lastIdx) {
        if(lastIdx == nums.length-1) {
            return;
        }
        if(list.isEmpty()) {
            for(int i=0; i<nums.length-1; i++) {
                list.addLast(nums[i]);
                find(nums, list, i);
                list.removeLast();
            }
        }
        int last = list.get(list.size()-1);
        for(int i=lastIdx+1; i<nums.length; i++) {
            if(last<=nums[i] && !isDup(nums, lastIdx, i)) {
                list.addLast(nums[i]);
                if(list.size() > 2) {
                    res.add(new ArrayList<>(list));
                }
                find(nums, list, i);
                list.removeLast();
            }
        }
    }

    private boolean isDup(int[] nums, int last, int pos) {
        for(int i = last+1; i < pos; i++) {
            if(nums[i] == nums[pos]) {
                return false;
            }
        }
        return true;
    }
}