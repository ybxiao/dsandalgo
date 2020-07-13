package com.example.dsandalgo.class02;

import java.util.Queue;

/**
 * 双向链表实现栈和队列
 *
 */
public class DoubleEndsQueueToStackAndQueue {

    public static class DoubleEndsQueue<T> {
        private DoubleNode<T> head;
        private DoubleNode<T> tail;

       public void addFromHead(T value){
           DoubleNode doubleNode = new DoubleNode(value);
           if (head == null){
               head =tail= doubleNode;
           }else{
               doubleNode.next = head;
               head.last = doubleNode;
               head = doubleNode;
           }


       }
       public void addFromBottom(T value){

           DoubleNode doubleNode = new DoubleNode(value);
           if (tail == null){
               head = tail = doubleNode;
           }else{
               tail.next = doubleNode;
               doubleNode.last = tail;
               tail = doubleNode;
           }

       }
       public T popFromHead(){
           if (isEmpty()){
               throw new RuntimeException("栈空了 ，不但能弹出");
           }

           DoubleNode<T> temp = head;
           if (head == tail){
               head = null;
               tail = null;
           }else{
               head = head.next;
               head.last = null;
               temp.next = null;
           }

           return temp.v;

       }
       public T popFromBottom(){
           if (isEmpty()){
               throw new RuntimeException("栈空了 ，不但能弹出");
           }
           DoubleNode<T> temp =tail;
           if (head == tail){
              head = null;
              tail = null;
           }else{
               tail = tail.last;
               tail.next = null;
               temp.last = null;
           }


           return temp.v;


       }

       public boolean isEmpty(){

           return  head == null;
       }
    }

    public static class Mystack<T>{

        DoubleEndsQueue<T>  queue;
        public Mystack(){
            queue = new DoubleEndsQueue<>();

        }


        public void push(T value){
            queue.addFromHead(value);
        }

        public T pop(){
            if (queue.isEmpty()){
                throw new RuntimeException("不行了");
            }
             return queue.popFromHead();

        }

    }

    public static class Myqueue<T>{
        public DoubleEndsQueue<T> queue;


        public Myqueue(){

            queue = new DoubleEndsQueue<>();

        }

        public void add(T value){

            queue.addFromHead(value);
        }
        public T poll(){
          return   queue.popFromBottom();
        }
        public  boolean isEmpty(){
            return queue.isEmpty();
        }



    }



}
