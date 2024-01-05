package com.example.dsandalgo.aaaa.basic;

import com.example.dsandalgo.juc.T;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */
public class ConstructBinarySearchTreeFromPreorderTraversal {

    public static class TreeNode {
        private int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.val = value;
        }

    }

    public static TreeNode bst(int[] preOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }

        int n = preOrder.length;
        return process(preOrder, 0, n - 1);


    }

    /**
     * @param preOrder
     * @param L
     * @param R
     * @return prOrder[L...R]范围上生成 bst
     */
    private static TreeNode process(int[] preOrder, int L, int R) {
        if (L > R) {
            return null;
        }
        int firstBigger = L + 1;
        for (; firstBigger <= R; firstBigger++) {
            if (preOrder[firstBigger] > preOrder[L]) {
                break;
            }
        }
        TreeNode root = new TreeNode(preOrder[L]);
        root.left = process(preOrder, L + 1, firstBigger - 1);
        root.right = process(preOrder, firstBigger, R);
        return root;
    }
}
