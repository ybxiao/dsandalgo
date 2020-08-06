package com.example.dsandalgo.class11;

import java.util.ArrayList;
import java.util.List;

/**
 * created on 2020/8/6.
 * time: 16:01
 * 打印字符串的全排列有多少中可能
 * @author yibo.xiao
 */


public class Code03_PrintAllPermutations {

    public static List<String> permutation(String s){
        List<String> ans  =  new ArrayList<>();
        if (s == null  || s.length() ==0){
            return ans;
        }
        char[] chars = s.toCharArray();
        process(chars,0,ans);
        return ans;

    }

    //0...index位
    //此时考虑index位置
    //递归图怎么画 再画一遍

    private static void process(char[] chars, int index, List<String> ans) {
        if (index  == chars.length){
            return;
        }
        for (int i = index ; i <chars.length ; i++) {
            swap(chars,index,i);
            process(chars,index+1,ans);
            swap(chars,index,i);

        }
    }

    private static void swap(char[] chars, int i, int index) {
        char temp =  chars[i];
        chars[i]  = chars[index];
        chars[index] = temp;
    }


    public static List<String> permutation2(String s){
        List<String> ans  =  new ArrayList<>();
        if (s == null  || s.length() ==0){
            return ans;
        }
        char[] chars = s.toCharArray();
        process2(chars,0,ans);
        return ans;

    }

    private static void process2(char[] chars, int index, List<String> ans){
        if (index  ==  chars.length ){
            ans.add(String.valueOf(chars));
            return;
        }
        boolean[] visitor = new boolean[26];
        for (int i = index; i < chars.length ; i++) {
            if (!visitor[chars[i] - 'a']){
                visitor[chars[i] - 'a'] =true;
                swap(chars,index ,i);
                process2(chars,index +1 ,ans);
                swap(chars,index,i);
            }
        }

    }


}
