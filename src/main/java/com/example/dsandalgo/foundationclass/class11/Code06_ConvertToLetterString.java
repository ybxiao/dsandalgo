package com.example.dsandalgo.foundationclass.class11;

/**
 * 从左往右的尝试：
 * 例题：
 * 规定1和A对应、2和B对应、3和C对应...
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code06_ConvertToLetterString {

    public static int number(String str){
        if (str == null || str.length() ==0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }



    // str[0...i-1]已经转化完了，固定了
    // i之前的位置，如何转化已经做过决定了, 不用再关心
    // i... 有多少种转化的结果

    private static int process(char[] strs, int index) {
        if (index ==  strs.length){
            return 1;
        }
        if (strs[index] == 0){
            return 0;
        }
        if (strs[index] == 1 ){
            int res=  process(strs,index+1);
            if (index +2 < strs.length){
                res += process(strs,index+2);
            }
            return res;
        }
        if (strs[index] ==2){
            int res = process(strs,index+1);
            if (index+2<strs.length && strs[index+2] >=0 && strs[index+2]<6){
                res += process(strs,index+2);
            }
            return res;
        }
        return process(strs,index+1);
    }

    //动态规划求解
    public static int dpway(String str){
        if (str == null || str.length() ==0){
            return 0;
        }
        int n =  str.length();
        char[] chars = str.toCharArray();
        int[] dp = new int[n+1];
        dp[n] =1;
        for (int i = n-1; i >= 0; i--) {
            if (chars[i] == '0'){
                return 0;
            }
            if (chars[i] == '1'){
                int res=  dp[i+1];
                if (i +2 < n){
                    res += dp[i+2 ];
                }
                dp[i] = res;
            }
            if (chars[i] == '2'){
                int res = dp[i+1];
                if (i+2<n && chars[i+2] >= '0' && chars[i+2]<6){
                    res += dp[i+2];
                }
                dp[i] = res;
        }


    }
        return dp[0];

}
}
