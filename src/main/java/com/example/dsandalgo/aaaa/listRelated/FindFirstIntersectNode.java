package com.example.dsandalgo.aaaa.listRelated;

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
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {

        }


    }



}
