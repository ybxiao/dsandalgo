package com.example.dsandalgo.foundationclass.class02;

public class ReversedList {

    public Node reverseNode(Node head){
        if (head == null  || head.next == null){
            return head;
        }

        Node  cur = head;
        Node pre = null;
        while (cur.next  != null){
            Node temp = cur.next;
            cur.next = pre;
            temp.next = cur;
            pre = cur;
            cur = temp;
        }
        return cur;
    }

    public Node reverseNodeV2(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node pre = null;
        Node cur = head;
        while (cur.next != null){
            Node T =  cur.next;

            cur.next = pre;
            T.next = cur;
            pre = cur;
            cur = T;

        }

        return cur;
    }




    public DoubleNode reverseDouble(DoubleNode head){
        if (head == null || head.next ==null){return  head;}
        DoubleNode   cur = head;
        DoubleNode pre =  null;
        while (cur.next != null){
            DoubleNode temp = cur.next;
            cur.next = pre;
            cur.last =  temp;
            pre = cur;
            cur = temp;

        }
        return cur;
    }

    /**
     * last 指向上一个节点
     * next 指向下一个节点
     * @param head
     * @return
     */
    public DoubleNode reverseDoubleV2(DoubleNode head){
        if (head == null || head.next == null){
            return head;
        }
        DoubleNode pre = null;
        DoubleNode cur =  head;
        while (cur.next != null){
            DoubleNode T =  cur.next;
            cur.next = pre;
            cur.last = T;
            pre = cur;
            cur = T;

        }
        return cur;
    }


    public DoubleNode reverseDoubleV3(DoubleNode head){
        if (head == null){
            return null;
        }

        DoubleNode pre = null;
        DoubleNode cur = head;
        while (cur.next != null){
            DoubleNode temp = cur.next;
            cur.next = pre;
            cur.last = temp;
            pre = cur;
            cur =temp;

        }

        return cur;




    }

    public Node reverseNodeV3(Node head){
        if (head == null || head.next == null){
            return null;
        }
        Node cur = head;
        Node pre =null;
        while (cur.next != null){
            Node temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;


        }
        return cur;



    }


}
