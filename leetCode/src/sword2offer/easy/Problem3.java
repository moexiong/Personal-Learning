package sword2offer.easy;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 */
public class Problem3 {

    /**
     * 个人思路：
     * 1...n个数，找出里面的重复值，最差应该是遍历一遍，最优是前2位就退出。
     * 由于 2 <= n <= 100000 可以知道最大的数不可能超过100000，则我们申请一个长度为100000的字节数组byteArr，然后把值当做index，byteArr[index] = 1，快速查重
     * @param nums
     * @return
     */
    public static int findRepeatNumber(int[] nums) {
        byte[] byteArr = new byte[100000];
        int i = 0;
        while (i < nums.length && byteArr[nums[i]] == 0) {
            byteArr[nums[i]] = 1;
            i++;
        }
        return nums[i];
    }

    public static void main(String[] args) {
        int[] nums = new int[] {2, 3, 1, 0, 2, 5, 3};
        int value = findRepeatNumber(nums);
        System.out.println(value);
    }

}
