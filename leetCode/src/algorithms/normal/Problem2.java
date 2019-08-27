package algorithms.normal;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 */
public class Problem2 {

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 重用l1的对象链表，记住头节点
        ListNode node = l1;
        int offset = 0;
        int sum = l1.val + l2.val + offset;
        l1.val = sum % 10;
        // 进位
        offset = sum / 10;
        // 2个都有下个节点，相加
        while (l1.next != null && l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
            sum = l1.val + l2.val + offset;
            l1.val = sum % 10;
            offset = sum / 10;
        }
        // l2更长，l1指向l2的剩余节点
        if (l2.next != null) {
            l1.next = l2.next;
        }
        // 对剩余位进行进位处理
        while (l1.next != null) {
            l1 = l1.next;
            sum = l1.val + offset;
            l1.val = sum % 10;
            offset = sum / 10;
        }
        // 增长位
        if (offset > 0) {
            l1.next = new ListNode(offset);
        }
        return node;
    }

    /**
     * 最优速度解答
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers_best(ListNode l1, ListNode l2) {
        ListNode p = l1;
        ListNode q = l2;
        int addNum = 0;
        while(q != null){
            if(p.next == null && q.next != null)
                p.next = new ListNode(0);
            if(q.next == null && p.next != null)
                q.next = new ListNode(0);
            int sumAll = addNum + p.val + q.val;
            p.val = sumAll % 10;
            addNum = sumAll / 10;
            if(p.next == null && q.next == null && addNum!=0)
                p.next = new ListNode(addNum);
            p = p.next;
            q = q.next;
        }
        return l1;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);
        ListNode node = addTwoNumbers(l1, l2);
        StringBuilder output = new StringBuilder();
        output.append(node.val);
        while (node.next != null) {
            node = node.next;
            output.append(" -> ").append(node.val);
        }
        System.out.println(output.toString());
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}