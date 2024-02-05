package com.example.dsandalgo.aaaa.basic;

import java.util.*;

//已更正
public class DFSCopy2023 {

    /**
     * 实现dfs深度优先遍历，使用栈先进后出的特性
     * @param node
     */
    public static void dfs(Node node) {
        if (node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        //sets 容器来纪录当前节点是否被访问过
        Set<Node> sets = new HashSet<>();
        stack.push(node);
        sets.add(node);
        //进栈时，对元素进行操作
        System.out.println(node.v);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node temp :
                    cur.nexts) {
                while (!sets.contains(temp)){
                    //此时需要把当前节点也放入进去，以便后续进行第二条路的遍历
                    stack.push(cur);
                    stack.push(node);
                    sets.add(node);
                    //进栈时，对元素进行操作
                    System.out.println(node.v);
                    //找到一条继续往下运行的路就跳出当前循环。
                    break;
                }
            }

        }



    }


    public class Node {
        public int v;
        public List<Node> nexts;

        public Node(int v) {
            this.v = v;
            nexts = new ArrayList<>();
        }


    }
}
