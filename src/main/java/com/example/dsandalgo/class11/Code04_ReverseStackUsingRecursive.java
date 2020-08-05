package com.example.dsandalgo.class11;

import java.util.Stack;

/**
 * created on 2020/8/4.
 * time: 15:44
 * 仰望星空之不申请额外的数据结构逆序栈元素
 * @author yibo.xiao
 */


public class Code04_ReverseStackUsingRecursive {


    public static void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int i = f(stack);
        reverseStack(stack);
        stack.push(i);

    }

    private static int f(Stack<Integer> stack) {
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }


    }


}
