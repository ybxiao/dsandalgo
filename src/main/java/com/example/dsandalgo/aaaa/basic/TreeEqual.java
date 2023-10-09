package com.example.dsandalgo.aaaa.basic;


import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import static com.example.dsandalgo.camp01.class04.Code02_TreeEqual.isEqual;

/**
 * 给定两棵二叉树的头节点head1和head2，
 * 返回head1中是否有某个子树的结构和head2完全一样
 */
public class TreeEqual {


    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int value) {
            val = value;
        }
    }

    /**
     * @param big
     * @param small
     * @return big中是否有某个子树的结构small完全一样
     * kmp算法的应用
     */
    public static boolean isSubTree(TreeNode big, TreeNode small) {
        //1、入参校验
        if (big == null || small == null) {
            return false;
        }
        //2、 对两棵树进行前序遍历生成数组
        List<String> sourceString = preSerial(big);
        List<String> matchString = preSerial(small);
        //3、使用kmp算法
        return indexOf(sourceString, matchString) != -1;

    }


    public static int indexOf(List<String> source, List<String> match) {
        if (source == null || match == null || source.size() < match.size()) {
            return 0;
        }

        int[] nextArray = getNextArray(match);
        int i = 0;
        int j = 0;
        while (i < source.size() && j < match.size()) {
            if (isEqual(source.get(i), match.get(j))) {
                i++;
                j++;
            } else if (nextArray[j] >= 0) {
                j = nextArray[j];
            } else {
                i++;
            }
        }

        return j == match.size() ? i - j : -1;

    }

    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else {
            if (a == null || b == null) {
                return false;
            } else {
                return a.equals(b);
            }

        }
    }

    //生成匹配串的next数组
    public static int[] getNextArray(List<String> match) {
        int n = match.size();
        int[] nextArray = new int[n];
        nextArray[0] = -1;
        nextArray[1] = 0;
        int cn = 0;

        for (int index = 2; index < n; index++) {
            if (isEqual(match.get(index - 1), match.get(cn))) {
                nextArray[index++] = ++cn;
            } else if (cn > 0) {
                cn = nextArray[cn];
            } else {
                nextArray[index++] = 0;
            }

        }
        return nextArray;
    }


    private static List<String> preSerial(TreeNode node) {
        ArrayList<String> ans = Lists.newArrayList();
        pre(node, ans);
        return ans;
    }

    private static void pre(TreeNode node, ArrayList<String> ans) {
        if (node == null) {
            ans.add("#");
        } else {
            ans.add(String.valueOf(node.val));
            pre(node.left, ans);
            pre(node.right, ans);
        }
    }

    public static void main(String[] args) {
        TreeNode head1 = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        head1.left = node1;
        head1.right = node2;
        node1.left = node3;
        node1.right = node4;
        node4.left = node5;

        TreeNode head2 = new TreeNode(4);
        TreeNode node21 = new TreeNode(1);
        TreeNode node22 = new TreeNode(2);
        head2.left = node21;
        head2.right = node22;
        isSubTree(head1, head2);
    }


}
