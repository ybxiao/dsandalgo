package com.example.dsandalgo.foundationclass.class07;

import java.util.*;

/**
 * 求树的最大宽度
 * 其实是树的层次遍历
 * 关键是记录每一层的开始或者结束，统计每一层的节点数目
 */
public class Code06_TreeMaxWith {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            value = value;
        }

        public static int maxWidthUseMap(Node head) {
            int max = 0;
            if (head != null) {
                Queue<Node> queue = new LinkedList<>();
                Map<Node, Integer> map = new HashMap<>();
                map.put(head, 1);
                queue.add(head);
                int curLevel = 1;
                int curNodes = 0;

                while (!queue.isEmpty()) {
                    Node poll = queue.poll();

                    if (poll.left != null) {
                        queue.add(poll.left);
                        map.put(poll.left, map.get(poll) + 1);
                    }
                    if (poll.right != null) {
                        queue.add(poll.right);
                        map.put(poll.right, map.get(poll) + 1);
                    }

                    if (curLevel == map.get(poll)) {
                        curNodes++;
                    } else {
                        max = Math.max(curNodes, max);
                        curLevel = map.get(poll);
                        curNodes = 1;

                    }


                }
                max = Math.max(curNodes, max);
            }

            return max;


        }

        public static int maxWidthNoMap(Node head) {
            if (head == null) {
                return 0;
            }
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            Node curEnd = head;
            Node nextEnd = null;
            int curNodes = 0;
            int max = 0;
            while (!queue.isEmpty()) {
                Node poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                    nextEnd = poll.left;
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                    nextEnd = poll.right;
                }
                curNodes++;

                if (poll == curEnd) {

                    curEnd = nextEnd;
                    max = Math.max(curNodes, max);
                    curNodes = 0;
                }


            }

            return max;


        }
    }


}
