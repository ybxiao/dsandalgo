package com.example.dsandalgo.aaaa.basic;

import java.util.*;

/**
 * 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。
 * 从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
 */
public class DistanceKNodes {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }

    }

    public static List<Node> distanceKNodes(Node head, Node target, int k) {
        Map<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        creatParentMap(head, parentMap);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(target);
        Set<Node> sets = new HashSet<>();
        List<Node> ans = new ArrayList<>();
        int currentLevel = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Node poll = queue.poll();
                sets.add(poll);

                if (currentLevel == k) {
                    ans.add(poll);
                }
                if (poll.left != null && !sets.contains(poll.left)) {
                    sets.add(poll.left);
                    queue.offer(poll.left);
                }
                if (poll.right != null && !sets.contains(poll.right)) {
                    sets.add(poll.right);
                    queue.offer(poll.right);
                }
                if (parentMap.containsKey(poll) && !sets.contains(parentMap.get(poll))) {
                    queue.offer(parentMap.get(poll));
                    sets.add(parentMap.get(poll));
                }
            }
            currentLevel++;
            if (currentLevel > k) {
                break;
            }


        }

        return ans;


    }

    private static void creatParentMap(Node cur, Map<Node, Node> parentMap) {
        if (cur == null) {
            return;
        }
        if (cur.left != null) {
            parentMap.put(cur.left, cur);
            creatParentMap(cur.left, parentMap);
        }
        if (cur.right != null) {
            parentMap.put(cur.right, cur);
            creatParentMap(cur.right, parentMap);
        }
    }


}
