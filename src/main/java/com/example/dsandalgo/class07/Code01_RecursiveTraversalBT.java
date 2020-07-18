package com.example.dsandalgo.class07;

/**
 * 递归遍历二叉树
 *
 * 1 递归序
 * 2 前序遍历
 * 3 中序遍历
 * 4 后序遍历
 */
public class Code01_RecursiveTraversalBT {

    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int value){
            value = value;
        }

    }


    public static void f(Node head){

        if (head == null){
            return;
        }
        //递归序第一次到达
        f(head.left);
        //递归序第二次到达
        f(head.right);
        //递归序第三次到达

    }
    //xian
    public static void pre(Node head){
        if (head == null){
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);


    }


    public static void in(Node head){
        if (head == null){
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }

    public static void post(Node head){
        if (head == null){
            return;
        }

        post(head.left);
        post(head.right);
        System.out.println(head.value);

    }

}
