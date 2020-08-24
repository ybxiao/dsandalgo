package com.example.dsandalgo.foundationclass.class02;

public class M04_RingArray {

    public static class MyQueue {
        private int[] array;
        private final int limit;
        private int pushIndex;
        private int pollIndex;
        private int size;

        public MyQueue(int limit) {
            this.limit = limit;
            pushIndex = 0;
            pollIndex = 0;
            size = 0;
            array = new int[limit];
        }

        public void push(int n) {
            if (size >= limit) {
                throw new RuntimeException("已满，不能再放了");
            } else {
                array[pushIndex] = n;
                size++;
                pushIndex = nextInedx(pushIndex);


            }

        }

        public int pop() {
            if (size == 0) {
                throw new RuntimeException("");
            } else {
                int res = array[pollIndex];
                size--;
                pollIndex = nextInedx(pollIndex);
                return res;
            }


        }

        public int nextInedx(int i) {
            return i < limit - 1 ? i + 1 : 0;

        }
    }


}
