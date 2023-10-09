package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Sets;

import java.util.*;

public class DFS {

    public static void dfs(Node node){
        if (node == null){
            return ;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> sets  = new HashSet<>();
        stack.push(node);
        sets.add(node);
        System.out.println(node.v);
        while (!stack.isEmpty()){
            Node pop = stack.pop();
            for (Node temp :
                    pop.nexts) {
                if (!sets.contains(temp)){
                    stack.push(temp);
                    sets.add(temp);
                    System.out.println(temp.v);
                }

            }
        }

    }






    public class Node {
        public int v;
        public List<Node> nexts;

        public Node(int v){
            this.v = v;
            nexts = new ArrayList<>();
        }


    }
}
