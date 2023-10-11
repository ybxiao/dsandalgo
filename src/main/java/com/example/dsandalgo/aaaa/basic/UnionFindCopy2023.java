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
 * <p>
 * <p>
 * <p>
 * 不带封装节点的版本
 * fatherMap 记录每一个数据的代表节点
 * sizeMap 记录每一个代表节点所标记集合的大小
 */
public class UnionFindCopy2023 {

    public static class UnionFind<V> {
        //记录元素的代表节点
        public HashMap<V, V> fatherMap;
        //代表节点所属集合的元素个数
        public HashMap<V, Integer> sizeMap;

        public UnionFind(List<V> values) {
            for (V v :
                    values) {
                fatherMap.put(v, v);
                sizeMap.put(v, 1);

            }
        }

        public V findFather(V v) {
            Stack<V> stack = new Stack<>();

            while (v != fatherMap.get(v)) {
                v = fatherMap.get(v);
                stack.push(v);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), v);
            }
            return v;
        }

        public boolean isSameFather(V a, V b) {
            return findFather(a) == findFather(b);
        }

        public void union(V a, V b) {
            V aFather = findFather(a);
            V bFather = findFather(b);
            if (aFather != bFather) {
                Integer sizeAFather = sizeMap.get(aFather);
                Integer sizeBFather = sizeMap.get(bFather);
                if (sizeAFather >= sizeBFather) {
                    fatherMap.put(bFather, aFather);
                    sizeMap.put(aFather, sizeBFather + sizeBFather);
                    sizeMap.remove(bFather);
                } else {
                    fatherMap.put(aFather, bFather);
                    sizeMap.put(bFather, sizeBFather + sizeBFather);
                    sizeMap.remove(aFather);
                }
            }

        }

        public int size() {
            return sizeMap.size();
        }

    }


}
