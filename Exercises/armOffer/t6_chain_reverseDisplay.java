package Exercises.armOffer;


import java.util.ArrayList;

import Fundamentals.base.Node;


/**
 * 从尾到头打印链表
 */
public class t6_chain_reverseDisplay {

    // 头插法
    public ArrayList<Integer> printListFromTailToHead(Node<Integer> node) {
        ArrayList<Integer> res = new ArrayList<>();
        Node<Integer> fack = new Node<>();
        fack.next = node;
        Node<Integer> pre = fack;
        Node<Integer> curr = node;
        while(curr != null && curr.next != null) {
            pre.next = curr.next;
            curr.next = curr.next.next;
            pre.next.next = curr;
            pre = pre.next;
            curr = curr.next;
        }
        // interate
        curr = fack.next;
        while(curr != null) {
            res.add(curr.data);
            curr = curr.next;
        }
        return res;
    }
}