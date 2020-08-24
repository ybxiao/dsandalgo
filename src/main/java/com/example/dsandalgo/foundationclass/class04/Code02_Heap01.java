package com.example.dsandalgo.foundationclass.class04;

public class Code02_Heap01 {

    public static class MyMaxHeap {
        private int[] arr;
        private int heapSize;
        private int limit;

        public MyMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            heapSize = 0;

        }

        public void push(int value){
            if (isFull()){
                throw  new RuntimeException("");
            }
            arr[heapSize] = value;
            heapInsert(arr,heapSize++);

        }
        public int pop(){
            if (isEmpty()){
                throw new RuntimeException("");
            }

            int res = arr[0];
            swap(arr,0 ,--heapSize);
            heapify(arr,0,heapSize);
            return res;
        }

        public boolean isFull(){
            return heapSize ==limit;
        }

        public boolean isEmpty(){
            return heapSize == 0;

        }

        /**
         * 在数组arr从index开始 heapsize的范围上做大根堆
         *
         * @param arr
         * @param index
         */
        public void heapInsert(int[] arr, int index) {

            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }


        }

        public void heapify(int[]arr ,int index,int heapSize){
            int left = index * 2 +1;
            while (left < heapSize){
                //右孩子存在，且右孩子节点的值大于左孩子节点的值。
                int largest = (left +1 < heapSize)  && (arr[left +1] > arr[left]) ? left+1:left;

                largest = arr[largest] > arr[index] ? largest:index;

                if (largest == index){
                    break;
                }
                swap(arr,index,largest);
                index = largest;
                left = index *2 +1;

            }



        }

    }



    public static void swap(int[] arr, int L, int R) {
        int temp = arr[L];
        arr[L] = arr[R];
        arr[R] = temp;
    }

    public static void main(String[] args) {
        MyMaxHeap heap= new MyMaxHeap(5);
        for (int i = 0; i < 5; i++) {
            heap.push(i);
        }
        heap.pop();
    }


}
