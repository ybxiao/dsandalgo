package com.example.dsandalgo.aaaa.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串所有的子序列
 * 先用暴力递归的方式（尝试的方式）
 * 再通过暴力递归的方式推导出动态规划的状态转移
 */
public class PrintAllSubsquences {

    public static List<String> subSequences(String s){
        if (s == null || s.length() == 0 ){
            return null;
        }
        char[] str = s.toCharArray();
        List<String> ans =  new ArrayList<>();
        process1(str, 0, ans ,"");
        return ans;

    }

    /**
     * 在str数组种，从0...到index位置上都已经做好了选择
     * path代表之前做好的选择决定，无法更改
     * 方法返回从index...chars.length位置上有多少种子序列
     * 把结果放入到ans中
     *
     * @param str
     * @param index
     * @param ans
     * @param path
     */
    public static void process1(char[] str, int index, List<String> ans, String path){
        if (index == str.length){
            ans.add(path);
            return;
        }
        //没有要index位置上的字符
        process1(str, index+1, ans, path);
        //要了index位置的字符
        process1(str, index+1, ans, path + str[index]);

    }


    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = subSequences(test);
        for (String s: ans1){
            System.out.println(s);
        }
    }

}
