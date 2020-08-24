package com.example.dsandalgo.foundationclass.class08;

/**
 * 如何判断一颗二叉树是否是满二叉树
 * 第一种暴力求解
 * 第二种利用二叉树的递归套路求解
 *
 */
public class Code02_IsFull {

    public static class Node{
        private int v;
        private Node left;

        private Node right;

        public Node(int val){
            this.v = val;
        }
    }

    /**
     * 分别算出树的高度和节点总个数
     * @param head
     */
    public static boolean isFull1(Node head){
        if (head == null){
            return false;
        }
        int h =  h(head);
        int nodes = n(head);
        return 1<<h  -1==nodes;


    }

    private static int n(Node head) {
        if (head ==null){
            return 0;
        }
        return n(head.right) + n(head.left) +1;
    }

    private static int h(Node head) {
        if (head == null){
            return 0;
        }
        return Math.max(h(head.left),h(head.right))+1;
    }

    /**
     * 用递归求解
     * @param head
     * @return
     */
    public static boolean isFull2(Node head){
        if (head == null){
            return true;
        }

        Info all =  process(head);
        return 1 <<all.height -1  ==  all.nodes;

    }

    private static Info process(Node head) {
        if (head == null){
            return new Info(0,0);
        }

        Info left = process(head.left);
        Info right = process(head.right);

        //height nodes

        int nodes =  (left == null? 0 : left.nodes) + (right == null ? 0 :right.nodes);
        int height = Math.max((left == null? 0 : left.height),(right == null? 0 : left.height)) +1;
        return new Info(nodes,height);

    }

    //设计info
    public static class Info{
        private int nodes;
        private int height;

        public Info(int nodes,int height){
            this.nodes = nodes;
            this.height = height;
        }

    }



    public static Node generateRandomBST(int maxLevel ,int maxValue){

        return generate(1,maxLevel,maxValue);


    }

    private static Node generate(int i, int maxLevel, int maxValue) {
        if (i > maxLevel || Math.random() <  0.5){
            return null;
        }
        Node head = new Node((int) Math.random() * maxValue);
        head.left =  generate(i+1,maxLevel,maxValue);
        head.right =  generate(i+1,maxLevel,maxValue);
        return head;

    }

}
