package com.example.dsandalgo.camp01.class04;

import java.util.ArrayList;

/**
 * 给你两棵树的头结点，判断其中一颗树是否是另一个的子树
 *  给定两棵二叉树的头节点head1和head2
 * 想知道head1中是否有某个子树的结构和head2完全一样
 *
 */
public class Code02_TreeEqual {

    public static class Node{
        public Node left;
        public Node right;
        public int val;
        public Node(int val){
            this.val = val;
        }

    }

    //暴力递归解法
    //时间复杂度 O(M*N)
    //M N 分别是两棵树的节点个数
    public static boolean containsTree1(Node big ,Node small){
        if (small == null){
            return true;
        }
        if (big == null){
            return false;
        }

        if (isSameValueStructure(big,small)){
            return true;
        }

        return containsTree1(big.right,small) || containsTree1(big.left,small);






    }

    private static boolean isSameValueStructure(Node big, Node small) {
        if (big == null && small != null){
            return false;
        }
        if (big != null && small == null){
            return false;
        }
        if (big == null && small == null){
            return true;
        }
        if (big.val  != small.val){
            return false;
        }
        return isSameValueStructure(big.left,small.left) && isSameValueStructure(big.right,small.right);


    }


    public static boolean contailsTree2(Node big, Node small){
        if (small == null){
            return true;
        }
        if (big == null){
            return false;
        }
        ArrayList<String> b =  preSerial(big);
        ArrayList<String> s =  preSerial(small);

        String[] str =  new String[b.size()];
        for (int i = 0; i < b.size(); i++) {
            str[i]  = b.get(i);
        }
        String[] match = new String[s.size()];

        for (int i = 0; i < match.length; i++) {
            match[i] =  s.get(i);
        }

        return getIndexof(str,match) != -1;










    }

    private static ArrayList<String> preSerial(Node big) {
        ArrayList<String>  ans = new ArrayList<>();
        pre(big,ans);
        return ans;

    }

    private static void pre(Node big, ArrayList<String> ans) {
        if (big == null){
            ans.add(null);
        }else{
            ans.add(String.valueOf(big.val));
            pre(big.right,ans);
            pre(big.left,ans);
        }

    }

    private static int getIndexof(String[] str, String[] match) {
        if (str == null  || match == null || match.length < 1 || match.length > str.length){
            return -1;
        }
        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x <str.length && y < match.length){
            if (isEqual(str[x] , match[y])){
                x++;
                y++;
            }else if (next[y] > 0){
                y = next[y];
            }else{
                x++;
            }

        }
        return y == match.length ? x-y : -1;

    }

    private static int[] getNextArray(String[] match) {
        int[] next =  new int[match.length];
        if (match == null){
            return new int[]{-1};
        }
        next[0] = -1;
        next[1] = 0;
        int cur = 2;
        int cn = 0 ;
        while (cur < match.length){
            if (isEqual(match[cur-1],match[cn])){
                next[cur++] = ++cn;
            }else if (next[cn] > 0){
                cn = next[cn];
            }else{
                next[cur++] = 0;
            }

        }
        return next;



    }

    public static boolean isEqual(String a ,String b){
        if (a == null && b== null ){
            return true;
        }else {
            if (a == null || b == null ){
                return false;
            }else {
                return a.equals(b);
            }

        }
    }

}
