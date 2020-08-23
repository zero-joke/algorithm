package Exercises.linkLisk;

import base.Node;

/**
 * 链表反转
 */
public class LinkListReverse {
    // 反转整个链表
    public Node reverse(Node node) {
        Node pre = null, cur = node;
        while (cur != null) {
            // 反转节点
            Node nxt = cur.next;
            cur.next = pre;
            // 向后移动
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // 反转 a 到 b 之间的结点  [a, b)
    public Node reverse(Node a, Node b) {
        Node pre = null, cur = a;
        while (cur != b) {
            // 反转节点
            Node nxt = cur.next;
            cur.next = pre;
            // 向后移动
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    // K 个一组反转链表, 如果剩余节点少于k个，按原顺序输出
    public Node reverse(Node head, int k) {
        Node a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null)
                return head; // 不足 k 个，不需要反转，base case
            b = b.next;
        }
        Node newHead = reverse(a, b);
        a.next = reverse(b, k);
        return newHead;
    }
}