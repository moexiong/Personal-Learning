package algorithms.easy;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 */
public class Problem9 {

    // 普通：转换为字符串
    private static boolean isPalindrome_str(int x) {
        String str = "" + x;
        for (int i = 0, j = str.length() - 1; i <= j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // 进阶：不转换为字符串
    private static boolean isPalindrome_int(int x) {
        // 小于0的肯定不是回文数
        if (x < 0) {
            return false;
        } else if (x < 10) {
            // 0 - 10之间的肯定是回文数
            return true;
        } else if (x % 10 == 0) {
            // 以0结尾肯定不是回文数
            return false;
        }
        int y = 0;
        while (x > y) {
            y = y * 10 + x % 10;
            if (x == y) {
                return true;
            }
            x = x / 10;
            if (x == y) {
                return true;
            }
        }
        return false;
    }

    /**
     * 最优速度解答
     * @param x
     * @return
     */
    private static boolean isPalindrome_best(int x) {
        if(x < 0) return false;
        int sub = 0;
        int temp = 0;
        int n = x;
        while (n > 0) {
            sub = n % 10;
            temp = temp * 10 + sub;
            n /= 10;
        }
        return temp == x;
    }

    public static void main(String[] args) {
        int x = 101101;
        System.out.println(x + "(str)：" + isPalindrome_str(x));
        System.out.println(x + "(int)：" + isPalindrome_int(x));
    }
}
