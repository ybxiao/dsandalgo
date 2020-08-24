package com.example.dsandalgo.foundationclass.class02;

import java.util.Stack;

/**
 *
 * 求栈中最小值
 */
public class M05_GetMinStack {

    public static class Mystack{
        private Stack<Integer> dataStack;
        private Stack<Integer> helpStack;

        public Mystack(){
            dataStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void push(int n){
            if (dataStack.isEmpty() &&  helpStack.isEmpty()){
                dataStack.push(n);
                helpStack.push(n);
            }
            else{
                dataStack.push(n);
                if (n > helpStack.peek()){
                    helpStack.push(helpStack.peek());
                }else{
                    helpStack.push(n);
                }

            }
        }

        public int pop(){
            if (dataStack.isEmpty()){
                throw new RuntimeException("");
            }
            helpStack.pop();
            return dataStack.pop();
        }

        public int getMin(){
            if (dataStack.isEmpty()){
                throw new RuntimeException("");
            }
            return helpStack.peek();
        }

    }

    public static class Mystack1{
        private Stack<Integer> dataStack;
        private Stack<Integer> helpStack;

        public Mystack1(){
            dataStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void push(int n){
            if(helpStack.isEmpty()){
                helpStack.push(n);
            }else if (n <= helpStack.peek()){
                helpStack.push(n);
            }
            this.dataStack.push(n);
        }

        public int pop(){
            if (dataStack.isEmpty()){
                throw new RuntimeException("");
            }else if (dataStack.peek() == helpStack.peek()){
                helpStack.pop();
            }
            return dataStack.pop();
        }

        public int getMin(){
            if (helpStack.isEmpty()){
                throw new RuntimeException("");
            }
            return helpStack.peek();
        }

    }
}
