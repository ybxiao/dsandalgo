package com.example.dsandalgo.class06;

public class Code03_SmallerEquelBigger {

    public static class Node{
        private int v ;
        private Node next;

        public Node(int v){
            this.v = v;

        }

    }

    /**
     * 对链表的操作一定要注意边界问题
     * @param head
     * @param pivot
     * @return
     */
    public static Node listPartition1(Node head ,int pivot){
        if (head == null || head.next == null){
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null){
            i++;
            cur = cur.next;
        }

        Node[] nodes = new Node[i];

        cur = head;
        i = 0;
        while (cur != null ){
            nodes[i++] = cur;
            cur = cur.next;
        }
        arrPartion(nodes,pivot);
        for (int j = 1; j <nodes.length; j++) {
            nodes[j-1].next = nodes[j];
         }
        nodes[nodes.length-1].next = null;

        return nodes[0];



    }

    private static void arrPartion(Node[] nodes, int pivot) {
        int L  = -1;
        int R = nodes.length;
        int index = 0;
        while (index < R){
            if (nodes[index].v < pivot){
                swap(nodes,++L ,index);
                index++;
            }
            if (nodes[index].v == pivot){
                index++;
            }
            if (nodes[index].v > pivot){
                swap(nodes,index,--R);
            }
        }



    }

    private static void swap(Node[] nodes, int index, int i) {
        Node temp = nodes[index];
        nodes[index] = nodes[i];
        nodes[i] = temp;
    }
}
