package com.example.dsandalgo.foundationclass.class08;

import java.util.ArrayList;
import java.util.List;

public class Code05_MaxSubBSTHead {
    public static class Node {

        private int val;

        private Node right;
        private Node left;

        public Node(int val){
            this.val =  val;
        }

    }

    /**
     * 判断一棵树是不是BST，如果是返回节点个数
     * 如果不是返回0
     * @param head
     * @return
     */
    public static int getBSTMaxSize(Node head){
        if (head == null){
            return 0;
        }
        List<Node> arr =  new ArrayList<>();
        inorder(head,arr);
        for (int i = 0; i < arr.size() -1; i ++) {
            if (arr.get(i).val> arr.get(i+1).val){
                return  0;
            }
        }

        return arr.size();

    }

    private static void inorder(Node head, List<Node> arr) {
        if (head == null){
            return;
        }
        inorder(head.left,arr);
        arr.add(head);
        inorder(head.right,arr);
    }

    public static Node maxSubBSTHead1(Node head){
        if (head == null){
            return null;
        }
        int bstMaxSize = getBSTMaxSize(head);
        if (bstMaxSize > 0){
            return head;
        }
        Node leftNode   = maxSubBSTHead1(head.left);
        Node rightNode =  maxSubBSTHead1(head.right);

        return getBSTMaxSize(leftNode) >= getBSTMaxSize(rightNode) ? leftNode : rightNode;


    }


    public static class Info{
        private int max;
        private int min;
        private Node maxHead;
        private int maxSize;

        public Info(int max,int min ,Node maxHead,int maxSize){
            this.max = max;
            this.min = min;
            this.maxHead = maxHead;
            this.maxSize =  maxSize;
        }
    }

    public Node maxSubBSTHead2(Node head){
        if (head == null){
            return null;
        }
        Info  info = process(head);

        return info.maxHead;
    }

    private Info process(Node head) {
        if (head == null){
            return new Info(0,0,null,0);
        }
        Info left = process(head.left);
        Info right = process(head.right);

        int maxSize =Math.max(left.maxSize ,right.maxSize);
        int max = Math.max((Math.max(left.max, right.max)),head.val);
        int min = Math.min((Math.min(left.min, right.min)),head.val);
        Node maxHead = null;
        if (left.maxHead  ==  head.left && right.maxHead == head.right){
            if (left.max <head.val && head.val < right.min){
                maxSize = maxSize +1;
                maxHead = head;
            }
        }else{
            maxHead = left.maxSize >= right.maxSize ? head.left:head.right;

        }

        return new Info(max,min,maxHead,maxSize);
    }

}
