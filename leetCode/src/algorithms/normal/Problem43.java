package algorithms.normal;

/**
 * @author: 詹世雄
 * @date: 2021/3/20 12:36
 * @description: 43. 字符串相乘 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 */
public class Problem43 {

    /**
     * 123... * 456... s1[i] * s2[j]
     * s3[i+j] = (s1[i] * s[j] + inc) / 10
     * s3[i+j+1] = (s1[i] * s[j] + inc) % 10
     * s1.l - i + s2.l - j = bit
     * bit -> s1.l + s2.l
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int sum = num1.length() + num2.length() - 2;
        int bit = 0;

        int inc = 0;
        int[] values = new int[sum + 3];
        while (bit <= sum) {
            int s = 0;
            for (int i = num1.length() - 1, j = sum - bit - i; i >= 0 && j < num2.length(); i--, j++) {
                if (j < 0) {
                    continue;
                }
                s += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
            s += inc;
            inc = s / 10;
            values[sum + 2 - bit] = s % 10;
            bit++;
        }
        if (inc > 0) {
            values[sum + 2 - bit] = inc;
        }
        StringBuilder builder = new StringBuilder();
        boolean firstZero = true;
        for (int value : values) {
            if (value == 0 && firstZero) {
                continue;
            }
            builder.append(value);
            firstZero = false;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        System.out.println(multiply(num1, num2));
        num1 = "9";
        num2 = "9";
        System.out.println(multiply(num1, num2));
        num1 = "912";
        num2 = "239";
        System.out.println(multiply(num1, num2));
        num1 = "911";
        num2 = "911";
        System.out.println(multiply(num1, num2));
        num1 = "105";
        num2 = "610";
        System.out.println(multiply(num1, num2));
    }

}
