package com.example.dsandalgo.foundationclass.class02;

public class ReversedList {

    public Node reverseNode(Node head){

        if (head == null || head.next ==null){return  head;}
        Node pre = null;
        Node cur = head;
        while (cur.next !=null){
            Node temp  = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;

        }

        return cur;
    }


    public DoubleNode reverseDouble(DoubleNode head){
        if (head == null || head.next ==null){return  head;}

        DoubleNode pre  = null;
        DoubleNode cur = head;
        while (cur.next != null){
            DoubleNode temp = cur.next;

            cur.next =pre;
            cur.last = temp;

            pre = cur;

            cur = temp;

        }



        return cur;
    }

    public static void main(String[] args) {

    }
}
