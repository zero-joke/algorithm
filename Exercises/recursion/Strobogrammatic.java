package Exercises.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 11 , 69 , 88 , 96
public class Strobogrammatic {
    private int[][] arr = new int[][] { { 0, 0 }, { 1, 1 }, { 6, 9 }, { 8, 8 }, { 9, 6 } };
    private List<String> result = new ArrayList<>();

    public List<String> allStrobogrammatic(int n) {
        if (n < 1)
            return new ArrayList<>();
        if (n % 2 == 1)
            result.addAll(Arrays.asList("0", "1", "8"));
        int i = n / 2 - 1;
        while (i >= 0) {
            List<String> newResult = new ArrayList<>();
            for (int[] item : arr) {
                if (i == 0 && item[0] == 0)
                    continue;
                if (result.isEmpty()) {
                    newResult.add(item[0] + "" + item[1]);
                } else {
                    for (String str : result) {
                        newResult.add(item[0] + str + item[1]);
                    }
                }
            }
            result = newResult;
            --i;
        }
        return result;
    }

    public static void main(String[] args) {
        Strobogrammatic Main = new Strobogrammatic();
        List<String> result = Main.allStrobogrammatic(5);
        for (String str : result) {
            System.out.println(str);
        }
    }
}
