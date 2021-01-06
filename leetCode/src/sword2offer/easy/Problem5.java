package sword2offer.easy;

/**
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 * 限制：
 * 0 <= s 的长度 <= 10000
 */
public class Problem5 {

    /**
     * 个人思路：
     * emm，遍历一遍应该少不了的，简单点
     * @param s
     * @return
     */
    public static String replaceSpace(String s) {
        char[] chars = new char[s.length() * 3];
        for (int i = 0, j = i; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                chars[j++] = '%';
                chars[j++] = '2';
                chars[j++] = '0';
            } else {
                chars[j++] = s.charAt(i);
            }
        }
        return String.valueOf(chars).trim();
    }

    public static void main(String[] args) {
        String s = "We are happy.";
        System.out.println(replaceSpace(s));
    }

}
