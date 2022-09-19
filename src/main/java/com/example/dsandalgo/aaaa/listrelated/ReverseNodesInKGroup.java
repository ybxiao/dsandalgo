package com.example.dsandalgo.aaaa.listrelated;
/**
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 *
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * */
public class ReverseNodesInKGroup {

    public static class ListNode{
        int val;
        ListNode next;
        ListNode(){

        }
        ListNode(int val){
            this.val = val;
        }
        ListNode(int val, ListNode next){
            this.val =val;
            this.next = next;
        }


    }
    public static ListNode reverseKGroup(ListNode head, int k){
        ListNode start = head;
        ListNode end = getKGroupEnd(k, start);
        if (end ==null){
            return head;
        }
        head = end;
        reverse(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getKGroupEnd(k, start);
            if (end == null){
                return head;
            }
            reverse(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }

        return head;

    }

    public static void reverse(ListNode start , ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        ListNode next;
        while (cur.next != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;
    }


    public static ListNode getKGroupEnd(int k, ListNode start){
        System.out.println(start);
        while (--k != 0 && start != null) {
            start = start.next;
            System.out.println(start);
        }
        return start;
    }


    public static void main(String[] args) {
        ListNode node1= new ListNode(1);

        ListNode node2= new ListNode(2);
        ListNode node3= new ListNode(3);
        ListNode node4= new ListNode(4);
        ListNode node5= new ListNode(5) ;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        reverseKGroup(node1,2);

    }


}
