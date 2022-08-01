package com.example.dsandalgo.aaaa.dp;

import java.util.Stack;

/**
 * 不申请额外的数据接口，逆序一个栈
 * 首先设计一个函数f。给一个栈，去除这个栈的栈低元素，把栈上面的所有元素落下来
 * 返回栈底元素
 * 再根据f函数，实现所需要的功能
 */
public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        Integer last = f(stack);
        reverse(stack);
        stack.push(last);


    }

    public static Integer f(Stack<Integer> stack){
        Integer result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else{
            Integer last = f(stack);
            stack.push(result);
            return last;
        }


    }

    public static void main(String[] args) {
        Stack<Integer> integers = new Stack<>();
        integers.push(1);
        integers.push(2);
        integers.push(3);
        System.out.println("qian:" + integers);
        reverse(integers);
        System.out.println("hou:"+ integers);
    }
}
