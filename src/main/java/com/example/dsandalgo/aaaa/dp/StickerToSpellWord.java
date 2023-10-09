package com.example.dsandalgo.aaaa.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr，出现的字符都是小写英文
 * arr每一个字符串，代表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来
 * 返回需要至少多少张贴纸可以完成这个任务
 * 例子：str= "babac"，arr = {"ba","c","abcd"}
 * ba + ba + c  3  abcd + abcd 2  abcd+ba 2
 * 所以返回2
 */
public class StickerToSpellWord {

    public static int minSticker1(String[] stickers, String target) {
        int ans = process1(stickers, target);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 尝试
    // 从第一张贴纸开始试
    // 先试使用一张第一张贴纸的情况，使用二张第一张贴纸的情况，一直到 n张第一张贴纸的情况
    // 当前贴纸完全不能减少剩余字符串字符的时候，停止尝试，换另一张贴纸
    private static int process1(String[] stickers, String target) {
        // 无效参数过滤，如果
        if (target.length() == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (String first :
                stickers) {

            String rest = minus(target, first);
            if (rest.length() != target.length()) {
                min = Math.min(min, process1(stickers, rest));
            }
        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }

    private static String minus(String target, String first) {
        char[] targetChar = target.toCharArray();
        char[] firstChar = first.toCharArray();
        //词频统计数组
        int[] count = new int[26];
        for (int i = 0; i < targetChar.length; i++) {
            count[targetChar[i] - 'a']++;
        }
        for (int i = 0; i < firstChar.length; i++) {
            count[firstChar[i] - 'a']--;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                sb.append((char) (i + 'a'));
                count[i]--;
            }
        }
        return sb.toString();
    }

    //优化点，使用词频统计来
    // a) 先统计贴纸的词频统计，使用一个二位数组即可int[N][26]
    // b) 然后按照词频去尝试
    public static int minSticker2(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickersC = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] chars = stickers[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                stickersC[i][chars[j] - 'a']++;
            }
        }
        return process2(stickersC, target);

    }

    private static int process2(int[][] stickersC, String target) {
        if (target.length() <= 0) {
            return 0;
        }
        char[] targetChars = target.toCharArray();
        int[] targetCount = new int[26];
        for (int i = 0; i < targetChars.length; i++) {
            targetCount[targetChars[i] - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        int n = stickersC.length;
        for (int i = 0; i < n; i++) {
            int[] tempSticker = stickersC[i];
            if (tempSticker[targetChars[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tempSticker[j] > 0) {
                        int num = targetCount[j] - tempSticker[j];
                        for (int k = 0; k < num; k++) {
                            sb.append((char) (tempSticker[j] + 'a'));
                        }
                    }
                    min = Math.min(min, process2(stickersC, sb.toString()));

                }
            }


        }
        return min + (min == Integer.MAX_VALUE ? 0 : 1);
    }


    // 记忆化搜索的方法
    // 从暴力尝试 -> 记忆化搜索 -> 动态规划
    // 当针对每一个格子里面的值求解有枚举行为时，可以进行严格位置依赖的的分析并想办法优化枚举行为
    // 其余情况记忆化搜索和动态规划的时间复杂度是一样的
    public static int minSticker3(String[] stickers, String target) {
        int n = stickers.length;
        int[][] stickersC = new int[n][26];
        for (int i = 0; i < n; i++) {
            char[] chars = stickers[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                stickersC[i][chars[j] - 'a']++;
            }
        }
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return process3(stickersC, target, dp);


    }

    private static int process3(int[][] stickersC, String target, Map<String, Integer> dp) {
        if (dp.containsKey(target)) {
            return dp.get(target);
        }
        char[] targetChars = target.toCharArray();

        int[] targetCount = new int[26];
        for (int i = 0; i < targetChars.length; i++) {
            targetCount[targetChars[i] - 'a']++;
        }

        int min = Integer.MAX_VALUE;
        int n = stickersC.length;
        for (int i = 0; i < n; i++) {
            int[] tempSticker = stickersC[i];
            if (tempSticker[targetChars[0] - 'a'] > 0) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < 26; j++) {
                    if (tempSticker[j] > 0) {
                        int num = targetCount[j] - tempSticker[j];
                        for (int k = 0; k < num; k++) {
                            sb.append((char) (tempSticker[j] + 'a'));
                        }
                    }
                    min = Math.min(min, process3(stickersC, sb.toString(), dp));

                }
            }


        }
        int ans = min + (min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target, ans);
        return ans;
    }


    public static void main(String[] args) {
        int a = 3;
        char b = (char) a;
        System.out.println(b);
        System.out.println((char) a);
    }

}
