package com.example.dsandalgo.class10;

import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 *
 *1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 *
 *
 *
 */
public class Code04_Kruskal {


    public static class Node<V>{
        public V  v;
        public Node(V v){
            this.v = v;
        }


    }
    public static class UnionSet<V>{
        private HashMap<V,Node<V>> nodes;
        private HashMap<Node<V>,Node<V>> parentMap;
        private HashMap<Node<V>,Integer> sizeMap;

        public UnionSet(List<V> values){
            for (V v:
                    values) {
                Node node = new Node(v);
                nodes.put(v,node);
                parentMap.put(node,node);
                sizeMap.put(node,1);
            }
        }



        public Node findFather(V v){
            Node cur  = new Node(v);
            Stack<Node> stack = new Stack<>();
            while (cur !=parentMap.get(cur)){
                stack.push(cur);
                cur = parentMap.get(cur);

            }
            while (!stack.isEmpty()){
                parentMap.put(stack.pop(),cur);
            }
            return cur;


        }

        public boolean isSameSet(V v1,V v2){
            Node f1 = findFather(v1);
            Node f2 = findFather(v2);
            return f1 ==  f2;

        }

        public void union(V  v1, V v2){
            if (!nodes.containsKey(v1) || !nodes.containsKey(v2)){
                return;
            }

            if (!isSameSet(v1,v2)){
                Node f1 = findFather(v1);

                Node f2 = findFather(v2);

                Integer size1 = sizeMap.get(f1);
                Integer size2 = sizeMap.get(f2);

                if (size1 < size2){
                    parentMap.put(f1,f2);
                    sizeMap.put(f2,size1+size2);
                    sizeMap.remove(f1);
                }else{
                    parentMap.put(f2,f1);
                    sizeMap.put(f1,size1+size2);
                    sizeMap.remove(f2);

                }

            }

        }


    }
    public static class MyComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }


    public static Set<Edge>  kruskalMST(Graph graph){
        List<Edge> edges = graph.edges;
        Set<Edge> result = new HashSet<>();
        List list = CollectionUtils.arrayToList(graph.nodes.values().stream().toArray());
        UnionSet<com.example.dsandalgo.class10.Node> edgeUnionSet = new UnionSet(list);

        Collections.sort(edges,new MyComparator());
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            if (!edgeUnionSet.isSameSet(edge.from,edge.to)){
                edgeUnionSet.union(edge.from,edge.to);
                result.add(edge);
            }
        }


        return result;

    }
}
