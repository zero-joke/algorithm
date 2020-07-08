package Exercises.armOffer;

/**
 * 替换空格 题目描述 将一个字符串中的空格替换成 "%20"。
 * 
 * Input: "A B"
 * 
 * Output: "A%20B"
 */
public class t4_string_replaceBlank {
    public String replace(StringBuilder str) {
        int p1 = str.length()-1;
        for (int i = 0; i <= p1; i++)
            if (str.charAt(i) == ' ')
                str.append("   ");
        int p2 = str.length()-1;
        while(p1 >= 0 && p2 > p1) {
            char c = str.charAt(p1--);
            if(c != ' ')
                str.setCharAt(p2--, c);
            else {
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            }
        }
        return str.toString();
    }
}