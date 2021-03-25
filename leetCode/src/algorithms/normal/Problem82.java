package algorithms.normal;

/**
 * @author: 詹世雄
 * @date: 2021/3/25 21:25
 * @description: 82.删除排序链表中的重复元素 II 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * 返回同样按升序排列的结果链表。
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class Problem82 {

    /**
     * 记一个头结点
     * 一个上一个节点 -> 判断当前节点与下一个节点
     * cur == next => 不做处理，并打重复标记
     * cur != next => 记录，恢复重复标记
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode newHead = null;
        ListNode last = null;
        ListNode cur = head;
        boolean re = false;
        while (cur != null) {
            if (cur.next == null) {
                if (re) {
                    re = false;
                } else if (last != null) {
                    last.next = new ListNode(cur.val);
                } else {
                    last = new ListNode(cur.val);
                    newHead = last;
                }
            } else if (cur.val == cur.next.val) {
                re = true;
            } else {
                if (re) {
                    re = false;
                } else {
                    if (last == null) {
                        last = new ListNode(cur.val);
                        newHead = last;
                    } else {
                        last.next = new ListNode(cur.val);
                        last = last.next;
                    }
                }
            }
            cur = cur.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1,
                new ListNode(2,
                        new ListNode(3,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(4,
                                                        new ListNode(5)))))));
        System.out.println(deleteDuplicates(node));
        ListNode n1 = new ListNode(1,
                new ListNode(1,
                        new ListNode(1,
                                new ListNode(2,
                                        new ListNode(3)))));
        System.out.println(deleteDuplicates(n1));
        ListNode n2 = new ListNode(1,
                new ListNode(1));
        System.out.println(deleteDuplicates(n2));
        ListNode n3 = new ListNode(1,
                new ListNode(2,
                        new ListNode(2)));
        System.out.println(deleteDuplicates(n3));
    }

}