package com.example.dsandalgo.aaaa.leecode;

import java.util.Stack;

/**
 * 一个不含有负数的数组可以代表一圈环形山，每个位置的值代表山的高度
 * 比如， {3,1,2,4,5}、{4,5,3,1,2}或{1,2,4,5,3}都代表同样结构的环形山
 * 山峰A和山峰B能够相互看见的条件为:
 * 1.如果A和B是同一座山，认为不能相互看见
 * 2.如果A和B是不同的山，并且在环中相邻，认为可以相互看见
 * 3.如果A和B是不同的山，并且在环中不相邻，假设两座山高度的最小值为min
 * 1)如果A通过顺时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 2)如果A通过逆时针方向到B的途中没有高度比min大的山峰，认为A和B可以相互看见
 * 两个方向只要有一个能看见，就算A和B可以相互看见
 * 给定一个不含有负数且没有重复值的数组 arr，请返回有多少对山峰能够相互看见
 * 进阶，给定一个不含有负数但可能含有重复值的数组arr，返回有多少对山峰能够相互看见
 */
public class VisibleMountain {

    public static class Record {
        public int height;
        public int count;

        public Record(int val) {
            this.height = val;
            this.count = 1;

        }

    }

    public static int getVisibleNum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        int size = arr.length;
        Stack<Record> stack = new Stack<>();
        stack.push(new Record(arr[maxIndex]));
        int res = 0;
        int index = nextIndex(maxIndex, size);
        while (index != maxIndex) {
            while (!stack.isEmpty() && arr[index] > arr[stack.peek().height]) {
                Record cur = stack.pop();
                res += getInternalNum(cur.count) + 2 * cur.count;
            }
            if (arr[index] == arr[stack.peek().height]) {
                stack.peek().count++;
            } else {
                stack.push(new Record(arr[index]));
            }
            index = nextIndex(index, size);
        }
        while (stack.size() > 2) {
            Record cur = stack.pop();
            res += getInternalNum(cur.count) + 2 * cur.count;
        }
        if (stack.size() == 2) {
            Record cur = stack.pop();
            res += getInternalNum(cur.count) + stack.peek().count == 1 ? cur.count : 2 * cur.count;
        }
        if (stack.size() == 1) {
            Record cur = stack.pop();
            res += getInternalNum(cur.count);
        }
        return res;


    }

    private static int getInternalNum(int count) {
        return count == 1 ? 0 : (count - 1) * count / 2;
    }

    private static int nextIndex(int maxIndex, int size) {
        return maxIndex < size - 1 ? maxIndex + 1 : 0;
    }


}
