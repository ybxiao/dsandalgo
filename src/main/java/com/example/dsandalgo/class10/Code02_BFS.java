package com.example.dsandalgo.class10;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 树的广度优先遍历
 *
 */
public class Code02_BFS {

    public static void bfs(Node node){

        if (node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);

        while (!queue.isEmpty()){
            Node poll = queue.poll();

            System.out.println(poll);

            for (Node n :
                    poll.nexts) {
                if (!set.contains(poll)){
                    set.add(n);
                    queue.add(n);
                }

            }


        }



    }

}
