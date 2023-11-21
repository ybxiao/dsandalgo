package com.example.dsandalgo.foundationclass.class07;

import java.util.*;

/**
 * 求树的最大宽度
 * 其实是树的层次遍历
 * 关键是记录每一层的开始或者结束，统计每一层的节点数目
 */
public class Code06_TreeMaxWidthCopy2023 {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            value = value;
        }

        public static int treeMaxWidthNoMap(Node head) {
            if (head == null) {
                return 0;
            }
            Queue<Node> queue = new LinkedList<>();
            //标记已经遍历过的元素
            Set<Node> sets = new HashSet<>();
            queue.add(head);
            Node curEnd = head;
            Node nextEnd = null;
            int max = 0;
            int curLevelNodeNum = 0;
            while (!queue.isEmpty()) {
                Node cur = queue.poll();
                sets.add(cur);
                if (!sets.contains(cur)) {
                    if (cur.left != null) {
                        queue.add(cur.left);
                        nextEnd = cur.left;
                    }
                    if (cur.right != null) {
                        queue.add(cur.right);
                        nextEnd = cur.right;
                    }
                }
                curLevelNodeNum++;
                if (cur == curEnd) {
                    max = Math.max(curLevelNodeNum, max);
                    curEnd = nextEnd;
                    curLevelNodeNum = 0;
                }
            }
            return max;

        }
    }


}
