#include <string>
#include <vector>
#include <algorithm>

using namespace std;

// 最长回文子串
// 实现技巧：回文串有两种 'bab', 'baab'，通过往字符串两边和每个字符中间添加特殊字符是其统一成一种形式。然后遍历每一个字符作为中心点，向两边扩散计算回文长度；
// 提升-动态规划（Manacher算法）：基于已计算的回文串的臂长，优化新回文串的扩散起点。
class Solution
{
public:
    // 回文扩散，并返回臂长
    int expand(const string &s, int left, int right)
    {
        while (left >= 0 && right < s.size() && s[left] == s[right])
        {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

    // Manacher 算法
    string longestPalindrome(string s)
    {
        int start = 0, end = -1;
        string t = "#";
        for (char c : s)
        {
            t += c;
            t += '#';
        }
        t += '#';
        s = t;

        vector<int> arm_len;
        int right = -1, j = -1;
        for (int i = 0; i < s.size(); ++i)
        {
            int cur_arm_len;
            if (right >= i)
            {
                int i_sym = j * 2 - i;  // i 关于 j 的对称点
                int min_arm_len = min(arm_len[i_sym], right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            }
            else
            {
                cur_arm_len = expand(s, i, i);
            }

            arm_len.push_back(cur_arm_len);
            if (i + cur_arm_len > right)
            {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start)  // 更新最大回文长度
            {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        string ans;
        for (int i = start; i <= end; ++i)
        {
            if (s[i] != '#')
            {
                ans += s[i];
            }
        }
        return ans;
    }
};