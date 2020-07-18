package com.example.dsandalgo.class07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 层次遍历
 *
 *
 */
public class Code03_LevelTravelsalBT {

    public static class Node{
        private int value;
        private Node left;
        private Node right;
        public Node(int value){
            value = value;
        }

    }

    public static void level(Node head){
        if (head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left != null){
                queue.add(poll.left);
            }
            if (poll.right!=null){
                queue.add(poll.right);
            }

        }


    }


}
