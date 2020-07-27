package com.example.dsandalgo.class09;

import java.util.HashSet;
import java.util.Set;

/**
 * created on 2020/7/27.
 * time: 16:13

 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 *
 * 第一种，列举全部情况 然后挑选出满足条件的情况下 灯最少的情况
 * 第二种 贪心
 *
 *
 * @author yibo.xiao
 */


public class Code02_Light {


    public static int minLight1(String string){
        if (string == null){
            return 0;
        }
        int i = process1(string.toCharArray(),0,new HashSet<Integer>());

        return i;
    }

    private static int process1(char[] chars, int index, HashSet<Integer> lights) {
        if (index == chars.length){
            for (int i = 0; i < chars.length; i++) {
                if (chars[i]!='X'){
                    if (!lights.contains(i) && !lights.contains(i+1) && !lights.contains(i-1)){
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        }else{
            int no =process1(chars,index+1,lights);
            int yes = Integer.MAX_VALUE;
            if (chars[index] == '.'){
                lights.add(index);
                yes =  process1(chars,index+1,lights);
                lights.remove(index);
            }
            return Math.min(no,yes);

        }

    }

    public static int process2(String road){
        if (road == null) {
            return 0;
        }
        char[] chars = road.toCharArray();
        int ans = 0;
        int index = 0;
        while (index < chars.length){
            if (chars[index] == '.') {
                if (index +1 == chars.length){
                    break;
                }
                if ( chars[index+1] =='X'){
                    ans ++;
                    index = index+2;
                }else {
                    ans ++;
                    index = index+3;
                }
            }
            if (chars[index] == 'X'){
                index ++;
            }
        }

        return index;

    }


}
