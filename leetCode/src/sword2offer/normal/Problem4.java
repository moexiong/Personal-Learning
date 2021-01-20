package sword2offer.normal;

/**
 * @author: Shixiong Zhan
 * @date: 2021/1/17 0:02
 * @description: 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 */
public class Problem4 {

    /**
     * 从左下角开始查找，对于每一个数的比较：大的在右，小的在上
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        return findValue(matrix, matrix.length - 1, 0, target);
    }

    public static boolean findValue(int[][] matrix, int x, int y, int target) {
        if (x < 0 || y > matrix[x].length - 1) {
            return false;
        }
        if (matrix[x][y] == target) {
            return true;
        }
        // up
        if (matrix[x][y] > target) {
            return findValue(matrix, --x, y, target);
        }
        // right
        else {
            return findValue(matrix, x, ++y, target);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        System.out.println("find 5: " + findNumberIn2DArray(matrix, 5));
        System.out.println("find 20: " + findNumberIn2DArray(matrix, 20));
    }

}
