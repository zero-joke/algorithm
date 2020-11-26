package Exercises.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个单词集合 （没有重复），找出其中所有的 单词方块 。
 * 
 * 一个单词序列形成了一个有效的单词方块的意思是指从第 k 行和第 k 列 (0 ≤ k < max(行数, 列数)) 来看都是相同的字符串。
 * 
 * 例如，单词序列 ["ball","area","lead","lady"] 形成了一个单词方块，因为每个单词从水平方向看和从竖直方向看都是相同的。
 * 
b a l l
a r e a
l e a d
l a d y
 */
public class WordSquares {
    private int wLen;
    private String[] words;
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> wordSquares(String[] words) {
        if(words == null || words.length < 1)
            return null;
        wLen = words[0].length();
        this.words = words;
        getSquare(new LinkedList<>());
        return result;
    }

    public void getSquare(LinkedList<String> list) {
        if (wLen == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        out: for (int i=0; i<words.length; ++i) {
            list.addLast(words[i]);
            int lmt = list.size() - 1; 
            String last = list.get(lmt);
            for (int j = 0; j < lmt && lmt < last.length(); ++j) {
                if (last.charAt(j) != list.get(j).charAt(lmt)) {
                    list.removeLast();
                    continue out;
                }
            }
            getSquare(list);
            list.removeLast();
        }
    }

    private void print() {
        for (List<String> list : result) {
            System.out.println(list.stream().collect(Collectors.joining("\n")));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        WordSquares ws = new WordSquares();
        ws.wordSquares(new String[] { "area","lead","wall","lady","ball" });
        ws.print();
    }
}
