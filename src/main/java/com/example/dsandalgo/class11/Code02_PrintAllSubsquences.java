package com.example.dsandalgo.class11;


import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * created on 2020/8/6.
 * time: 15:20
 * 字符串子序列和子串的区别
 * 子序列是保证相对位置不变的任意n个字符，不要求连续
 * 子串必须是原串的连续n个字符
 *
 * 暴力递归 怎么试 套路一
 * 从左往右试的算法
 * @author yibo.xiao
 */


public class Code02_PrintAllSubsquences {

    public static List<String>  subStrings(String s){
        if (s == null){
            return null;
        }
        char[] strs = s.toCharArray();
        List<String> ans  = new ArrayList<>();
        String path = "";
        process1(strs,0,ans,path);
        return ans;


    }

    //0..index已经做完决策了
    // 在index位置上决定要不要这个字符
    //ans 代表最终返回的结果集合
    //path 是遍历完成之后收集的子串

    private static void process1(char[] strs, int index, List<String> ans, String path) {
        if (index  == strs.length){
            ans.add(path);
            return;
        }
        String no  = path;
        process1(strs,index+1,ans,no);
        String yes = path + String.valueOf(strs[index]);
        process1(strs,index+1,ans,yes);

    }


    public static List<String> subsNoRepeat(String s){
        if (StringUtils.isEmpty(s)){
            return null;
        }
        char[] strs = s.toCharArray();
        Set<String> set =  new HashSet<>();
        String path = "";
        process2(strs,0,set,path);
        List<String> ans = new ArrayList<>();
        for (String cur : set
             ) {
            ans.add(cur);
            
        }
        return ans;
    }

    private static void process2(char[] strs, int index, Set<String> set, String path) {
        if (index == strs.length){
            set.add(path);
            return;
        }
        process2(strs,index+1,set,path);
        process2(strs,index+1,set,path+String.valueOf(strs[index]));
    }


}
