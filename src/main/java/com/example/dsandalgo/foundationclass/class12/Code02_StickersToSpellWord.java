package com.example.dsandalgo.foundationclass.class12;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * 给定一个字符串str，给定一个字符串类型的数组arr。
 * arr里的每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * 至少需要两张贴纸"ba"和"abcd"，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。是可以拼出str的。所以返回2。
 *
 *
 */
public class Code02_StickersToSpellWord {

    public static int minStickers1(String[] arr ,String target){
        if (arr == null ||  arr.length == 0){
            return 0;
        }
        int n = arr.length;

        int[][] map = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] chars = arr[i].toCharArray();
            for (char c :chars) {
                map[i][c-'a']++;
            }
        }

        Map<String,Integer> dp = new HashMap<String,Integer>();
        dp.put("",0);
        return process1(map, dp,target);

    }

    //傻缓存 map里面存放数组的词频统计
    //dp key：字符串 value:拼出该字符串使用的最小贴纸数

    // dp 傻缓存，如果t已经算过了，直接返回dp中的值
    // t 剩余的目标
    // 0..N每一个字符串所含字符的词频统计
    // 返回值是-1，map 中的贴纸  怎么都无法rest

    private static int process1(int[][] map, Map<String, Integer> dp, String rest) {
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }

        int ans = Integer.MAX_VALUE ;
        char[] str = rest.toCharArray();
        int[] tmap = new int[str.length];
        for (char c :str) {
            tmap[c-'a'] ++;
        }

        for (int i = 0; i < map.length; i++) {

            if (map[i][str[0]-'a'] == 0){
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 26; j++) {
                if (tmap[j] > 0){
                    for (int k = 0; k < Math.max(0, tmap[j] - map[i][j] ); k++) {
                        sb.append(tmap[j]);
                    }
                }

               /* int max = Math.max(0, tmap[j] - map[i][j]);
                while (max > 0){
                    sb.append('a'+ j);
                    max --;
                }*/

            }
            String s = sb.toString();
            int temp = process1(map, dp, s);
            if (temp != -1){
                ans = Math.min(ans,temp+1);
            }
        }
        dp.put(rest,ans==Integer.MAX_VALUE ? -1:ans);

        return dp.get(rest);
    }


}
