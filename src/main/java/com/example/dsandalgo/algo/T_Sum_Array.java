package com.example.dsandalgo.algo;

import java.util.HashMap;
import java.util.Map;

public class T_Sum_Array {

    public static void main(String[] args) {
        Long ll = 12L;
        System.out.println(ll.byteValue());
    }


    public int[] sumArray1(int[] arr,int target){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] + arr[j] ==target){
                    return new int[]{i,j};
                }
            }



        }

        return null;

    }

    public int[] sumArray2(int[] arr,int target){
        Map<Integer,Integer>  arrMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            arrMap.put(arr[i],i);
        }
        for (int i = 0; i < arr.length; i++) {
            int need = target - arr[i];
            if (arrMap.containsKey(need) && arrMap.get(need) != i){
                return new int[]{i,arrMap.get(need)};
            }
        }

        return null;
    }
}
