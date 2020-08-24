package com.example.dsandalgo.foundationclass.class08;


import java.util.*;

/**
 * 给定一棵二叉树的头节点head，任何两个节点之间都存在距离，
 * 返回整棵二叉树的最大距离
 */
public class Code08_MaxDistance {

    public static int masDistsance1(Node head){
        if (head == null){
            return 0;
        }
        List<Node> arr =  new ArrayList<Node>();
        preOrderList(head,arr);
        Map<Node,Node> parentMap = getParentMap(head);
        int max = 0;
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i; j < arr.size(); j++) {
                max = Math.max(max ,distance(parentMap,arr.get(i),arr.get(j)));
            }
        }

        return max;
    }

    private static int distance(Map<Node, Node> parentMap, Node node, Node node1) {
        Node cur = node;
        Set<Node> set = new HashSet<>();
        while (parentMap.get(cur) !=null){
            cur = parentMap.get(cur);
            set.add(cur);
        }

        cur =  node1;
        while (! set.contains(cur)){
            cur = parentMap.get(cur);
        }

        Node lowesetAncestor = cur;
        int distance1 = 1;
        int distance2 = 1;
        cur = node;
        while (cur != lowesetAncestor){
            cur =  parentMap.get(cur);
            distance1++;
        }
        cur = node1;
        while (cur != lowesetAncestor){
            cur = parentMap.get(cur);
            distance2++;
        }
        return distance1+distance2 -1;





    }

    //找到所有节点的头结点 放入parentMap 里面
    private static Map<Node, Node> getParentMap(Node head) {
        Map<Node,Node> map = new HashMap<>();
        if (head  != null){
            map.put(head,null);
        }
        filterMap(head,map);
        return map;
    }

    /**
     * 遍历添充parentMap
     * @param head
     * @param map
     */
    private static void filterMap(Node head, Map<Node, Node> map) {
        if (head.left != null){
            map.put(head.left ,head);
            filterMap(head.left,map);
        }
        if (head.right != null){
            map.put(head.right ,head);
            filterMap(head.right,map);
        }
    }

    private static void preOrderList(Node head, List<Node> arr) {
        if (head == null){
            return;
        }
        arr.add(head);
        preOrderList(head.left,arr);
        preOrderList(head.right,arr);
    }


    public static class Info{
        private int maxdistance;
        private int height;

        public Info(int maxdistance, int height){
            this.maxdistance = maxdistance;
            this.height = height;
        }
    }

    public static int  maxDistance2(Node head){
        if (head == null){
            return 0;
        }
        Info info = process(head);

        return info.maxdistance;
    }

    private static Info process(Node head) {
        if (head == null){
            return new Info(0,0);
        }
        Info l = process(head.left);
        Info r = process(head.right);
        int h =  Math.max(l.height,r.height) +1;
        int maxDistance  = Math.max(Math.max(l.maxdistance,r.maxdistance),h);
        return new Info(maxDistance,h);
    }
}
