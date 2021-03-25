package algorithms.normal;

/**
 * @author: 詹世雄
 * @date: 2021/3/24 23:19
 * @description: 456.132模式 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 *
 * 示例 1：
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 *
 * 示例 2：
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 *
 * 示例 3：
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 */
public class Problem456 {

    /**
     * i < j < k ; nums[i] < nums[k] < nums[j]
     * i => min i < j
     * j => max j > k  k > i
     * 先找出i的下限，即从左向右形成一个低水位拉伸数组。
     * 反向找j，将当前值放入递减的单调栈（相当于一个k的备选值）里，当栈顶 > min[j]（此时表示前面有一个i） 且 nums[j] > 栈顶则退出。存在132序列。否则把大于min[j]的元素弹出栈。或者把小于栈顶的nums[j]加入栈。
     * @param nums
     * @return
     */
    public static boolean find132pattern(int[] nums) {
        if (nums.length < 3) {
            return false;
        }
        int[] min = new int[nums.length];
        // 初始化
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            min[i] = Math.min(nums[i], min[i - 1]);
        }
        // k递减栈
        int[] stack = new int[nums.length];
        // 栈顶元素
        int top = -1;
        for (int k = nums.length - 1; k >= 0; k--) {
            if (nums[k] == min[k]) {
                continue;
            }
            while (top >= 0 && stack[top] <= min[k]) {
                top--;
            }
            if (top >= 0 && nums[k] > stack[top]) {
                return true;
            }
            stack[++top] = nums[k];
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4};
        System.out.println(find132pattern(arr1));
        int[] arr2 = {3, 1, 4, 2};
        System.out.println(find132pattern(arr2));
        int[] arr3 = {-1, 3, 2, 0};
        System.out.println(find132pattern(arr3));
        int[] arr4 = {3, 5, 0, 3, 4};
        System.out.println(find132pattern(arr4));
    }
}
