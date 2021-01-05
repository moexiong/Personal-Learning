package algorithms.easy;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class Problem21 {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
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
     * a1->a2->a3->a4->a5...
     * b1->b2->b3->b4->b5...
     * 已知：这俩都有序；问题：增长的跨度无法确定
     * 定义：Nc => min(la, lb)；则 Nc.next => min(l?.next, l?)；lc => N[c=1->n]
     * 含义：对于la队列和lb队列，我们可以每次找出这2个队列中头节点最小的一个作为“当前根节点”，然后从根的next计算剩下的队列lc与另一个最初的队列(la or lb)再次进行对比，互相抢占“C位”。
     * @param la
     * @param lb
     * @return
     */
    public static ListNode mergeTwoLists(ListNode la, ListNode lb) {
        return findMinNode(la, lb);
    }

    public static ListNode findMinNode(ListNode la, ListNode lb) {
        if (la == null) {
            return lb;
        }
        if (lb == null) {
            return la;
        }
        if (la.val > lb.val) {
            lb.next = findMinNode(la, lb.next);
            return lb;
        } else {
            la.next = findMinNode(la.next, lb);
            return la;
        }
    }

    public static void main(String[] args) {
        ListNode la = new ListNode(1, new ListNode(2, new ListNode(4, new ListNode(6))));
        ListNode lb = new ListNode(1, new ListNode(3, new ListNode(4, new ListNode(5))));
        ListNode header = mergeTwoLists(la, lb);
        System.out.print(header.val + "->");
        while (header.next != null) {
            header = header.next;
            System.out.print(header.val + "->");
        }
    }

}
