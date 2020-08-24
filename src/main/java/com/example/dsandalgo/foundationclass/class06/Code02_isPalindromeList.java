package com.example.dsandalgo.foundationclass.class06;

import java.util.Stack;

/**
 * 判断链表是否回文
 * 三种方式 空间复杂度
 * 1  需要O(n)
 * 2  O(n/2)
 * 3  O(1)
 *
 */
public class Code02_isPalindromeList {

    public static class Node{

        private int v;

        private Node next;

        public Node(int v){
            this.v = v;

        }

    }

    public static boolean isPalindrome1(Node head){
        //只有一个节点 不可能回文
        if (head ==null || head.next ==null){
            return false;
        }
        Stack<Node> stack = new Stack<>();
        Node cur =  head;
        while (cur  != null){
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()){
            if (head.v!=stack.pop().v){
                return false;
            }

        }
        return true;


    }

    public static boolean isPalindrome2(Node head){
        //只有一个节点 不可能回文
        if (head ==null || head.next ==null){
            return false;
        }

        Node  slow =  head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null ){
            fast = fast.next.next;
            slow = slow.next;

        }
        Node right  = slow;
        Stack<Node> stack = new Stack<>();
        while (right != null){
            stack.push(right);
            right = right.next;
        }

        while (!stack.isEmpty()){
            if (head.v != stack.pop().v){
                return false;
            }
        }
        return true;

    }

    public static boolean isPalindrome3(Node head){
        //只有一个节点 不可能回文
        if (head ==null || head.next ==null){
            return false;
        }

        Node  n1 =  head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null ){
            n2 = n2.next.next;
            n1 = n1.next;

        }

        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        //save the last node
        n3 = n1 ;
        boolean res =  true;
        while (head != null && n1 !=null){
            if (head.v != n1.v){
                res =  false;
                break;
            }

        }

        //recover
        n1 = n3.next;
        n3.next = null;
        while (n1 != null){
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;

        }
        return res;




    }



}
