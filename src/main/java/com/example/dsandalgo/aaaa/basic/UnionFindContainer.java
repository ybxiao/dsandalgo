package com.example.dsandalgo.aaaa.basic;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * 并查集
 * 解决联通性问题的利器
 * 1 初始化并差集
 * a) 把给到的数据集，都封装成一个个单独的Node节点，此时每一个节点代表一个集合
 * b) 此时每个节点都是自己所在集合的代表节点，代表节点的表示方法是 当前节点的祖先节点是自己
 * c) 每个集合的节点个数也是1，表示方法为一个单独的sizeMap key：代表节点，value代表当前集合的节点个数
 * 2 并差集使用
 * a) union方法合并两个集合
 * b) isSameSet方法判断两个数据是不是同一个集合
 * c) sets()方法判断总共有几个集合
 * 3 并差集重要概念
 * a) union方法合并，是把当前两个数据所代表的集合合并到一起，并非单纯的合并两个节点。在合并的时候，要把集合节点个数比较小的哪个集合，挂到比较大的那个集合上面。
 * b) 在查找当前节点所在集合代表节点的过程中，把路过的所有节点最后都直接挂在代表节点下。（在N次调用合并之后，并差集的三个方法的复杂度是O(N)）
 * 4 union方法合并逻辑
 * a) 找出当前需要合并的数据对应的封装节点
 * b) 找出两个封装节点对应的代表节点，对应的代表节点是否属于一个集合
 * c）如果不属于，则走合并操作。获取对应代表节点的所在集合的大小，较大集合的代表节点作为最终合并后的集合的代表节点，把较小集合的代表节点挂到其下面
 * 同时更新集合合并后的size大小，并在sizeMap里面删除较小集合的数据。
 */
public class UnionFindContainer {

    public static class  Node<T>{
        public T value;
        public Node(T val){
            this.value = val;
        }
    }

    public static class UnionFind<T>{
        //每一个元素对应的Node类型
        public HashMap<T, Node<T>> nodesMap;
        //每一个节点对应的父节点
        public HashMap<Node<T>, Node<T>> parentMap;

        //key:代表节点，value：当前集合中的元素个数
        public HashMap<Node<T>, Integer> sizeMap;

        public UnionFind(List<T> valueList){
            nodesMap = new HashMap<>();
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (T t : valueList) {
                Node<T> node = new Node(t);
                nodesMap.put(t, node);
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        //给你一个任意节点，去找它的代表节点
        public Node<T> findFather(Node<T> cur){
            Stack<Node<T>> helpStack = new Stack<>();
            while (cur !=parentMap.get(cur)){
                helpStack.push(cur);
                cur =  parentMap.get(cur);
            }
            while (!helpStack.isEmpty()){
                Node<T> pop = helpStack.pop();
                parentMap.put(pop, cur);
            }
            return cur;
        }

        public boolean isSameSet(T v1, T v2){
            return findFather(nodesMap.get(v1)) ==  findFather(nodesMap.get(v2));
        }

        public void union(T v1 ,T v2){
            if (!isSameSet(v1, v2)){
                Node<T> aNode = nodesMap.get(v1);
                Node<T> bNode = nodesMap.get(v2);
                Node<T> father2a = findFather(aNode);
                Node<T> father2b = findFather(bNode);
                Integer aSize = sizeMap.get(father2a);
                Integer bSize = sizeMap.get(father2b);
                Node<T> big = aSize >= bSize ? father2a : father2b;
                Node<T> small = big == father2a ? father2b : father2a;
                parentMap.put(small, big);
                sizeMap.put(big, aSize +  bSize);
                sizeMap.remove(small);

            }
        }
        public int sets(){
            return sizeMap.size();
        }
    }


}
