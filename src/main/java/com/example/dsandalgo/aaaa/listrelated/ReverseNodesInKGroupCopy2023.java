package com.example.dsandalgo.aaaa.listrelated;

/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * <p>
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class ReverseNodesInKGroupCopy2023 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {

        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }


    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        //先找出第一组k个节点的尾节点
        ListNode end = getKGroupEnd(start, k);
        //说明该链表不够一组，无法翻转
        if (end == null) {
            return head;
        }
        head = end;
        reverse(start, end);
        while (start.next != null) {
            ListNode nextStart = start.next;
            ListNode curEnd = getKGroupEnd(nextStart, k);
            if (curEnd == null) {
                return head;
            }
            reverse(nextStart, curEnd);
            start.next = curEnd;
            start = nextStart;
        }


        return end;

    }

    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k > 0 && start != null) {
            start = start.next;
        }
        return start;

    }

    public static void reverse(ListNode start, ListNode end) {
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        cur.next = end;
    }


}
