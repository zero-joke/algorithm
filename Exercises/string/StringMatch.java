package Exercises.string;

/**
 * 重复叠加字符串匹配
给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，如果不存在则返回 -1。

举个例子，A = "abcd"，B = "cdabcdab"。
答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。

注意:  A 与 B 字符串的长度在1和10000区间范围内。
 */
public class StringMatch {
    // 假设 q 是 len(B)<=len(A*q) 的最小数。我们只需要检查 B 是 A*q 的子串还是 A*(q+1) 的子串。
    // 如果我们尝试 k<q，那么 B 的长度大于 A*k，因此不能是子字符串。当 k=q+1 时，A*k 已经足够大，
    // 可以尝试 B 的所有位置，即 A[i:i+len(B)] == B，i = 0, 1, ..., len(A) - 1。
    public int repeatedStringMatch(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int q = 1;
        while(lenA*q < lenB) {
            q++;
        }
        String qA = "";
        for(int i=0; i<q;i++) {
            qA+=A;
        }
        if(qA.contains(B)) {
            return q;
        } else if((qA+A).contains(B)) {
            return q+1;
        }
        return -1;
    }

    private int myRepeatedStringMatch(String A, String B) {
        if(A.contains(B))
            return 1;
        int lenA = A.length();
        int lenB = B.length();
        if(B.contains(A)) {
            int count = 1;
            int i = B.indexOf(A);
            int j = i + lenA;
            while(j < lenB && j+lenA <= lenB && B.substring(j,j+lenA).equals(A)) {
                count++;
                j = j+lenA;
            }
            if(j<lenB) {
                if(lenB-j > lenA)
                    return -1;
                if(B.substring(j,lenB).equals(A.substring(0, lenB-j))
                    && B.substring(0,i).equals(A.substring(lenA-i,lenA)))
                    count+=2;
                else
                    return -1;
            }
            return count;
        } else {
            int i = 1;
            while(i<lenB) {
                if(i>lenA)
                    return -1;
                if(lenB-i>lenA)
                    continue;
                if(B.substring(0,i).equals(A.substring(lenA-i,lenA))
                    && B.substring(i,lenB).equals(A.substring(0,lenB-i)))
                    return 2;
            }
            return -1;
        }
    }
}