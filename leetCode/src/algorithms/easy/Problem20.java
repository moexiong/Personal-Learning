package algorithms.easy;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 *
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 *
 * 输入: "{[]}"
 * 输出: true
 *
 */
public class Problem20 {

    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        // 空串符合要求
        if ("".equals(s)) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        try {
            for (char c : s.toCharArray()) {
                switch (c) {
                    case ')':
                        char temp = stack.pop();
                        if ('(' != temp) {
                            return false;
                        }
                        break;
                    case ']':
                        temp = stack.pop();
                        if ('[' != temp) {
                            return false;
                        }
                        break;
                    case '}':
                        temp = stack.pop();
                        if ('{' != temp) {
                            return false;
                        }
                        break;
                    default:
                        stack.push(c);
                }
            }
        } catch (Exception e) {
            return false;
        }
        // 当栈空时，即为合法字符串
        return stack.empty();
    }

    /**
     * 最优速度解答
     * @param s
     * @return
     */
    public static boolean isValid_best(String s) {
        char [] crr = s.toCharArray();
        char [] st = new char[crr.length];
        int temp1 = 0;
        int temp2 = 0;
        int temp3 = 0;
        char temp = 65;
        int count = -1;
        boolean judge = true;
        for(int i = 0; i < crr.length;  i++) {
            if(count != -1) {
                temp = st[count];
            }
            switch(crr[i]) {
                case '(':
                    temp1++;
                    count++;
                    st[count] = '(';
                    continue;
                case '[':
                    temp2++;
                    count++;
                    st[count] = '[';
                    continue;
                case '{':
                    temp3++;
                    count++;
                    st[count] = '{';
                    continue;
                case ')':
                    temp1--;
                    if(temp1 < 0) {
                        judge = false;
                        return judge;
                    }
                    if(temp != '(') {
                        judge = false;
                        return judge;
                    }else {
                        count--;
                    }
                    continue;
                case ']':
                    temp2--;
                    if(temp2 < 0) {
                        judge = false;
                        return judge;
                    }
                    if(temp != '[') {
                        judge = false;
                        return judge;
                    }else {
                        count--;
                    }
                    continue;
                case '}':
                    temp3--;
                    if(temp3 < 0) {
                        judge = false;
                        return judge;
                    }
                    if(temp != '{') {
                        judge = false;
                        return judge;
                    }else {
                        count--;
                    }
                    continue;

            }

        }
        if(temp1 != 0 || temp2 != 0 || temp3 != 0) {
            return false;
        }
        return judge;

    }

    public static void main(String[] args) {
        String s = "";
        System.out.println(isValid(s));// true
        s = "()";
        System.out.println(isValid(s));// true
        s = "()[]{}";
        System.out.println(isValid(s));// true
        s = "([)]";
        System.out.println(isValid(s));// false
        s = "{[]}";
        System.out.println(isValid(s));// true
    }
}
