package com.example.dsandalgo.aaaa.listrelated;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandom {
    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static Node copyListWithRandom1(Node head) {
        if (head == null) {
            return head;
        }
        Map<Node, Node> old2New = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            old2New.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            old2New.get(cur).next = old2New.get(cur.next);
            old2New.get(cur).random = old2New.get(cur.random);
            cur = cur.next;
        }

        return old2New.get(head);


    }


    public static Node copyListWithRandom2(Node head){


        return null;
    }

}
