package com.example.dsandalgo.aaaa.basic;

import java.util.*;

/**
 * 给定三个参数，二叉树的头节点head，树上某个节点target，正数K。
 * 从target开始，可以向上走或者向下走，返回与target的距离是K的所有节点
 */
public class DistanceKNodesCopy2023 {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            value = val;
        }
    }

    public List<Node> distanceK(Node head, Node target, int k) {
        Map<Node, Node> parents = new HashMap<>();
        parents.put(head, null);
        createParentsMap(head, parents);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(target);
        Set<Node> sets = new HashSet<>();
        sets.add(target);
        List<Node> ans = new ArrayList<>();
        int curLevel = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (--size > 0) {
                Node cur = queue.poll();
                if (curLevel == k) {
                    ans.add(cur);
                }
                if (cur.left != null && !sets.contains(cur.left)) {
                    sets.add(cur.left);
                    queue.add(cur.left);
                }
                if (cur.right != null && !sets.contains(cur.right)) {
                    sets.add(cur.right);
                    queue.add(cur.right);
                }
                if (parents.containsKey(cur)) {
                    sets.add(parents.get(cur));
                    queue.add(parents.get(cur));
                }

            }
            curLevel++;
            if (curLevel > k) {
                break;
            }

        }

        return ans;
    }

    private void createParentsMap(Node head, Map<Node, Node> parents) {
        if (head == null) {
            return;
        }
        if (head.left != null) {
            parents.put(head.left, head);
            createParentsMap(head.left, parents);
        }
        if (head.right != null) {
            parents.put(head.right, head);
            createParentsMap(head.right, parents);
        }

    }


}
