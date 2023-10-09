package com.example.dsandalgo.aaaa.listrelated;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并两个有序列表
 * 测试链接：https://leetcode.com/problems/merge-two-sorted-lists
 */
public class MergeTwoSortedLinkedListCopy2023 {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    public static ListNode mergeTwoSortedLinkedList(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : null;
        }
        //找出新列表的头部
        ListNode head = head1.val < head2.val ? head1 : head2;
        ListNode cur = head;
        ListNode cur1 = head == head1 ? head1.next : head1;
        ListNode cur2 = head == head2 ? head2.next : head2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                cur.next = cur1;
                cur1 = cur1.next;
            } else {
                cur.next = cur2;
                cur2 = cur2.next;
            }
            cur = cur.next;
        }
        cur.next = cur1 == null ? cur2 : cur1;
        return head;

    }

    public static ListNode mergeKList(List<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(lists.size(), Comparator.comparingInt(a -> a.val));
        ListNode newHead = null;
        for (int i = 0; i < lists.size(); i++) {
            heap.add(lists.get(0));
        }
        newHead = heap.poll();
        if (newHead.next != null) {
            heap.add(newHead.next);
        }
        while (!heap.isEmpty()) {
            ListNode poll = heap.poll();
            newHead.next = poll;
            if (poll.next != null) {
                heap.add(poll.next);
            }

        }
        return newHead;

    }

}
