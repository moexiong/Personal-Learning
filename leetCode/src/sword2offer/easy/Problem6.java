package sword2offer.easy;

import java.util.Arrays;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 *  
 * 限制：
 * 0 <= 链表长度 <= 10000
 */
public class Problem6 {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 个人思路：
     * 链表长度未知，用数组返回无法确定长度，但最后一个节点可以知道
     * 对于一个m长度的链表，第n个node在int[]中的位置应该是index=m-n的。
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        return reverseNode(head, 0);
    }

    public static int[] reverseNode(ListNode node, int index) {
        int[] value;
        index++;
        if (node.next != null) {
            value = reverseNode(node.next, index);
        } else {
            value = new int[index];
        }
        value[value.length - index] = node.val;
        return value;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(3, new ListNode(2)));
        int[] reversePrint = reversePrint(node);
        System.out.println(Arrays.toString(reversePrint));
    }

}
