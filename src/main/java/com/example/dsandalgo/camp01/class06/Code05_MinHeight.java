package com.example.dsandalgo.camp01.class06;

/**
 * 给定一棵二叉树的头节点head
 * 求以head为头的树中，最小深度是多少？
 *
 *  使用morris遍历求树的最小深度
 *
 *
 *
 */
public class Code05_MinHeight {

    public static class Node{
        public int val;
        Node left;
        Node right;
        public Node(int val){
            this.val = val;
        }

    }

    /**
     * 递归套路求解
     * @param head
     * @return
     */
    public static int minHeight1(Node head){
        if (head == null){
            return 0;
        }
        return p(head);
    }
    private static int p(Node head) {
        if (head.left == null  && head.right == null){
            return 1;
        }
        int pLeft = Integer.MAX_VALUE;
        if (head.left != null){
            pLeft = p(head.left);
        }
        int pRight = Integer.MAX_VALUE;
        if (head.right != null){
            pRight = p(head.right);
        }
        return Math.min(pLeft,pRight)+1;
    }


    /**
     * 使用morris遍历改写
     * @param head
     * @return
     */
    public static int minHeight2(Node head){
        if (head == null){
            return 0;
        }

        Node cur = head;
        Node mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;

        while (cur != null){
            mostRight =  cur.left;
            if (mostRight != null){
                int leftHeight = 1;
                while (mostRight.right != null && mostRight.right != cur){
                    leftHeight ++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null){
                    curLevel ++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }else{
                    if (mostRight.left == null){
                        minHeight = Math.min(minHeight,curLevel);
                    }
                    curLevel -= leftHeight;
                }
            }else{
                curLevel ++;
            }
            cur = cur.right;


        }


        int finalRight = 1;
        cur = head;
        while (cur.right != null){
            finalRight ++;
            cur = cur.right;
        }
        if (cur.right == null && cur.left == null){
            return Math.min(minHeight,finalRight);
        }

        return minHeight;


    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int treeLevel = 7;
        int nodeMaxValue = 5;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(treeLevel, nodeMaxValue);
            int ans1 = minHeight1(head);
            int ans2 = minHeight2(head);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");

    }

}
