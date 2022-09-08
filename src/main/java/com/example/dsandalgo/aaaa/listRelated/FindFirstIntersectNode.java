package com.example.dsandalgo.aaaa.listRelated;

import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点。如果不相交返回null
 * 要求如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)
 * 分一下几种情况
 * 1 两个链表都有环
 *  1 分析两个链表的入环节点：
 *      1 如果两个链表相交，那么两个链表肯定共用一个环。
 *      2 如果两个链表的入环节点是同一个，两个链表肯定相交且相交的第一个节点肯定位于两个链表头节点到入环节点的路上。
 *      3 如果两个链表的入环节点不是同一个，那么从其中一个链表的入环节点开始遍历如果能走到另一个链表的入环节点，则两个链表相交，且相交的第一个节点就是任意一个入环的节点。
 *      4 其他情况都不相交。
 * 2 两个链表一个有环，一个无环（不可能相交）
 * 3 两个链表都无环
 *  1 先判断两个链表的长度。如果两个链表相交，那么从相交节点开始，两个链表到尾节点的长度必定是一致的。
 *  2 只需要把两个链表对齐长度，依次往下遍历，如果遇到相同的节点，则说明两个链表相交，否则不相交。
 *
 * 衍生面试题：
 * 如何判定一个链表是否有环，并返回其第一个入环节点。
 *
 *
 *
 *
 */
public class FindFirstIntersectNode {


    public static class Node{
        private int value;
        private Node next;
        public Node(){
        }
    }


    public Node getFirstIntersectNode(Node head1, Node head2)  {
        if (head1 == null || head2 == null){
            return null;
        }
        Node loopNode1 = getLoopNode(head1);
        Node loopNode2 = getLoopNode(head2);
        if (loopNode1 == null && loopNode2 == null) {
            return noLoop(head1, head2);
        }
        if (loopNode1 != null && loopNode2 != null) {
            return bothLoop(head1, loopNode1, head2, loopNode2);
        }

        return null;
    }

    private Node bothLoop(Node head1, Node loopNode1, Node head2, Node loopNode2) {
        if (loopNode1 == loopNode2){
           Node cur1 = head1;
           Node cur2 = head2;
           int n = 0;
           while (cur1 != loopNode1){
               n++;
               cur1 =  cur1.next;
           }
           while (cur2 !=  loopNode2){
               n--;
               cur2 =  cur2.next;
           }
           cur1 = n > 0 ? head1 :head1;
           cur2 = cur1 == head1 ? head2 :head1;
           while (cur1 != cur2){
               cur1 = cur1.next;
               cur2 = cur2.next;
           }
           return cur1;

        }else{
            Node cur1 = loopNode1;
            while (cur1 != null){
                cur1 = cur1.next;
                if (cur1 == loopNode2){
                    return loopNode1;
                }
                if (cur1 == loopNode1){
                    return null;
                }
            }
        }


        return null;
    }

    private Node noLoop(Node head1, Node head2) {
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 1;
        while (cur1.next != null){
            n ++;
            cur1 =  cur1.next;
        }
        while(cur2.next != null){
            n --;
            cur2 = cur2.next;
        }
        if (cur1 != cur2){
            return null;
        }
        //较长的链表用cur1，较短的哪个用cur2
        if (n < 0){
            cur1 = head2;
        }
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while ( n > 0){
            cur1 = cur1.next;
            n--;
        }
        while (cur1 != cur2){
            if (cur1 == null){
                return null;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }


    /**
     * 给一个链表的头节点，判定该链表是否有环
     * 如果有环，返回入环节点
     * 否则返回 null
     * @param head
     * @return
     */
    public Node getLoopNode(Node head){
        if (head == null || head.next ==null){
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;
        while (fast != slow  ) {
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            slow = head.next;
            fast = head.next.next;
        }
        fast = head;
        while ( fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        HashSet<Long> set1 = Sets.newHashSet();
        HashSet<Object> set2 = Sets.newHashSet();
        for (int i = 0; i < 1000; i++) {
            set1.add((long)(Math.random() * 1000000));
            set2.add((long)(Math.random() * 1000000));
        }
        System.out.println(Sets.difference(set1, set2).size());



    }





}
