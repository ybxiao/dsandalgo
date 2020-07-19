package com.example.dsandalgo.class07;

/**
 * 打印二叉树
 */
public class Code05_PrintBinayTree {

    public static class Node{
        private int value;
        private Node left;
        private Node right;

        public Node(int value){
            this.value = value;
        }


    }


    public static void printBinaryTree(Node head){
        if (head == null){
            return;
        }

        printInOrder(head,0,"H",17);
    }

    /**
     * 中序遍历 打印
     * @param head
     * @param height 链表层级
     * @param h 标识 'H' 'v' '^'
     * @param len 字符打印宽度
     */
    private static void printInOrder(Node head, int height, String h, int len) {
        if (head == null){
            return;
        }
        printInOrder(head.right,height+1,"v",17);

        String v =h + head.value + h;
        int i = (len - v.length()) / 2;
        System.out.println(getSpace(height*len + i, v));

        printInOrder(head.left,height+1,"^",17);


    }

    private static String getSpace(int i, String v) {
        StringBuilder sb = new StringBuilder();

        for (int j = 0; j < i; j++) {
            sb.append(" ");
        }
        sb.append(v);
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printBinaryTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printBinaryTree(head);

    }
}
