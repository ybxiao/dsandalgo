package com.example.dsandalgo.juc;

/**
 * 单例模式
 */
public class T_Singlton {


    private T_Singlton(){
    }

    private static class instanceHolder{
        public static  T_Singlton instance =  new T_Singlton();

    }

    public static T_Singlton getInstance() {
        return instanceHolder.instance;
    }

    public static void main(String[] args) {
        System.out.println(T_Singlton.class);

    }
}
