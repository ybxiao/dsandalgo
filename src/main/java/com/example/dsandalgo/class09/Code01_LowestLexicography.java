package com.example.dsandalgo.class09;

import java.util.*;

/**
 * created on 2020/7/27.
 * time: 11:13
 * 两种解法
 * 1 暴力
 *  暴力更难啊 根本理解不了
 * 2 贪心
 * @author yibo.xiao
 */


public class Code01_LowestLexicography {


    public static String lowestLexicography1(String[] strings){
        if (strings == null || strings.length < 1) {
            return null;
        }
        //暴力求解，存放所有可能
        List<String> arr = new ArrayList<>();
        //存放已经使用过的字符串
        Set<Integer> use  = new HashSet<>();
        process(strings, use, "", arr);
        String ans = arr.get(0);
        for (int i = 1; i < arr.size(); i++) {

            if (ans.compareTo(arr.get(i)) >0){
                ans = arr.get(i);
            }
        }

        return ans;
    }

    /**
     * 暴力求解方法：求出所有的字符串拼接可能  全排列
     * @param strings
     * @param use
     * @param path
     * @param arr
     * @return
     */
    private static void process(String[] strings, Set<Integer> use, String path, List<String> arr) {
        if (use.size() ==  strings.length){
            arr.add(path);
        }else {
            for (int i = 0; i < strings.length; i++) {
                if (!use.contains(i)) {
                    use.add(i);
                    process(strings, use, path + strings[i], arr);
                    use.remove(i);
                }
            }

        }
    }

    public static String lowestLexicography2(String[] strings){
        if (strings ==null || strings.length <1){
            return null;
        }
        Arrays.sort(strings,new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
        }

        return sb.toString();

    }

    public  static  class MyComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }

    public static void main(String[] args) {
        String[] str = new String[]{"ab","b","ba"};

        lowestLexicography1(str);
    }

}
