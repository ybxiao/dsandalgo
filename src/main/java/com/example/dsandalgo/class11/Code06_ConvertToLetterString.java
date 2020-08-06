package com.example.dsandalgo.class11;

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

    //0..index已经处理完毕
    //处理index往后的字符如何转换
    //

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
}
