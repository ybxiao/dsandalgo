package com.example.dsandalgo.foundationclass.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * created on 2020/7/30.
 * time: 16:56
 * 并查集，增删改O(1)的牛逼算法
 * @author yibo.xiao
 */


public class Code01_UnionFind {

    public static class Node<V> {
        private V v ;

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



}
