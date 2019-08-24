package algorithms.easy;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 */
public class Problem14 {

    public static String longestCommonPrefix(String[] strs) {
        String sameStr = "";
        // 没有字符串就没有公共前缀
        if (strs == null || strs.length == 0) {
            return sameStr;
        }
        // 初始化，默认公共前缀第一个
        sameStr = strs[0];
        for (String str : strs) {
            int sub = sameStr.length() > str.length() ? str.length() : sameStr.length();
            for (int i = 0; i < str.length() && i < sameStr.length(); i++) {
                if (sameStr.charAt(i) != str.charAt(i)) {
                    sub = i;
                    break;
                }
            }
            if (sub < sameStr.length()) {
                sameStr = sameStr.substring(0, sub);
            }
        }
        return sameStr;
    }

    /**
     * 最优速度解答
     * @param strs
     * @return
     */
    public String longestCommonPrefix_best(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
        String[] strs2 = new String[]{"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs2));
    }
}
