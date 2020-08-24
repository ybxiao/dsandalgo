package com.example.dsandalgo.foundationclass.class02;

import java.util.LinkedList;
import java.util.Queue;

public class M07_TwoQueueImplStack {

    public static class TowQueueStack<T>{
        Queue<T> queue1;
        Queue<T> queue2;

        public TowQueueStack(){
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }
        public void push(T v){
            queue1.offer(v);


        }
        public T poll(){
            while (queue1.size() >1){
                queue2.offer(queue1.poll());

            }
            T poll = queue1.poll();

            Queue temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return poll;


        }
        public T peek(){
            while (queue1.size() >1){
                queue2.offer(queue1.poll());

            }
            T poll = queue1.peek();

            Queue temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            queue1.offer(poll);
            return poll;


        }


    }
}
