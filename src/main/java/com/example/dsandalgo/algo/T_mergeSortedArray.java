package com.example.dsandalgo.algo;

public class T_mergeSortedArray {
    public static void main(String[] args) {


    }


    public int[] merge(int[] a ,int m  ,int[] b ,int n){
        int[] ints = new int[m + n];
        int count = 0;
        int i  =0;
        int j = 0;
        while (count != m+n){
            if (i == m){
                while (j < n){
                    ints[count++]  = b[j++];
                }
                break;

            }
            if (j  == n){
                while (i < m){
                    ints[count ++]  = a [i++];
                }
                break;
            }

            if (a[i] >= b[j]) {
                ints[count++] = b[j++];
            }else{
                ints[count++] = a[i++];
            }
            }
        return ints;
        }


 }
