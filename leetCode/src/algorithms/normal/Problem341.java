package algorithms.normal;

import java.util.*;

/**
 * @author: 詹世雄
 * @date: 2021/3/23 22:22
 * @description: 341.扁平化嵌套列表迭代器 给你一个嵌套的整型列表。请你设计一个迭代器，使其能够遍历这个整型列表中的所有整数。
 * 列表中的每一项或者为一个整数，或者是另一个列表。其中列表的元素也可能是整数或是其他列表。
 *
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: [1,1,2,1,1]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: [1,4,6]
 * 解释: 通过重复调用 next 直到 hasNext 返回 false，next 返回的元素的顺序应该是: [1,4,6]。
 */
public class Problem341 implements Iterator<Integer> {

    private transient Deque<Integer> values = new LinkedList<>();
    private transient Integer idx = -1;

    Problem341(List<NestedInteger> nestedList) {
        this.dfs(nestedList);
    }

    private void dfs(List<NestedInteger> nestedIntegers) {
        for (NestedInteger nestedInteger : nestedIntegers) {
            if (nestedInteger.isInteger()) {
                values.offerLast(nestedInteger.getInteger());
            } else {
                dfs(nestedInteger.getList());
            }
        }
    }

    @Override
    public boolean hasNext() {
        return values.size() > 0;
    }

    @Override
    public Integer next() {
        return values.pollFirst();
    }
}
interface NestedInteger {

    boolean isInteger();

    Integer getInteger();

    List<NestedInteger> getList();

}