package com.example.dsandalgo.juc;

public class T_0001 {

    public static void main(String[] args) {

        int i = 0,j = 0;
        i = i++;
        j = ++j;
        System.out.println(i);
        System.out.println(j);


      /*  t1(i++);
        t1(++j);*/

    }

    public static void t1(int index){
        System.out.println(index);

        StringBuffer sb = new StringBuffer();

    }


}
