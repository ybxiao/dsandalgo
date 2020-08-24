package com.example.dsandalgo.foundationclass.class10;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Code07_MegerUser {

    public static class UnionSet<V>{
        private HashMap<V, Code01_UnionFind.Node<V>> nodes;
        private HashMap<Code01_UnionFind.Node<V>, Code01_UnionFind.Node<V>> parentMap;
        private HashMap<Code01_UnionFind.Node<V>,Integer> sizeMap;

        public UnionSet(List<V> values){
            for (V v:
                    values) {
                Code01_UnionFind.Node node = new Code01_UnionFind.Node(v);
                nodes.put(v,node);
                parentMap.put(node,node);
                sizeMap.put(node,1);
            }
        }



        public Code01_UnionFind.Node findFather(V v){
            Code01_UnionFind.Node cur  = new Code01_UnionFind.Node(v);
            Stack<Code01_UnionFind.Node> stack = new Stack<>();
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
            Code01_UnionFind.Node f1 = findFather(v1);
            Code01_UnionFind.Node f2 = findFather(v2);
            return f1 ==  f2;

        }

        public void union(V  v1, V v2){
            if (!nodes.containsKey(v1) || !nodes.containsKey(v2)){
                return;
            }

            if (!isSameSet(v1,v2)){
                Code01_UnionFind.Node f1 = findFather(v1);

                Code01_UnionFind.Node f2 = findFather(v2);

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


        public int getNum() {
            return sizeMap.size();
        }
    }

    public static class User{
        public String a;
        public String b;
        public String c;

        public User(String a,String  b,String c){
            this.a = a;
            this.b = b;
            this.c = c;
        }


    }

    //返回合并之后的数量
    public static int mergeUsers(List<User> users){

        UnionSet<User> userUnionSet = new UnionSet<>(users);
        
        HashMap<String,User> mapA = new HashMap<>();
        HashMap<String,User> mapB = new HashMap<>();
        HashMap<String,User> mapC = new HashMap<>();

        for (User u :
                users) {
            if (mapA.containsKey(u.a)){
                userUnionSet.union(u,mapA.get(u.a));
            }else{
                mapA.put(u.a,u);
            }
            if (mapB.containsKey(u.b)){
                userUnionSet.union(u,mapB.get(u.b));
            }else{
                mapB.put(u.b,u);
            }
            if (mapC.containsKey(u.c)){
                userUnionSet.union(u,mapC.get(u.c));
            }else{
                mapC.put(u.c,u);
            }
        }

        return userUnionSet.getNum();

    }

}
