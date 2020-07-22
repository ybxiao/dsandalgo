package com.example.dsandalgo.class08;

import javax.imageio.ImageTranscoder;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *给定一棵二叉树的头节点head，
 * 返回这颗二叉树中最大的二叉搜索子树的大小
 *
 * 两种方法
 * 都是递归
 *
 */
public class Code04_MaxSubBSTSize {

    public static class Node{
        private int val;

        private Node left;

        private Node right;

        public Node(int val){
            this.val =  val;
        }
    }

    /**
     * 判断以head为头的二叉树是否是二叉搜索树
     * 如果是 返回节点个数
     * 如果不是 返回 0
     * @param head
     * @return
     */
    public static int getBSTSize(Node head){
        if (head  == null){
            return  0;
        }
        List<Node> arr =  new ArrayList<Node>();
        inOrder(head,arr);
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i).val  > arr.get(i+1).val){
                return 0;
            }
        }
        return arr.size();



    }

    private static void inOrder(Node head, List<Node> arr) {
        if (head == null){
            return;
        }
        inOrder(head.left, arr);
        arr.add(head);
        inOrder(head.right,arr);
    }

    public static int maxSubBSTSize(Node head){
        if (head == null){
            return  0;
        }
        int bstSize = getBSTSize(head);
        if (bstSize != 0){
            return bstSize;
        }

       return Math.max(maxSubBSTSize(head.left),maxSubBSTSize(head.right));


    }


    public static class Info{
        private int size;

        private int max;

        private int min;

        private boolean isBST;

        public Info(int size, int max, int min ,boolean isBST){
            this.size = size;
            this.max = max;
            this.min = min;
            this.isBST = isBST;

        }
    }


    public static int maxSubBSTSize2(Node head){
        Info info = process(head);
        return info.size;

    }

    private static Info process(Node head) {
        if (head == null){
            return new Info(0,0,0,true);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int max = Math.max (Math.max(head.val,left.max),right.max );
        int min = Math.min(Math.min(head.val,left.min),right.min);
        boolean isBst = false;
        int maxsize = 0;
        if (left.isBST && right.isBST && left.max < head.val && head.val < right.min){
            isBst = true;
            maxsize =  left.size + right.size +1;
        }
        maxsize  = Math.max(left.size ,right.size);

        return new Info(maxsize,max,min,isBst);






    }

}
