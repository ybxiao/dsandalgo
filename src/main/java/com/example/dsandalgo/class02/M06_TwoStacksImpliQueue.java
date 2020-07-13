package com.example.dsandalgo.class02;

import java.util.Stack;

public class M06_TwoStacksImpliQueue {

    public static class TowStackQueue{
        private Stack<Integer> dataStack;
        private Stack<Integer> helpStack;

        public TowStackQueue(){
            dataStack = new Stack<>();
            helpStack  = new Stack<>();


        }

        public void push2Pop(){
            if (helpStack.isEmpty()){
                while (!dataStack.isEmpty()){
                    helpStack.push(dataStack.pop());
                }
            }
        }

        public void push(int v){
            dataStack.push(v);
            push2Pop();

        }
        public int poll(){
            if (helpStack.isEmpty() && dataStack.isEmpty()){
                throw new RuntimeException("");
            }
            push2Pop();
            return helpStack.pop();

        }


    }
}
