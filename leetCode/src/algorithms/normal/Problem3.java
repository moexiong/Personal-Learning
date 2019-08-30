package algorithms.normal;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class Problem3 {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int maxSub = 0;
        int index = 0;
        while (right < s.length() - 1) {
            // 移动右指针
            right++;
            // 左右指针之间存在重复
            index = s.indexOf(s.charAt(right), left);
            if (index >= left && index < right) {
                if ((right - left) > maxSub) {
                    maxSub = right - left;
                }
                // 移动左指针到重复字符后
                left = index + 1;
            }
        }
        if (right - left + 1 > maxSub) {
            maxSub = right - left + 1;
        }
        return maxSub;
    }

    /**
     * 最优速度解答
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring_best(String s) {
        char[] charArr = s.toCharArray();
        if (charArr.length == 0) {
            return 0;
        }
        int maxLength = 0;
        int baseIndex = 0;
        int i;

        //i从第二个元素开始
        for (i = baseIndex + 1; i < charArr.length; i++) {

            //j从base开始，从前往后找，与第i个相同的元素，并且计算长度
            for (int j = baseIndex; j < i; j++) {

                //如果找到相同元素，base直接跳到当前元素下一个
                if (charArr[j] == charArr[i]) {
                    maxLength = (i - baseIndex) > maxLength ? (i - baseIndex) : maxLength;
                    baseIndex = j + 1;
                    break;
                }
            }
        }

        //全部遍历后还要再计算一次，如果直到最后一位还没有重复元素的情况
        maxLength = (i - baseIndex) > maxLength ? (i - baseIndex) : maxLength;
        return maxLength;
    }

    public static void main(String[] args) {
        String str = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(str));// 3
        str = "b";
        System.out.println(lengthOfLongestSubstring(str));// 1
        str = "bc";
        System.out.println(lengthOfLongestSubstring(str));// 2
        str = "bbc";
        System.out.println(lengthOfLongestSubstring(str));// 2
        str = "bcc";
        System.out.println(lengthOfLongestSubstring(str));// 2
        str = "pwwew";
        System.out.println(lengthOfLongestSubstring(str));// 2
        str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));// 3
    }
}
