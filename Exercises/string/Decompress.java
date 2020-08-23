package Exercises.string;

import java.util.Stack;

// or example, 2[3[a]b] decompresses into aaabaaab
public class Decompress {
    public String doDecompress(String string) {
        String res = "";
        String temp = "";
        Stack<String> itemStack = new Stack<>();
        Stack<Integer> dupStack = new Stack<>();
        for(int i=0; i<string.length();) {
            char c = string.charAt(i);
            if(Character.isDigit(c) && i+1<string.length() && string.charAt(i+1) == '[') {
                if(!itemStack.empty()) {
                    itemStack.push(itemStack.pop()+temp);
                } else {
                    res += temp;
                }
                temp = "";
                dupStack.push(c - 48);
                itemStack.push("");
                i += 2;
            } else if(c == ']') {
                Integer dup = dupStack.pop();
                temp = itemStack.pop() + temp;
                temp = temp.repeat(dup);
                if(!dupStack.empty()) {
                    itemStack.push(itemStack.pop()+temp);
                } else {
                    res += temp;
                }
                temp = "";
                ++i;
            } else {
                temp += c;
                ++i;
            }
        }
        if(temp.length() > 0) {
            res += temp;
        }
        return res;
    }

    public static void main(String[] args) {
        Decompress decompress = new Decompress();
        System.out.println(decompress.doDecompress("10[a]"));
    }
}