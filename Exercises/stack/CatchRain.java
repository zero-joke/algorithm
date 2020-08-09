package Exercises.stack;

import java.util.Stack;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 示例: 输入:
 * [0,1,0,2,1,0,1,3,2,1,2,1] 输出: 6 LeetCode:
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 
 * 思路：每个柱子的纵向列上能接住的水容量=该柱子分别两边最高柱子中的较小值 - 当前柱子的高度
 */
public class CatchRain {


    /**
        先计算每根柱子两边的最高柱子，每个柱子的纵向列上能接住的水容量=该柱子分别两边最高柱子中的较小值 - 当前柱子的高度
     */
    public int trap_dymc(int heigth[]) {
        int anz = 0;
        int len = heigth.length;
        int left_max[] = new int[len];
        int right_max[] = new int[len];
        left_max[0] = heigth[0];
        right_max[len - 1] = heigth[len - 1];
        for (int i = 1; i < len; i++)
            left_max[i] = Math.max(left_max[i - 1], heigth[i]);
        for (int j = len - 2; j >= 0; j--)
            right_max[j] = Math.max(right_max[j + 1], heigth[j]);
        for (int k = 0; k < len; k++) {
            anz += Math.min(left_max[k], right_max[k]) - heigth[k];
        }
        return anz;
    }

    /**
        利用栈，依次遍历只把递减的柱子放入栈中，如果遇到柱子比之前的高，则从栈中弹出之前的柱子，计算储水量
    */
    public int trap_byStack(int heigth[]) {
        int anz = 0;
        int current = 0;
        Stack<Integer> stack = new Stack<>();
        while (current < heigth.length) {
            while (!stack.empty() && heigth[current] > heigth[stack.peek()]) {
                int lastIdx = stack.pop();
                if (stack.empty())
                    break;
                int distance = current - stack.peek() - 1;
                int high = Math.min(heigth[current], heigth[stack.peek()]) - heigth[lastIdx];
                anz += (distance * high);
            }
            stack.push(current++);
        }
        return anz;
    }
}