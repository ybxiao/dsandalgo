package com.example.dsandalgo.foundationclass.class08;

/**
 * 判断二叉树 是否平衡二叉树
 */
public class Code01_IsBalanced {

    public static class Node{
        private int v;
        private Node left;
        private Node right;
        public Node(int val){
            v = val;
        }

    }

    public static boolean  isBalacne2(Node head){


        return  process2(head).isBalanced;

    }

    private static Info process2(Node head) {
        if (head  == null){
            return new Info(true, 0);
        }
        Info leftInfo  = process2(head.left);
        Info rightInfo  = process2(head.right);

        boolean isbalanced = false;

        if ((leftInfo.height - rightInfo.height <=1) && leftInfo.isBalanced && rightInfo.isBalanced){
            isbalanced = true;
        }
        int height = Math.max(leftInfo.height,rightInfo.height) +1;
        return new Info(isbalanced,height);


    }

    public static class Info{
        private boolean isBalanced;
        private int height;

        public Info(boolean isBalanced, int height){
            this.isBalanced = isBalanced;
            this.height = height;
        }

    }


}
