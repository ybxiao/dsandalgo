package com.example.dsandalgo.class06;

import java.util.HashMap;
import java.util.Map;

/**
 * 一种特殊的单链表节点类描述如下
 * class Node {
 * int value;
 * Node next;
 * Node rand;
 * Node(int val) { value = val; }
 * }
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点 head，请实现一个函数完成这个链表的复制，并返回复制的新链表的头节点。
 * 【要求】
 * 时间复杂度O(N)，额外空间复杂度O(1)
 *
 *
 */
public class Code04_CopyListWithRandom {

    public static class Node{
        private int value;
        private Node next;
        private Node rand;
        public Node(int val){
            value = val;
        }
    }

    public static Node copyListWithRand1(Node head){
        if (head == null) {return  null;}
        Map<Node,Node> map =  new HashMap<Node ,Node>();
        Node cur  =  head;
        while (cur != null){
            Node node = new Node(cur.value);
            map.put(cur,node);
            cur = cur.next;
        }

        cur=head;

        while (cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);

    }


    public static Node copyListWithRand2(Node head){
        Node cur  =  head;

        while (cur != null){
            Node node = new Node(cur.value);

            Node temp = cur.next;
            cur.next =  node;
            node.next = temp;
            cur  = cur.next;
        }
        cur  = head;
        while (cur != null){

            Node temp =  cur.next.next;
            cur.next.rand = cur.rand==null?null:cur.rand.next;
            cur = temp;
        }
        //拆分
        cur  = head;
        Node copyHead =  cur.next;

        while (cur != null){

            Node temp =  cur.next.next;

            cur.next.next = temp==null?null:temp.next;
            cur.next = temp;
            cur = temp;

        }

        return copyHead;



    }



}
