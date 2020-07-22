package com.example.dsandalgo.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断一棵树是不是二叉搜索树
 * 第一种方法 中序遍历二叉树把所有节点放入预先准备好的数组中
 * 在判断数组中的元素是否依次递增的
 * 第二种方法
 * 利用二叉树的递归套路求解
 */
public class Code03_IsBST {

    public static class Node{
        private int v;

        private Node right;

        private Node left;
        public Node(int val){
            this.v = val;
        }
    }

    public static boolean isBST1(Node head){
        if (head == null){
            return true;
        }
        List<Node> nodes =  new ArrayList<>();
        inOrder(head,nodes);

        for (int i = 0; i < nodes.size()-1; i++) {
            if (nodes.get(i).v > nodes.get(i+1).v){
                return false;
            }

        }


    return true;



    }

    private static void inOrder(Node head, List<Node> nodes) {
        inOrder(head.left,nodes);
        nodes.add(head);
        inOrder(head.right,nodes);

    }

    public static  boolean isBST2(Node head){
        if (head == null){
            return true;
        }
        return  process(head).isBST;

    }

    private static Info process(Node head) {
        if (head == null) {
            return new Info(true,0,0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        boolean isBST =  left.isBST && right.isBST && (left.maxValue< head.v) && (head.v> right.minValue );
        int min = left.minValue;
        int max =  right.maxValue;
        return new Info(isBST,max,min);



    }


    public static class Info{

        private boolean isBST;

        private int maxValue;
        private int minValue;

        public Info(boolean isBST, int max,int min){
            this.isBST = isBST;
            this.maxValue = max;
            this.minValue = min;
        }

    }

}
