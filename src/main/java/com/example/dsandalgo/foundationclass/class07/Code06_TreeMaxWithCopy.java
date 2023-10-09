package com.example.dsandalgo.foundationclass.class07;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 求树的最大宽度
 * 其实是树的层次遍历
 * 关键是记录每一层的开始或者结束，统计每一层的节点数目
 */
public class Code06_TreeMaxWithCopy {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            value = value;
        }

    }

    public static int maxWidthUsingMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> nodeLevelMap = new HashMap<>();
        queue.add(head);
        int max = 0;
        int currentLevel = 1;
        int currentLevelNode = 0;
        nodeLevelMap.put(head, currentLevel);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (poll.left != null) {
                queue.add(poll.left);
                nodeLevelMap.put(poll.left, nodeLevelMap.get(poll) + 1);
            }

            if (poll.right != null) {
                queue.add(poll.right);
                nodeLevelMap.put(poll.right, nodeLevelMap.get(poll) + 1);
            }
            if (currentLevel == nodeLevelMap.get(poll)) {
                currentLevelNode++;
            } else {
                max = Math.max(max, currentLevelNode);
                currentLevel = nodeLevelMap.get(poll);
                currentLevelNode = 1;
            }
        }
        return max;

    }

    public static int maxWidthNoMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        int max = 0;
        Node currentEnd = head;
        Node nextEnd = null;
        int currentNodeNum = 0;
        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            currentNodeNum++;
            if (currentEnd == poll) {
                currentEnd = nextEnd;
                max = Math.max(max, currentNodeNum);
                currentNodeNum = 0;
            }


        }
        return max;


    }

}
