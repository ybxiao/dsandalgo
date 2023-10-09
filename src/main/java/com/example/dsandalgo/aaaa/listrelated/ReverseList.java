package com.example.dsandalgo.aaaa.listrelated;

public class ReverseList {


    public Node reverseLinkedList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return cur;
    }


    public Node reverseLinkedList2(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        //todo
        return null;
    }


    public DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        DoubleNode pre = null;
        DoubleNode cur = head;
        while (cur.next != null) {
            DoubleNode temp = cur.next;
            cur.next = pre;
            cur.last = temp;
            pre = cur;
            cur = temp;
        }
        return cur;

    }

    public static Node reverseLinkedList3(Node head){
        if (head == null || head.next == null){
            return head;
        }
        return process(head);


    }
    //递归的翻转链表
    public static Node process(Node head){
        if (head.next == null){
            return head;
        }else{
            Node cur = process(head.next);
            head.next.next = head;
            head.next = null;
            return cur;
        }

    }



}
