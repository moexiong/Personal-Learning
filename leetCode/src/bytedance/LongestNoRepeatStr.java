package bytedance;

/**
 * @author: Shixiong Zhan
 * @date: 2021/2/27 16:38
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class LongestNoRepeatStr {

    public static int lengthOfLongestSubstring(String s) {
        return findLongest(s, 0, 0, new int[127]);
    }

    private static int findLongest(String s, int x, int y, int[] arr) {
        if (s == null || x == s.length()) {
            return x - y;
        }
        if (arr[s.charAt(x)] > 0) {
            int tmp = arr[s.charAt(x)];
            if (tmp >= y) {
                arr[s.charAt(x)] = 0;
                return Math.max((x - y), findLongest(s, x, tmp, arr));
            }
        }
        arr[s.charAt(x)] = x + 1;
        x++;
        return findLongest(s, x, y, arr);
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "abba";
        String s3 = "pwwkew";
        String s4 = "  ";
        String s5 = "a";
        String s6 = "dvdf";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
        System.out.println(lengthOfLongestSubstring(s3));
        System.out.println(lengthOfLongestSubstring(s4));
        System.out.println(lengthOfLongestSubstring(s5));
        System.out.println(lengthOfLongestSubstring(s6));
    }

}
