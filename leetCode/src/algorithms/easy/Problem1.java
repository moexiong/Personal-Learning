package algorithms.easy;

import java.util.*;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，
 * 并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例：
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 */
public class Problem1 {

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        // 没有存在的结果
        return null;
    }

    /**
     * 最优速度解答
     * @param nums
     * @param target
     * @return
     */
    private int[] twoSum_best(int[] nums, int target) {
        int indexArrayMax = 2047;
        int[] indexArrays = new int[indexArrayMax + 1];
        int diff = 0;
        for (int i = 0; i < nums.length; i++) {
            diff = target - nums[i];
            if (indexArrays[diff & indexArrayMax] != 0) {
                return new int[] { indexArrays[diff & indexArrayMax] - 1, i };
            }
            indexArrays[nums[i] & indexArrayMax] = i + 1;
        }
        throw new IllegalArgumentException("No two sum value");
    }

    public static void main(String[] args) {
        int[] num1 = new int[]{1, 5, 3, 8, 4};
        System.out.println("first: " + Arrays.toString(twoSum(num1, 7)));
        num1 = new int[]{3, 5, 1, 8, 5};
        System.out.println("second: " + Arrays.toString(twoSum(num1, 10)));
    }
}
