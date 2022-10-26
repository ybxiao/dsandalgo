package com.example.dsandalgo.aaaa.dp;

import java.util.Stack;

/**
 * 认识一下递归，经典的汉诺塔问题
 */
public class Hanoi {

    public static void hanoi(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move" + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private static void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1);
    }

    private static void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private static void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("move " + n + " from right to left");
        midToLeft(n - 1);
    }

    private static void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    private static void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    public static void main(String[] args) {
        hanoi3(3);
       /* System.out.println( " ================");
        func(3, "left", "right", "mid");*/
    }

    public static void func(int n  , String left ,String right ,String mid){
        if (n == 1){
            System.out.println("move 1 from "+left + "to" + right);
        }else{
            func(n -1 , left, mid ,right);
            System.out.println("move "+n+" from "+left + "to" + right);
            func( n-1 , mid ,right ,left);
        }
    }

    public static void hanoi3(int N) {
        if (N < 1) {
            return;
        }
        Stack<Record> stack = new Stack<>();
        stack.add(new Record(false, N, "left", "right", "mid"));
        while (!stack.isEmpty()) {
            Record cur = stack.pop();
            if (cur.base == 1) {
                System.out.println("Move 1 from " + cur.from + " to " + cur.to);
                if (!stack.isEmpty()) {
                    stack.peek().finish1 = true;
                }
            } else {
                if (!cur.finish1) {
                    stack.push(cur);
                    stack.push(new Record(false, cur.base - 1, cur.from, cur.other, cur.to));
                } else {
                    System.out.println("Move " + cur.base + " from " + cur.from + " to " + cur.to);
                    stack.push(new Record(false, cur.base - 1, cur.other, cur.to, cur.from));
                }
            }
        }
    }


    public static class Record {
        //代表的含义是？
        public boolean finish1;
        public int base;
        public String from;
        public String to;
        public String other;

        public Record(boolean f1, int b, String f, String t, String o) {
            finish1 = false;
            base = b;
            from = f;
            to = t;
            other = o;
        }
    }

}
