package com.example.dsandalgo.aaaa.listrelated;

public class IsPalindromeList {


    public static class Node{
        public int val;
        public Node next;
        public Node(int value){
            this.val = value;
        }
    }

    public static boolean isPalindrome3(Node head){
        if (head == null || head.next == null){
            return true;
        }
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null){
            n1 = n2.next;
            n2 = n2.next.next;
        }
        //找到中点 上中点 n1
        n2 = n1.next;// the right part first Node
        n1.next = null;// mid.next = null
        Node n3 = null;
        while (n2 != null){
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;//save the last end
        n2 = head;
        boolean res = true;
        while (n1 != null && n2 != null){
            if (n1.val != n2.val){
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // revert the right part node
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
