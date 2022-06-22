package com.example.dsandalgo.aaaa.dp;

import java.util.ArrayList;
import java.util.List;

import static com.example.dsandalgo.foundationclass.class11.Copy_Code03_PrintAllPermutaions.swap;

/**
 *暴力递归方法打印一个字符串的全排列
 *好的尝试比较容易改成动态规划，好的尝试的判断标准就是递归函数的设计
 * 下面会有两种暴力递归方法，其中第二种方法要比第一种方法好
 * 关键在于递归方法参数的设计
 *
 */
public class PrintAllPermutations {

    public static  List<String> printAllPermutations(String s){
        List<String> ans  = new ArrayList<>();
        if (s ==null || s.length() == 0){
            return ans;
        }
        char[] chars = s.toCharArray();
        List rest = new ArrayList<Character>();
        for (Character c: chars) {
            rest.add(c);
        }
        String path ="";
        f(rest, path, ans);
        return ans;

    }


    /**
     *
     * @param rest 剩下可用的字符
     * @param path 之前已经选择的字符的排列情况保存在path中
     * @param res  所有全排列的结果放入到res中
     * 方法含义： 返回rest字符上的全排列字符串
     *
     */
    public static void  f(List<Character> rest , String path , List<String> res){
        if (rest.isEmpty()){
            res.add(path);
            return;
        }else {
            for (int i = 0; i < rest.size(); i++) {
                Character cur = rest.get(i);
                rest.remove(cur);
                f(rest, path + cur, res);
                rest.add(i, cur);
            }
        }

    }

    /**
     *
     * @param chars 用来进行全排列的字符数组
     * @param index 表示从0...index-1已经做好了全排列，需要在index往后的位置上继续全排列
     * @param ans 所有的全排列结果 存放到ans中
     */
    public static void g(char[] chars, int index, List<String> ans){
        if (index == chars.length){
            ans.add(String.valueOf(chars));
            return;
        }else{
            for (int i = 0; i < chars.length; i++) {
                swap(chars, index, i);
                g(chars, index+1, ans);
                swap(chars, index, i);
            }
        }

    }


}
