package com.example.dsandalgo.class10;

import java.util.*;

/**
 * 拓扑排序
 */
public class Code04_TopologySort {

    public static List<Node>   topologySort(Graph graph){


        HashMap<Node ,Integer> inMap =  new HashMap<>();

        Queue<Node> queue  =  new LinkedList<>();

        for (Node n :
                graph.nodes.values()) {
            inMap.put(n,n.in);
            if (n.in == 0){
                queue.add(n);
            }
        }
        List<Node> result = new ArrayList<>();

        while (!queue.isEmpty()){
            Node poll = queue.poll();
            result.add(poll);
            for (Node n :
                    poll.nexts) {
                inMap.put(n,inMap.get(n)-1  );
                if (inMap.get(n) == 0){
                    queue.add(n);
                }
            }
        }

        return result;



    }



}
