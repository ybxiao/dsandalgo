package com.example.dsandalgo.aaaa.listrelated;

public class ReverseListCopy {


    public Node reverseLinkedList(Node head) {
        if (head == null){
            return null;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null){
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return cur;
    }







}
