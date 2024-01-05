package com.example.dsandalgo.aaaa.basic;

public class MaxXOR {

    //O(N^2)的时间复杂度
    public static int maxXorSubarray1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] eOr = new int[arr.length];
        eOr[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            eOr[i] ^= eOr[i - 1];
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                max = Math.max(max, j == 0 ? eOr[i] : eOr[i] ^ eOr[j - 1]);
            }
        }

        return max;

    }

    public static class Node {
        public Node[] nexts = new Node[2];
    }


    public static class NumTrie {
        public Node root;

        public NumTrie() {
            root = new Node();
        }

        public void add(int num) {
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                // 提取num第i位置的数
                int path = num >> i & 1;
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }

        }

        public int maxXOr(int num) {
            int ans = 0;
            Node cur = root;
            for (int i = 31; i >= 0; i--) {
                // 提取num第i位置的数
                int path = num >> i & 1;
                int best = i == 31 ? path : path ^ 1;
                //实际的best
                best = cur.nexts[best] == null ? best ^ 1 : best;
                ans |= (path ^ best) << i;
                cur = cur.nexts[best];

            }
            return ans;


        }

    }

    //O(N)
    //
    public static int maxXorSubarray2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        NumTrie numTrie = new NumTrie();
        //表示一个数都不选的情况
        numTrie.add(0);
        int ans = 0;
        int xOr = 0;
        for (int i = 0; i < arr.length; i++) {
            xOr ^= arr[i];
            ans = Math.max(ans, numTrie.maxXOr(arr[i]));
            numTrie.add(xOr);

        }

        return ans;

    }


}
