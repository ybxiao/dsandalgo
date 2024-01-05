package com.example.dsandalgo.aaaa.basic;


/**
 * manacher算法：求一个字符串的最长回文子串（子串的含义代表必须是连续的）
 * 基本求解思路：中心扩散法，遍历字符串中的每个字符，以当前字符未中心向左右两边扩散，
 * 看能够扩散多远，即是以当前字符为中心的回文子串长度，不断遍历更新结果，求出最长的回文子串。
 * 此方法有个缺陷，当客观的最长回文子串并非以字符串中的字符未中心时，会错过最优结果，
 * 因此需要先转化成manarch字符串，再进行求解。manarch字符串：给字符串间隔添加一个字符，使其
 * 变成2n+1长度的字符串。@manacherString(String str)
 * 时间复杂度O(N^2)
 * <p>
 * manacher算法加速：O(n)
 * 主要是应用已经求过回文半径数组的字符，对以后得扩散加速。
 * 三个基础概念：
 * 回文半径数组:pArray[i] 代表以i为中心，向左右两边扩散的回文字符串的半径有多长
 * 回文最右边界：R，代表此时回文子串的最优边界
 * 回文中心：C，代表取的回文最右边界时的回文中心
 * 步骤：（主要是讨论i的对称位置i`的回文半径大小，来对i位置的求解进行加速）
 * 1、遍历字符串中的每个字符，在以其为中心，向两边扩散，求回文子串长度时，利用关于c的对称点
 * 进行加速。
 * 假如当前来到了字符串 i位置，其关于c的对称点为i`位置，分以下几种情况
 * a) i在r右侧，此时无法加速pArray[i] 至少==1，当前字符本身的长度。
 * b）i在r左侧或者和r重合，此时根据pArray[i`]的状况，分3中情况讨论
 * 1）pArray[i`] 的长度，在以c为中心的回文子串中，假设r关于c的对称点为l，即在（l，r）范围中，
 * 此时pArray[i] == pArray[i`]
 * 2）pArray[i`] 的长度 正好等于l到c的长度，此时pArray[i] 至少为pArray[i`]
 * 3）pArray[i`] 的长度 超过了l到c的长度，此时pArray[i]== pArray[i`]
 */
public class Manacher2023 {

    public static int manachar(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        String manachrString = manacharString(str);
        char[] charArray = manachrString.toCharArray();
        int[] pArray = new int[manachrString.length()];
        int r = -1;
        int c = -1;
        int ans = 0;
        for (int i = 0; i < charArray.length; i++) {
            pArray[i] = r > i ? Math.min(pArray[c * 2 - i], r - i) : 1;
            while (i + pArray[i] < charArray.length && i - pArray[i] > -1) {
                if (charArray[i + pArray[i]] == charArray[i - pArray[i]]) {
                    pArray[i]++;

                } else {
                    break;
                }
                ans = Math.max(ans, pArray[i]);
                if (i + pArray[i] > r) {
                    r = pArray[i] + i;
                    c = i;
                }
            }

        }
        return ans - 1;


    }

    private static String manacharString(String str) {
        char[] sourceArray = str.toCharArray();
        char[] targetArray = new char[sourceArray.length * 2 + 1];
        int index = 0;
        for (int i = 0; i < targetArray.length; i++) {
            targetArray[i] = ((i & 1) == 0) ? '#' : sourceArray[index++];
        }
        return targetArray.toString();

    }


}
