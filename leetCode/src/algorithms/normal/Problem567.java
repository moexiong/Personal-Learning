package algorithms.normal;

/**
 * @author: 詹世雄
 * @date: 2021/3/14 17:48
 * @description: 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 *
 * 示例 1：
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 *
 * 示例 2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 *
 * 提示：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10000] 之间
 */
public class Problem567 {

    /**
     * 思路：匹配s1在s2上是否存在，不要求顺序，最简单的思路就是滑动窗口，当窗口内的字符串种类和数量都对上时即可退出。
     * 若存在s2[i]属于s1内，则要求s2[i] - s2[i + s1.length]之间都符合。
     * i指针是左边界，用来恢复。j指针是右边界，用来匹配。(种类匹配的变化与值有关)
     * 窗口移动一步时会出现2种情况：1.新加入的字符不属于s1，窗口可以整个跳过新字符的位置，重新计数。2.新加入的字符属于s1，如果数量多于s1，需要恢复一个，需要考虑释放的不是重复值时，种类变化。
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] s1Bak = new int[26];
        int[] s1Arr = new int[26];
        boolean[] s1Exist = new boolean[26];
        int type = 0;
        for (char c : s1.toCharArray()) {
            if (s1Arr[c - 'a'] == 0) {
                s1Exist[c - 'a'] = true;
                type++;
            }
            s1Bak[c - 'a']++;
            s1Arr[c - 'a']++;
        }
        int tBak = type;
        char[] s2Arr = s2.toCharArray();
        int i = 0, j = 0;
        while (j < s2Arr.length) {
            if (s1Exist[s2Arr[j] - 'a']) {
                s1Arr[s2Arr[j] - 'a']--;
                if (s1Arr[s2Arr[j] - 'a'] == 0 && --type == 0) {
                    return true;
                } else if (s1Arr[s2Arr[j] - 'a'] < 0) {
                    s1Arr[s2Arr[i] - 'a']++;
                    if (s1Arr[s2Arr[i] - 'a'] == 1) {
                        type++;
                    }
                    i++;
                }
                j++;
            } else {
                i = ++j;
                type = tBak;
                System.arraycopy(s1Bak, 0, s1Arr, 0, s1Bak.length);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));// t
        System.out.println(checkInclusion("abc", "aabaccab"));// t
        System.out.println(checkInclusion("ooo", "eidbaooo"));// t
        System.out.println(checkInclusion("abo", "eidobbaooo"));// t
        System.out.println(checkInclusion("bdo", "eidbaooo"));// f
    }
}
