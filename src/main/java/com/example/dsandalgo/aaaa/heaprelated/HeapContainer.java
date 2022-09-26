package com.example.dsandalgo.aaaa.heaprelated;

import java.util.Comparator;

public class HeapContainer {

    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap( int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        public void push(int value){
            if (isFull()){
                throw new IllegalStateException("heap is full ,can not push");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize ++);

        }

        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index -1)/2]){
                swap(arr, index , (index-1)/2);
                index = (index -1)/2;
            }
        }

        private void swap(int[] arr, int index, int i) {
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }

        public int pop(){
            if (heapSize <= 0){
                throw new IllegalStateException("heap is empty");
            }
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0 ,heapSize);
            return ans;
        }

        private void heapify(int[] arr, int index, int heapSize) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                //有左孩子的时候，有可能有右孩子，也有可能没有右孩子
                //把当前节点左右孩子中较大的节点 给到larger
                int larger = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                //比较index的值 和 larger节点值
                larger = arr[larger] > arr[index] ? larger : index;
                if (larger == index) {
                    break;
                }
                swap(arr, index, larger);
                index = larger;
                //循环往复
                left = index * 2 + 1;
            }

        }

    }

    public static class RightMaxHeap{
        private int[] heap;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            this.heap = new int[limit];
            this.limit = limit;
            this.size = 0;
        }

        public boolean isFull(){
            return size == limit;
        }
        public boolean isEmpty(){
            return size == 0;
        }
        public void push(int value){
            if (isFull()){
                throw new IllegalStateException("heap is full");
            }
            heap[size++] = value;
        }

        public int pop(){
            int maxIndex = 0  ;
            for (int i = 1; i < size; i++) {
                if (heap[i] > heap[maxIndex]){
                    maxIndex = i;
                }
            }
            int ans = heap[maxIndex] ;
            heap[maxIndex] = heap[--size];
            return ans;
        }
    }

    public static class MyComparator implements Comparator<Integer>{
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        int value = 1000;
        int limit = 100 ;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes ; i++) {
            int curLimit = (int) (Math.random() * limit) +1;
            MyMaxHeap myMaxHeap = new MyMaxHeap(curLimit);
            RightMaxHeap rightMaxHeap = new RightMaxHeap(curLimit);
            int curOpTimes = (int)(Math.random() * limit);
            for (int j = 0; j < curOpTimes; j++) {
                if (myMaxHeap.isFull() !=  rightMaxHeap.isFull()){
                    System.out.println("Oops");
                }
                if (myMaxHeap.isEmpty() !=  rightMaxHeap.isEmpty()){
                    System.out.println("Oops");
                }
                if (myMaxHeap.isEmpty()){
                    int  curValue = (int ) Math.random() * value;
                    myMaxHeap.push(curValue);
                    rightMaxHeap.push(curValue);
                } else if (myMaxHeap.isFull()) {
                    if (myMaxHeap.pop() != rightMaxHeap.pop()){
                        System.out.println("Oops");
                    }
                }else {
                    if (Math.random() < 0.5){
                        int curValue = (int) (Math.random() * value);
                        myMaxHeap.push(curValue);
                        rightMaxHeap.push(curValue);
                    }else{
                        if (myMaxHeap.pop() != rightMaxHeap.pop()){
                            System.out.println("Oops");
                        }
                    }
                }
            }
        }
        System.out.println("finished");
    }
}
