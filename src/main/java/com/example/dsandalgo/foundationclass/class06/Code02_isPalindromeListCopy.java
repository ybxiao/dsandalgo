package com.example.dsandalgo.foundationclass.class06;

import java.util.Stack;

/**
 * 判断链表是否回文
 * 三种方式 空间复杂度
 * 1  需要O(n)
 * 2  O(n/2)
 * 3  O(1)
 */
public class Code02_isPalindromeListCopy {

    public static class Node {

        private int v;

        private Node next;

        public Node(int v) {
            this.v = v;

        }

    }

    public static boolean isPalindrome1(Node head) {
        //只有一个节点 不可能回文
        if (head == null || head.next == null) {
            return false;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (!stack.isEmpty()) {
            if (head.v != stack.pop().v) {
                return false;
            }

        }
        return true;


    }

    public static boolean isPalindrome2(Node head) {
        //只有一个节点 不可能回文
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

        }
        Node right = slow;
        Stack<Node> stack = new Stack<>();
        while (right != null) {
            stack.push(right);
            right = right.next;
        }

        while (!stack.isEmpty()) {
            if (head.v != stack.pop().v) {
                return false;
            }
        }
        return true;

    }

    public static boolean isPalindrome3(Node head) {
        //只有一个节点 不可能回文
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;

        }

        fast = slow.next;
        slow.next = null;
        Node n3 = null;
        while (fast != null) {
            n3 = fast.next;
            fast.next = slow;
            slow = fast;
            fast = n3;
        }
        //save the last node
        n3 = slow;
        boolean res = true;
        while (head != null && slow != null) {
            if (head.v != slow.v) {
                res = false;
                break;
            }

        }

        //recover
        slow = n3.next;
        n3.next = null;
        while (slow != null) {
            fast = slow.next;
            slow.next = n3;
            n3 = slow;
            slow = fast;

        }
        return res;


    }


}
