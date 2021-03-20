package algorithms.normal;

/**
 * @author: 詹世雄
 * @date: 2021/3/20 19:52
 * @description: 151.翻转字符串里的单词 给定一个字符串，逐个翻转字符串中的每个单词。
 *
 * 说明：
 *
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 示例 1：
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 *
 * 示例 2：
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 示例 4：
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 *
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *
 * 提示
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *  
 * 进阶：
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 */
public class Problem151 {

    public static String reverseWords(String s) {
        StringBuilder builder = new StringBuilder();
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (32 == s.charAt(i) || 160 == s.charAt(i)) {
                if (j != 0) {
                    builder.append(s, i + 1, i + j + 1).append(" ");
                    j = 0;
                }
                continue;
            }
            j++;
        }
        if (j > 0) {
            builder.append(s, 0, j);
        } else {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String s1 = "the sky is blue";
        System.out.println("origin: " + s1 + " => " +  reverseWords(s1));
        String s2 = "  hello world!  ";
        System.out.println("origin: " + s2 + " => " +  reverseWords(s2));
        String s3 = "a good   example";
        System.out.println("origin: " + s3 + " => " +  reverseWords(s3));
        String s4 = "  Bob    Loves  Alice   ";
        System.out.println("origin: " + s4 + " => " +  reverseWords(s4));
        String s5 = "Alice does not even like bob";
        System.out.println("origin: " + s5 + " => " +  reverseWords(s5));
    }

}
