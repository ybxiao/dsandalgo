package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Maps;

public class CubeRoot {
    public static double cube(double num) {
        double left = 0;
        double right = num;
        double error = 0.00001;
        while (right - left > error) {
            double temp = left + (right - left) / 2;
            double cube = temp * temp * temp;
            if (cube ==  num) {
               return temp;
            } else if (cube > num) {
                right = temp;
            }else{
                left = temp;
            }

        }
        return (left + right) / 2;
    }

    public static void main(String[] args) {
        System.out.println(cube(27));
    }

}
