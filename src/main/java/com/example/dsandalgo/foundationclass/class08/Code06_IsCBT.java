package com.example.dsandalgo.foundationclass.class08;

import java.util.LinkedList;
import java.util.Queue;

public class Code06_IsCBT {

    public static class Node{
        private int val;
        private Node left;
        private Node right;

        public Node(int val){
            this.val = val;
        }


    }

    public boolean isCBT1(Node head){
        if (head == null){
            return false;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        boolean flag =  false;

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            Node l = poll.left;
            Node r = poll.right;

            if (
                    (flag && (l != null || r !=null)) || (l==null && r !=null)

            ){
                return false;
            }
            if (l != null){
                queue.add(l);
            }
            if (r != null){
                queue.add(r);
            }
            if (l ==null || r == null){
                flag = true;
            }

        }
        return true;
    }


    public static class Info{
        private boolean isFull;
        private boolean isCBT;
        private int height;


        public Info(boolean isFull,boolean isCBT,int height){
            this.isFull  =isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }



    public static boolean isCBT2(Node head){
        if (head == null){
            return true;
        }
        Info  info = process(head);
        return info.isCBT;
    }

    private static Info process(Node head) {
        if (head == null){
            return new Info(true,true,0);
        }
        Info left = process(head.left);
        Info right = process(head.right);
        int h = Math.max(left.height ,right.height) +1;
        boolean isFull = left.isFull
                &&
                right.isFull
                && left.height == right.height;
        boolean isCBT = false;
        if (isFull){
            isCBT = true;
        }
        if (left.height == right.height){
            if (left.isFull && right.isCBT){ isCBT = true ;}
        }
        if (left.height  - right.height ==1){
            if (left.isCBT && right.isFull){
                isCBT = true;
            }
        }
        return new Info(isFull,isCBT,h);
    }
}
