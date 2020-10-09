package com.example.dsandalgo.leecode;

/**
 * 去除链表中的重复项
 *
 *
 * 给出一个升序排序的链表，删除链表中的所有重复出现的元素，只保留原链表中只出现一次的元素。
 * 例如：
 * 给出的链表为  1→2→3→3→4→4→5, 返回1→2→5.
 * 给出的链表为1→1→1→2→3, 返回2→3.
 *
 *
 */
public class InterView_code_01 {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            val = val;
        }
    }

    public ListNode removeReplicate(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            if (fast.val == slow.val) {
                fast = fast.next;
            }


        }


        return null;

    }

}
