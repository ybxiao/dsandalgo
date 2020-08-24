package com.example.dsandalgo.foundationclass.class06;

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
     * 第一种方法，先把链表的元素放到数组里面，再对数组做荷兰国旗问题求解
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

    public static Node listPartition2(Node head ,int pivot){
        if (head == null || head.next == null){
            return head;
        }
        Node sHead = null;
        Node sTail = null;
        Node eHead = null;
        Node eTail = null;
        Node gHead = null;
        Node gTail = null;

        Node cur  = head;
        while (cur != null) {
            if (cur.v < pivot){
                if (sHead == null){
                    sHead = sTail = cur;
                }else {
                    sTail.next = cur;
                    sTail = sTail.next;
                }

            }

            if (cur.v == pivot){
                if (eHead == null){
                    eHead = eTail = cur;
                }else {
                    eTail.next = cur;
                    eTail = eTail.next;
                }

            }

            if (cur.v == pivot){
                if (gHead == null){
                    gHead = gTail = cur;
                }else {
                    gTail.next = cur;
                    gTail = gTail.next;
                }

            }
            cur = cur.next;

        }

       if (sTail  != null){
           sTail.next = (eHead==null) ? gHead:eHead;
           return sHead;
       }
       if (sHead == null){
           if (eHead != null){
               eTail.next = gHead;
               return eHead;
           }else {
               return gHead;
           }
       }





    return null;

    }




}
