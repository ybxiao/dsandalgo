package com.example.dsandalgo.class10;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_Prim {

    public static class EdgeComparator implements Comparator<Edge>{
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight  - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph){
        Set<Edge> result = new HashSet<>();
        Set<Node> nodeSet = new HashSet<>();
        PriorityQueue<Edge> queue =new PriorityQueue(new EdgeComparator());
        for (Node n :
                graph.nodes.values()) {

            if (!result.contains(n)){

                nodeSet.add(n);
                for (Edge e :
                        n.edges) {
                    queue.add(e);
                }
            }
            while (!queue.isEmpty()){
                Edge poll = queue.poll();
                if (!nodeSet.contains(poll.to)){
                    nodeSet.add(poll.to);
                    for (Edge e:poll.to.edges){
                        queue.add(e);
                    }
                    result.add(poll);
                }

            }


        }

        return result;

    }

    public static int prim(int[][] graph){
        int size = graph.length;
        int[] distance = new int[size];
        Boolean[] vistor = new Boolean[size];
        vistor[0] = true;
        for (int i = 0; i < size; i++) {
            distance[i] = graph[0][i];
        }
        int sum  = 0;
        for (int i =  1  ; i <size  ; i++) {
            int  path = Integer.MAX_VALUE  ;
            int  minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!vistor[j] && distance[j] < path){
                    path =  distance[j];
                    minIndex = j;
                }
            }
            if (minIndex ==-1){
                return sum;
            }
            sum +=path;
            vistor[minIndex] =true;
            for (int j = 0; j < size; j++) {
                if (!vistor[j] && distance[j] > graph[minIndex][j]){
                    distance[j] = graph[minIndex][j];
                }
            }

        }
        return sum;



    }
}
