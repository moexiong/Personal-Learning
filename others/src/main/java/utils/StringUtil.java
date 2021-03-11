package utils;

/**
 * @author: 詹世雄
 * @date: 2021/3/7 17:07
 * @description:
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

}
