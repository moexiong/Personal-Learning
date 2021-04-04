package algorithms.normal;

import java.util.Arrays;

/**
 * @author: 詹世雄
 * @date: 2021/4/4 23:00
 * @description: 781.森林中的兔子
 * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。我们将这些回答放在 answers 数组里。
 * 返回森林中兔子的最少数量。
 *
 * 示例:
 * 输入: answers = [1, 1, 2]
 * 输出: 5
 * 解释:
 * 两只回答了 "1" 的兔子可能有相同的颜色，设为红色。
 * 之后回答了 "2" 的兔子不会是红色，否则他们的回答会相互矛盾。
 * 设回答了 "2" 的兔子为蓝色。
 * 此外，森林中还应有另外 2 只蓝色兔子的回答没有包含在数组中。
 * 因此森林中兔子的最少数量是 5: 3 只回答的和 2 只没有回答的。
 *
 * 输入: answers = [10, 10, 10]
 * 输出: 11
 *
 * 输入: answers = []
 * 输出: 0
 *
 * 说明:
 * answers 的长度最大为1000。
 * answers[i] 是在 [0, 999] 范围内的整数。
 */
public class Problem781 {

    /**
     * 不考虑说谎的情况：兔子如果说的数量不一样，一定是不同颜色的，即说还有n个，即存在n + 1个兔子；同样意味着最多有n + 1个兔子说还有n个兔子与它颜色相同。
     * 对于兔子说出的n，若n <= n + 1，则可以认为都是同一种兔子，超过的一定是不同种，可以先统计，然后重置计数。
     * @param answers
     * @return
     */
    public static int numRabbits(int[] answers) {
        int[] rabbits = new int[1000];
        int sum = 0;
        for (int answer : answers) {
            if (rabbits[answer] > answer) {
                sum = sum + answer + 1;
                rabbits[answer] = 1;
            } else {
                rabbits[answer]++;
            }
        }
        for (int j = 0; j < rabbits.length; j++) {
            if (rabbits[j] > 0) {
                sum = sum + j + 1;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2};
        System.out.println(Arrays.toString(arr1) + " => " + numRabbits(arr1));
        int[] arr2 = {10, 10, 10};
        System.out.println(Arrays.toString(arr2) + " => " + numRabbits(arr2));
        int[] arr3 = {};
        System.out.println(Arrays.toString(arr3) + " => " + numRabbits(arr3));
        int[] arr4 = {1, 0, 1, 0, 0};
        System.out.println(Arrays.toString(arr4) + " => " + numRabbits(arr4));
    }

}
