package com.example.dsandalgo.foundationclass.class08;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *给定一棵二叉树的头节点head，和另外两个节点a和b。
 * 返回a和b的最低公共祖先
 *
 * 1 暴力求解
 * 2 套路求解
 *
 */
public class Code07_lowestAncestor {

    /**
     * 暴力解法
     * @param head
     * @param o1
     * @param o2
     * @return
     */
    public Node lowestAncestor1(Node head, Node o1,Node o2 ){
        if (head == null){
            return null ;
        }

        Map<Node,Node> parentMap = new HashMap<>();
        parentMap.put(head,null);
        filterParentMap(head,parentMap);
        Node cur = o1;
        Set<Node> set = new HashSet<>();
        while (parentMap.get(cur) != null){
            cur = parentMap.get(cur);
            set.add(cur);
        }

        cur = o2;
        while (!set.contains(cur)){
            cur = parentMap.get(cur);
        }
        return cur;


    }

    private void filterParentMap(Node head, Map<Node, Node> parentMap) {
        if (head.left != null){
            parentMap.put(head.left,head);
            filterParentMap(head.left,parentMap);
        }
        if (head.right != null)
        {
            parentMap.put(head.right,head);
            filterParentMap(head.right,parentMap);
        }

    }

    /**
     * 递归信息封装
     * 左右子树都这么调用
     *
     */
    public static class Info{

        //最低公共祖先
        private Node ans;
        //在子树上是否发现o1 节点
        private boolean findo1;
        //在子树上是否发现o2节点
        private boolean findo2;

        public Info(Node ans ,boolean findo1 ,boolean findo2){
            this.ans = ans;

            this.findo1 = findo1;
            this.findo2 = findo2;
        }

    }

    public Node lowestAncestor(Node head,Node o1,Node o2){
        if (head == null){
            return null;
        }

        Info info = process(head,o1,o2);
        return info.ans;

    }

    private Info process(Node head,Node o1,Node o2) {
        if (head ==null){
            return new Info(null,false,false);
        }
        Info left = process(head.left, o1,o2);
        Info right = process(head.right,o1,o2);
        Node ans = null;
        if ((left.findo1 && right.findo2) || (left.findo2 && right.findo1)){
            ans = head;
        }
        if (left.findo2 && left.findo1){
            ans  = left.ans;
        }
        if (right.findo1 && right.findo2){
            ans = left.ans;
        }
        boolean findo1 = left.findo1 || right.findo1 || head == o1;
        boolean findo2 = left.findo1 || !right.findo1 || head == o2;
        return new Info(ans,findo1 ,findo2);


    }


}
