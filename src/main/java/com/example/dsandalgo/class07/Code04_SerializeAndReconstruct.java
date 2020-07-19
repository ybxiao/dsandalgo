package com.example.dsandalgo.class07;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 二叉树的序列化和重建
 * 空位补null
 */
public class Code04_SerializeAndReconstruct {

    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int value){
            value = value;
        }
    }


    public static Queue preSerial(Node head){
        Queue<String>  ans = new LinkedList<>();
        preT(head,ans);
        return ans;
    }

    private static void preT(Node head, Queue<String> ans) {
        if (head == null){
            ans.add(null);
        }
        else{
            ans.add(String.valueOf(head.value));
            preT(head.left,ans);
            preT(head.right,ans);
        }

    }

    private static Node preB(Queue<String> ans){
        String poll = ans.poll();

        Node node = generateNode(poll);
        if (node != null) {

            node.left =  preB(ans);
            node.right = preB(ans);
        }

        return node;

    }

    public static Queue<String> inSerial(Node head){
        Queue<String> ans =  new LinkedList<>();
        inT(head,ans);
        return ans;
    }

    private static void inT(Node head, Queue<String> ans) {
        String poll = ans.poll();
        if (head == null){
            ans.add(null);
        }else{
            inT(head.left,ans);
            ans.add(String.valueOf(head.value));
            inT(head.right,ans);
        }




    }

    public static  Stack<String>  posS(Node head){
        Stack<String> ans = new Stack<>();

        posT(head ,ans);

        return ans;

    }

    private static void posT(Node head,  Stack<String> ans) {
        if (head == null){
            ans.add(null);
        }else{
            posT(head.left,ans);
            posT(head.right,ans);
            ans.add(String.valueOf(head.value));
        }
    }

    public static Node posB(Stack<String> ans){
        String pop = ans.pop();
        Node node = generateNode(pop);
        if (node != null){
            node.right = posB(ans);
            node.left = posB(ans);
        }

        return node;
    }


    public static Queue<String> levelSerial(Node head){

        Queue<String> ans = new LinkedList<>();
        if (head == null){
            ans.add(null);
            return ans;
        }else{
            Queue<Node> queue =  new LinkedList<>();
            queue.add(head);
            ans.add(String.valueOf(head.value));
            while (!queue.isEmpty()) {
                Node poll = queue.poll();

                if (poll.left != null){
                    queue.add(poll.left);
                    ans.add(String.valueOf(poll.left.value));

                }else{
                    ans.add(null);
                }

                if (poll.right != null){
                    queue.add(poll.right);
                    ans.add(String.valueOf(poll.right.value));

                }else{
                    ans.add(null);
                }


                }

            }

        return ans;
    }

    public static Node buildByLevelSerial(Queue<String>  ans){
        if (ans == null || ans.size() == 0){
            return null;
        }
        String poll = ans.poll();
        Node node = generateNode(poll);
        Queue<Node>  queue =  new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            Node poll1 = queue.poll();
            poll1.left = generateNode(ans.poll());
            poll1.right = generateNode(ans.poll());
            if (poll1.left != null){ queue.add(poll1.left);}
            if (poll1.right != null){
                queue.add(poll1.right);
            }


        }

        return node;
    }








    private static Node generateNode(String poll) {
        if (poll == null){
            return null;
        }

        return new Node(Integer.valueOf(poll));
    }

}
