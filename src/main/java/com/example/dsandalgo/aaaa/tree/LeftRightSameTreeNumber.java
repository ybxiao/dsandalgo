package com.example.dsandalgo.aaaa.tree;


//如果一个节点X，它左树结构和右树结构完全一样，那么我们说以X为头的子树是相等子树

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 给定一棵二叉树的头节点head，返回head整棵树上有多少棵相等子树
// 二叉树的递归套路：
//
public class LeftRightSameTreeNumber {

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int sameTreeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return sameTreeNum(head.left) + sameTreeNum(head.right) + (same(head.left, head.right) ? 1 : 0);
    }

    private static boolean same(Node left, Node right) {
        if (left == null ^ right == null) {
            return false;
        }
        if (left == null && right == null) {
            return true;
        }
        return left.value == right.value && same(left.left, right.left) && same(left.right, right.right);

    }

    public static int subTreeNum2(Node head) {
        if (head == null) {
            return 0;
        }
        String algorithm = "SHA-256";

        Hash hash = new Hash(algorithm);
        return process(head, hash).ans;

    }

    private static Info process(Node head, Hash hash) {
        if (hash == null) {
            return new Info(0, hash.hashCode("#,"));
        }
        Info leftInfo = process(head.left, hash);
        Info rightInfo = process(head.right, hash);
        int ans = leftInfo.ans + rightInfo.ans;
        String str = hash.hashCode(leftInfo.val + "," + head.value + "," + rightInfo.val);
        if (leftInfo.val.equals(rightInfo.val)) {
            ans++;
        }
        return new Info(ans, str);
    }

    public static class Info {
        public int ans;
        public String val;

        public Info(int a, String v) {
            ans = a;
            val = v;
        }
    }

    public static class Hash {
        private MessageDigest hash;

        public Hash(String algorithm) {
            try {
                hash = MessageDigest.getInstance(algorithm);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        public String hashCode(String input) {
            return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
        }
    }

}

