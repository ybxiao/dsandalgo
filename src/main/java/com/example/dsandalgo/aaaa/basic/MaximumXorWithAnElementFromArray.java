package com.example.dsandalgo.aaaa.basic;

/**
 * https://leetcode.com/problems/maximum-xor-with-an-element-from-array/
 */
public class MaximumXorWithAnElementFromArray {

    public int[] maximizeXor(int[] nums, int[][] queries) {
        int N = nums.length;
        //Node head = new Node();
        return new int[]{};

    }


    public class Node {
        public int min;
        public Node[] nexts;

        public Node() {
            min = Integer.MAX_VALUE;
            nexts = new Node[2];
        }
    }

    public class TrieNum {
        public Node head;

        public TrieNum() {
            head = new Node();
        }

        public void addNum(int num) {
            Node cur = head;
            cur.min = Math.min(num, cur.min);
            for (int i = 30; i >= 0; i--) {
                int path = num >> i & 1;
                cur.nexts[path] = (cur.nexts[path] == null) ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
                cur.min = Math.min(num, cur.min);
            }
        }

        public int maxXorWithXBehindM(int x, int m) {
            if (head.min > m){
                return 0;
            }
            int ans = 0;
            Node cur = head;
            for (int i = 30; i >= 0; i--) {
                int path = x >> i & 1;
                int best = path ^ 1;
                best ^= (cur.nexts[best] == null || cur.nexts[best].min >= x) ? 1 : 0;
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];

            }

            return ans;
        }


    }
}
