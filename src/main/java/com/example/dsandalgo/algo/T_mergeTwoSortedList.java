package com.example.dsandalgo.algo;

public class T_mergeTwoSortedList {

    public static void main(String[] args) {


    }



    public class Node{
        private int v;
        private Node next;


        public Node(int v){
            this.v = v;
        }
    }

    /**
     * 递归法
     * @param L1
     * @param L2
     * @return
     */

    Node mergeRecur(Node L1,Node L2){
        if (L1  == null ){
            return  L2;
        }
        if (L2 == null){
            return L1;
        }
        if (L1.v <= L2.v){
            L1.next =mergeRecur(L1.next,L2);
            return L1;
        }else {
            L2.next = mergeRecur(L1,L2.next);
            return L2;
        }



    }

    /**
     *
     * 迭代法
     * @param L1
     * @param L2
     * @return
     */
    Node mergeIter(Node L1,Node L2){
        if (L1  == null ){
            return  L2;
        }
        if (L2 == null){
            return L1;
        }

        Node head =new Node(0);
        Node tail =head;


        while (L1!=null && L1!=null){
            if (L1.v <= L2.v){
                tail.next = L1;
                L1 = L1.next;
            }else{
                tail.next  = L2;
                L2 = L2.next;
            }
            tail = tail.next;
        }
        tail.next = L1==null ?L2:L1;

       return head.next;
    }


}

