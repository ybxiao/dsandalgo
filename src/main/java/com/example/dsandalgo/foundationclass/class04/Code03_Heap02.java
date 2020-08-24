package com.example.dsandalgo.foundationclass.class04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 支持泛型
 * 带自定义比较器
 * 且给用户提供了resign方法
 *
 */
public class Code03_Heap02 {


    public static class MyHeap<T>{

        private ArrayList<T> arr;
        private Map<T,Integer> indexMap;
        private int heapSize;
        private Comparator<? super T> com;

        public MyHeap(Comparator<? super T> com){

            this.arr = new ArrayList<>();
            this.com = com;
            this.indexMap = new HashMap<T,Integer>();
            this.heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize ==0;
        }

        public int size(){

            return heapSize;
        }

        public boolean contains(T value){
            return indexMap.containsKey(value);
        }

        public void push(T value){
            indexMap.put(value,heapSize);
            arr.add(value);
            heapInsert(heapSize++);
        }

        public T pop(){
            if (isEmpty()){
                throw new RuntimeException("");
            }
            T res = arr.get(0);
            indexMap.remove(res);
            swap(0,--heapSize);
            heapify(0,heapSize);


            return res;
        }

        public void resign(T value){
            Integer integer = indexMap.get(value);
            heapInsert(integer);
            heapify(integer,heapSize);

        }

        public void heapInsert(int index){
            while (com.compare(arr.get(index), arr.get((index - 1) / 2)) < 0){
                swap(index,(index-1)/2);
                index = (index-1)/2;
            }

        }

        public void swap(int L,int R){
            T t = arr.get(L);
            T t1 = arr.get(R);
            arr.set(L,t1);
            arr.set(R,t);
            indexMap.put(t1,L);
            indexMap.put(t,R);

        }
        public void heapify(int index ,int heapSize){
            int left = index*2 +1;
            while (left < heapSize){
                int largest = (left+1 < heapSize &&  com.compare(arr.get(left+1),arr.get(left)) <0) ? left+1:left;
                largest = com.compare(arr.get(largest),arr.get(index)) < 0? largest : left;
                if (largest == index){
                    break;
                }
                swap(left,largest);
                index = largest;
                left = index*2 +1;
            }


        }







    }

}
