package com.example.dsandalgo.camp01.class05;

/**
 * manacher算法，俗称马拉车算法，主要是解决回文子串问题
 * 假设字符串str长度为N，想返回最长回文子串的长度
 * 时间复杂度O(N)
 *
 * 1）理解回文半径数组
 *
 * 2）理解所有中心的回文最右边界R，和取得R时的中心点C
 *
 * 3）理解   L…(i`)…C…(i)…R  的结构，以及根据i’回文长度进行的状况划分
 *
 * 4）每一种情况划分，都可以加速求解i回文半径的过程
 *
 * 关键概念的理解：
 * 回文半径、直径、覆盖区域
 * 回文最右边界，回文中心点
 * 回文半径数组
 *
 * 与KMP算法的类似： 使用已经找到的回文半径数组对后续匹配进行加速
 *
 *
 */
public class Code01_Manacher {

    public static int manacher(String s){
        //1. 边界判断
        if (s == null || s.length() == 0){
            return 0;
        }
        //2. 转化成manacher字符
        char[] str = manacherString(s);

        //3. 初始化回文右边界，回文中心，回文半径数组
        // 讲述中：R代表最右的扩成功的位置。中：最右的扩成功位置的，再下一个位置
        int R = -1;
        int C = -1;
        int[] pArray = new int[str.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != pArray.length; i++) {
            //pArray中 已知的回文半径大小赋值
            pArray[i] =  R > i ? Math.min(pArray[C * 2 - i],R-i) : 1;

            while (i + pArray[i] < str.length && i - pArray[i] > -1){
                if (str[i+pArray[i]] == str[i-pArray[i]]){
                    pArray[i] ++;
                }else{
                    break;
                }
            }
            if (pArray[i] > R){
                R = i+ pArray[i];
                C = i;
            }
            max = Math.max(max,pArray[i]);
        }
        return max -1;


    }

    private static char[] manacherString(String s) {
        char[] charArray = s.toCharArray();
        char[] res = new char[charArray.length *2 +1];

        for (int i = 0; i != res.length; i++) {
            res[i] = (i&1) == 0 ? '#':charArray[i/2];
        }
        return res;

    }



    //暴力解法 无加速
    public static int right(String s){
        if (s == null || s.length() == 0){
            return 0;
        }
        char[] str= manacherString(s);
        int max = 0;
        for (int i = 0; i != str.length; i++) {
            int L = i-1;
            int R = i+1;
            while (L >= 0 && R < str.length && str[L] == str[R]){
                L --;
                R ++;
            }
            max = Math.max(max,R-L-1);



        }

        return max/2 ;


    }



    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }



}
