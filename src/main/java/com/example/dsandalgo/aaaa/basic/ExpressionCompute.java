package com.example.dsandalgo.aaaa.basic;

import java.util.LinkedList;

/**
 * 经典递归套路解题，可以解决一票题目
 * https://leetcode.com/problems/basic-calculator-iii/
 * 具体题目描述：
 * 给定一个字符串表达式str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号。返回公式的计算结果
 * 难点在于括号可能嵌套很多层，str="48*((70-65)-43)+8*1"，返回-1816。str="3+1*4"，返回7。str="3+(1*4)"，返回7。
 * 1，可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查
 * 2，如果是负数，就需要用括号括起来，比如"4*(-3)"但如果负数作为公式的开头或括号部分的开头，则可以没有括号，比如"-3*4"和"(-3*4)"都是合法的
 * 3，不用考虑计算过程中会发生溢出的情况。
 * 设计一个递归函数 f
 */
public class ExpressionCompute {

    public static int calculate(String str) {
        return f(str.toCharArray(), 0)[0];
    }


    //index表示：从str[index]开始往后进行计算，遇到')'或者终止位置就停，
    //f函数，返回两个值，一个是当前计算段的结果，一个是当前计算到了什么位置
    private static int[] f(char[] charArray, int index) {
        LinkedList<String> queue = new LinkedList<>();
        int cur = 0;
        int[] bracket = null;
        while (index < charArray.length || charArray[index] == ')') {
            if (charArray[index] > '0' || charArray[index] < '9') {
                cur = cur * 10 + charArray[index++] - '0';
            } else if (charArray[index] != '(') {
                addNum(queue, cur, charArray[index++]);
                cur = 0;
            } else {
                bracket = f(charArray, index + 1);
                cur = bracket[0];
                index = bracket[1] + 1;
            }
        }
        addNum(queue, cur, '+');
        return new int[]{getAns(queue), index};
    }

    private static int getAns(LinkedList<String> queue) {
        int res = Integer.valueOf(queue.pollFirst());
        while (queue.size() > 1) {
            String op = queue.pollFirst();
            int pre = Integer.valueOf(queue.pollFirst());
            res = op.equals("+") ? res + pre : res - pre;
        }
        return res;
    }

    private static void addNum(LinkedList<String> queue, int num, char op) {
        if (!queue.isEmpty() && (queue.peekLast().equals("*") || queue.peekLast().equals("/"))) {
            String top = queue.pollLast();
            Integer pre = Integer.valueOf(queue.peekLast());
            num = top.equals("*") ? pre * num : pre / num;
        }
        queue.addLast(String.valueOf(num));
        queue.addLast(String.valueOf(op));
    }


}
