package com.example.dsandalgo.foundationclass.class07;


import java.util.Stack;

/**
 * 非递归实现先序、中序、后续遍历二叉树
 * 所有的递归都可以改成非递归。自己设计栈操作而已。
 */
public class Code02_UnRecursiveTravesalBT {

    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int value){
            value = value;
        }

    }

    public static void pre(Node head){
        if (head == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            System.out.println(pop.value);
            if (pop.right != null){
                stack.push(pop.right);
            }
            if (pop.left != null){
                stack.push(pop.left);
            }
        }
    }

    public static void in(Node head){

        Stack<Node> stack = new Stack<>();
        if (head != null){

            while (!stack.isEmpty() || head != null){

                if (head != null){
                    stack.push(head);
                    head = head.left;
                }else{
                    Node pop = stack.pop();
                    System.out.println(pop.value);
                    // 相当于我每弹出一个节点，就把当前的指针指向它的右孩子。
                    // 如果然后在右子树不为空，在右子树上重复之前放左节点的步骤
                    head = pop.right;

                }
            }
            System.out.println();

        }




    }

    /**
     * 根据先序遍历 倒一下
     * 用两个栈
     *
     * @param head
     */
    public static void pos1(Node head){
        if (head != null){
            Stack<Node> stack1  =  new Stack<>();
            Stack<Node> stack2  =  new Stack<>();

            stack1.push(head);
            while (!stack1.isEmpty()){
                Node pop = stack1.pop();
                stack2.push(pop );
                if (pop.left !=null){
                    stack1.push(pop.left);
                }
                if (pop.right != null){
                    stack1.push(pop.right);
                }

            }

            while (!stack2.isEmpty()){
                System.out.println(stack2.pop().value);
            }


        }



    }

    /**
     * 炫技版
     * 吊炸天
     * @param head
     */
    public static void pos2(Node head){

        if (head !=null){

            Stack<Node> stack = new Stack<>();
            stack.push(head);

            Node c =  null;
            while (!stack.isEmpty()){
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right){
                    stack.push(c.left);
                }else if (c.right != null && head != c.right){
                    stack.push(c.right);
                }else{
                    Node pop = stack.pop();
                    System.out.println(pop.value);
                    head = pop;
                }

            }





        }

    }





}
