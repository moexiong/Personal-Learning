package algorithms.easy;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例:
 * 输入: 123
 * 输出: 321
 * 输入: -123
 * 输出: -321
 * 输入: 120
 * 输出: 21
 */
public class Problem7 {

    private static int reverse(int x) {
        boolean flag = true;
        if (x < 0) {
            flag = false;
        }
        StringBuilder sb = new StringBuilder();
        String temp = (Math.abs(x) + "");
        for (int i = temp.length() - 1; i >= 0; i--) {
            sb.append(temp.charAt(i));
        }
        int number = 0;
        try {
            number = Integer.valueOf(sb.toString());
        } catch (NumberFormatException e) {
            return number;
        }
        if (flag) {
            return number;
        }
        return -number;
    }

    /**
     * 最优速度解答
     * @param x
     * @return
     */
    private static int reverse_best(int x) {
        long temp = 0;
        while (x != 0) {
            //取余数
            int pop = x % 10;
            //叠加
            temp = temp * 10 + pop;
            if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
                return 0;
            }
            //取商
            x = x / 10;
        }
        return (int)temp;
    }

    public static void main(String[] args) {
        System.out.println(reverse(3046));
        System.out.println(reverse(1060));
        System.out.println(reverse(-2135));
        System.out.println(reverse(-1200));
        System.out.println(reverse(1135434452));
    }
}
