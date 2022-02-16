package com.example.dsandalgo.leecode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 求两个正序数组的中位数
 * 已知两个数组有序长度分别为 m n
 * 求其中位数 要求时间复杂度O(log(M+N))
 * 思路：
 * 转化成求数组最K小问题进行求解
 *
 *
 */
public class Leecode_No_4 {

    public static int getMedian(int[] num1,int[] num2){
        LinkedList lst =new LinkedList();


        return 0;
    }

    public static void main(String[] args) {
        //Stack s = new Stack<Character>();

        //Stack stack1  = new Stack<Character>();
        elimate("(1(23456(789)a)(b)c)");
    }

    public static void elimate(String s){
        if(s == null || s.length() == 0){
            return;
        }
        char[] str = s.toCharArray();
        Stack stack = new Stack<Character>();
        Stack help = new Stack<Character>();
        StringBuffer res = new StringBuffer();
        for(int i = 0 ;i < str.length ;i++){
            if (str[i] != ')'){
                stack.push(str[i]);
            }
            if(str[i] == ')'){
                if (stack.isEmpty()){
                    System.out.println("ERROR");
                    return;
                }
                while (!stack.isEmpty()){
                    char  temp  = (char) stack.pop();
                    if(temp == '('){
                        StringBuffer sb = new StringBuffer();
                        while(!help.isEmpty()){
                            sb.append(help.pop());

                        }
                        res.append(sb);
                        continue;
                    }else {
                        help.push(temp);
                    }

                }
            }


        }
        if(stack.isEmpty()){
            System.out.println(res.toString());
        }else{
            System.out.println("ERROR");
        }







    }






}
