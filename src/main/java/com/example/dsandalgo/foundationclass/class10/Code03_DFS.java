package com.example.dsandalgo.foundationclass.class10;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Code03_DFS {

    public static void dfs(Node node){
        if (node  == null){
            return;
        }
        Stack<Node> stack =  new Stack<>() ;
        Set<Node> set =  new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.v);

        while (!stack.isEmpty()){

            Node pop = stack.pop();
            for (Node n :
                    pop.nexts) {
                if (!set.contains(n)){
                    set.add(n);
                    //stack.push(pop);
                    stack.push(n);
                    System.out.println(n.v);
                    break;
                }
            }
        }




    }
}
