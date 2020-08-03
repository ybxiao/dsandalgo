package com.example.dsandalgo.class11;

/**
 * created on 2020/8/3.
 * time: 15:39
 * 经典递归 汉诺塔问题
 * @author yibo.xiao
 */


public class Code01_Hanoi {

    public static void hanoi1(int n){
        leftToRight(n);
    }

    //把n层圆盘从左 移动到右
    private static void leftToRight(int n) {
        if (n  == 1){
            System.out.println("move  1 from left to right");
            return;
        }
        leftToMid(n-1);
        System.out.println("move  "+n+" from left to right");
        midToRight(n-1);
    }

    private static void midToRight(int n) {
        if (n ==1 ){
            System.out.println("move 1 form mid to right");
            return;
        }
        midToLeft(n-1);
        System.out.println("move "+n+" from mid to right");
        leftToRight(n-1);
    }

    private static void midToLeft(int n) {
        if (n ==1){
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n-1);
        System.out.println("move "+n+ "from mid to left");
        rightToLeft(n-1);
    }

    private static void rightToLeft(int n) {
        if (n ==1){
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n-1);
        System.out.println("move "+n+" from right to left");
        midToLeft(n-1);

    }

    private static void rightToMid(int n) {
        if (n ==1){
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n-1);
        System.out.println("move "+n+" from right to mid");
        leftToMid(n-1);
    }

    private static void leftToMid(int n) {

        if (n ==1){
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(n-1);
        System.out.println("move "+n+" from left to mid");
        rightToMid(n-1);
    }

    public static void hanoi2(int n){
        if (n > 0){
            func("A","C","B",n);
        }

    }

    private static void func(String from, String to, String other, int n) {
        if (n ==1){
            System.out.println("move 1  from"+from+"  to" +to);
            return;
        }
        func(from,other,to,n-1);
        System.out.println("move "+n+"  from"+from+"  to" +to);
        func(other,to,from,n-1);
    }


    public static void main(String[] args) {
        hanoi1(3);
        System.out.println("=======================");
        hanoi2(3);
    }




}
