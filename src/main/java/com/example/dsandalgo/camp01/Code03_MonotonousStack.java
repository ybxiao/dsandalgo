package com.example.dsandalgo.camp01;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调递归栈
 * 定义：
 * 一种特别设计的栈结构，为了解决如下的问题：
 *
 * 给定一个可能含有重复值的数组arr，i位置的数一定存在如下两个信息
 * 1）arr[i]的左侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 2）arr[i]的右侧离i最近并且小于(或者大于)arr[i]的数在哪？
 * 如果想得到arr中所有位置的两个信息，怎么能让得到信息的过程尽量快。
 *
 * 那么到底怎么设计呢？
 *
 *
 * 例题：
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 *
 */
public class Code03_MonotonousStack {

    public static int[][] getNearLessNoRepeat(int[] arr){
        int[][] res =  new int[arr.length][2];

        //栈中元素自底向上依次变小
        //数组中元素不重复
        Stack<Integer> stack =  new Stack<>();
        int right = 0 ;
        while (right < arr.length){

            while (!stack.isEmpty() && arr[stack.peek()] > arr[right]){
                Integer popIndex = stack.pop();
                res[popIndex][0] =  stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][1] = right;
            }
            stack.push(right);
            right ++;
        }
        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            res[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            res[pop][1] =  -1;
        }

        return res;

    }

    public static int[][] getNearLess(int[] arr){
        int[][] res =  new int[arr.length][2];

        //栈中元素自底向上依次变小
        //数组中元素重复
        Stack<List<Integer>> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]){
                List<Integer> list = stack.pop();
                int leftIndex = stack.isEmpty()? -1 : stack.pop().get(stack.pop().size() -1);

                for (Integer index : list ) {
                    res[index][0] = leftIndex;
                    res[index][1] = i;
                }
                if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]){
                   stack.peek().add(i);
                }else{
                    ArrayList<Integer> pushList = new ArrayList<>();
                    pushList.add(i);
                    stack.push(pushList);
                }

            }



        }

        return res;
    }


    //暴力解法
    //遍历数组每一个元素，依次向左找和向右找，找到比当前位置小的的第一个元素留存

    public static int[][] rightWay(int[] arr){
        int[][] res =  new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {

            //记录最左的合适位置
            int leftIndxe = -1;
            //记录最右的合适位置
            int rightIndex = -1;
            int cur = i-1;
            while (cur > 0){
                if (arr[cur] < arr[i]){
                    leftIndxe = cur;
                    break;
                }
                cur --;
            }


            cur = i + 1;
            while (cur < arr.length){
                if (arr[cur] < arr[i]){
                    rightIndex = cur;
                    break;
                }
                cur ++;
            }
            res[i][0] = leftIndxe;
            res[i][1] =  rightIndex;

        }
        return  res;
    }

}
