package com.example.dsandalgo.algo;

/**
 * created on 2020/8/24.
 * time: 14:03
 * kmp算法尝试编写
 *
 *
 * 返回match串在 source串中完全匹配的首字符的位置
 * 如果不存在返回 -1
 * @author yibo.xiao
 */


public class T_KMP {

    public static int getMatchIndex(String source,String match){
        if (source == null || match == null || source.length() < match.length()) {
            return -1;
        }
        char[] s = source.toCharArray();
        char[] m = match.toCharArray();
        int[] nextArray = getNextArray(match);
        int i = 0;
        int j = 0;
        while (i < s.length && j < m.length){
            if (s[i] == m[j]){
                i++;
                j++;
            }else if(nextArray[j] == -1){
                i++;
            }else{
                j = nextArray[j];
            }
        }


        return  j == m.length? i-j:-1;
    }


    public static int[] getNextArray(String match){
        int[] ans = new int[match.length()];
        char[] mch = match.toCharArray();
        ans[0] = -1;
        ans[1] = 0;
        int i =  2;
        int j = 0;
        while (i < match.length()){
            while (j != -1) {
                if (mch[i-1]  ==  mch[j]){
                    ans[i] = ++j ;
                }else {
                    j = ans[j];
                }
            }
            ans[i] = 0;
            i++;
        }

        return ans;
    }


    public static void main(String[] args) {
        System.out.println(getMatchIndex("abcc","bc"));

        System.out.println(getNextArray("bc")[0]);
        System.out.println(getNextArray("bc")[1]);

    }
}
