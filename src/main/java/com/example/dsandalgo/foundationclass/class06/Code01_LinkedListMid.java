package com.example.dsandalgo.foundationclass.class06;

import java.util.ArrayList;
import java.util.List;

/**
 * 快慢指针
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点
 * midOrUpMidNode
 *
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点
 *midOrDownMidNode
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
 *midOrUpMidPreNode
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
 *midOrDownMidPreNode
 *
 */
public class Code01_LinkedListMid {

    public static class Node{
        private int v;

        private Node next;
        public Node(int v ){
            this.v = v;
        }


    }


    public static Node midOrUpMidNode(Node head){

        if (head == null || head.next ==null || head.next.next == null){
            return head;
        }
        Node slow =  head;
        Node fast = head.next.next;
        while (fast.next!= null && fast.next.next !=null){
            fast = fast.next.next;
            slow = slow.next;

        }
        return slow.next;


    }

    public static Node midOrDownMidNode(Node head){
        if (head == null || head.next ==null || head.next.next == null){
            return head;
        }
        Node slow =  head;
        Node fast =  head.next;

        while (fast.next != null && fast.next.next != null){

            fast = fast.next.next;
            slow = slow.next;

        }
        return slow.next;

    }

    public static Node midOrUpMidPreNode(Node head){

        if (head == null || head.next ==null || head.next.next == null){
            return head;
        }
        Node slow =  head;
        Node fast =  head.next.next;

        while (fast.next != null && fast.next.next != null){

            fast = fast.next.next;
            slow = slow.next;

        }
        return slow;

    }


    public static Node midOrDownMidPreNode(Node head){

        if (head == null || head.next ==null || head.next.next == null){
            return head;
        }
        Node slow =  head;
        Node fast =  head.next;

        while (fast.next != null && fast.next.next != null){

            fast = fast.next.next;
            slow = slow.next;

        }
        return slow;


    }

    /**
     * 使用链表
     * @param head
     * @return
     */
    public static Node right(Node head){
        if (head == null || head.next ==null || head.next.next == null){
            return head;
        }
        Node cur = head;
        List<Node> list = new ArrayList<>();
        list.add(cur);
        while (cur.next  != null){
            list.add(cur.next);
            cur = cur.next;
        }
        return list.get((list.size()-1)/2);




    }



}
