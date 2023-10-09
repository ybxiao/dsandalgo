package com.example.dsandalgo.aaaa.basic;


import java.util.*;

public class BFS {
    public static void bfs(Node node){
        if (node  == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> sets = new HashSet<>();
        sets.add(node);
        queue.add(node);
        while (!queue.isEmpty()){
            Node pop = queue.poll();
            System.out.println(pop.v);
            for (Node temp: pop.nexts){
                if (!sets.contains(temp)){
                    queue.add(temp);
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
