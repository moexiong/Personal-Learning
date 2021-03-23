package algorithms.normal;

import java.util.*;

/**
 * @author: 詹世雄
 * @date: 2021/3/21 17:46
 * @description: 15.三数之和 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 * 提示：
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class Problem15 {

    /**
     * 排序，有序数组内，找出3数之和为 0。即 a + b + c = 0
     * 定一个数，改求2数之和。
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 3) {
            return ans;
        }
        Arrays.sort(nums);
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return ans;
        }
        // 固定左侧数一定为非正，求2数之和等于左数
        int[] rep = new int[-nums[0] + 1];
        for (int i = 0; i < nums.length - 1 && nums[i] <= 0; i++) {
            if (rep[-nums[i]] < 1) {
                twoSum(nums, i + 1, nums[i], ans);
                rep[-nums[i]] = 1;
            }
        }
        return ans;
    }

    private static void twoSum(int[] nums, int i, int target, List<List<Integer>> ans) {
        // i -> v <- j
        int j = nums.length - 1;
        // j >= 0
        int[] rep = new int[nums[j] + 1];
        while (i < j) {
            if (nums[i] + nums[j] > -target) {
                j--;
            } else if (nums[i] + nums[j] < -target) {
                i++;
            } else {
                if (rep[nums[j]] < 1) {
                    List<Integer> as = new ArrayList<>();
                    as.add(target);
                    as.add(nums[i]);
                    as.add(nums[j]);
                    ans.add(as);
                    rep[nums[j]] = 1;
                }
                i++;
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {-1, 0, 1, 2, -1, -4};
        // [[-1,-1,2],[-1,0,1]]
        System.out.println(threeSum(arr));
        int[] arr2 = new int[] {0, 0, 0};
        // [[0,0,0]]
        System.out.println(threeSum(arr2));
        int[] arr3 = new int[] {-1, 0, 1, 2, -1, -4, -2, -3, 3, 0, 4};
        // [[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]]
        System.out.println(threeSum(arr3));
        int[] arr4 = new int[] {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        // [[-4,-2,6],[-4,0,4],[-4,1,3],[-4,2,2],[-2,-2,4],[-2,0,2]]
        System.out.println(threeSum(arr4));
    }

}
