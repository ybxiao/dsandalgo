package com.example.dsandalgo.aaaa.listrelated;

public class ReverseList {




    public Node reverseLinkedList(Node head){
        if (head == null || head.next == null){
            return head;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null){
            Node temp =  cur.next;
            cur.next = pre;
            pre = cur;
            cur =temp;
        }
        return cur;
    }




    public DoubleNode reverseDoubleList(DoubleNode head){
        if (head == null  || head.next == null){
            return head;
        }
        DoubleNode pre = null;
        DoubleNode cur = head;
        while (cur.next != null){
            DoubleNode temp = cur.next;
            cur.next = pre;
            cur.last = temp;
            pre = cur;
            cur = temp;
        }
        return cur;

    }


}
