package com.example.dsandalgo.aaaa.basic;

public class MinSteps {

    /**
     * 一个数组中只有两种字符'G'和'B'，
     * 可以让所有的G都放在左侧，所有的B都放在右侧
     * 或者可以让所有的G都放在右侧，所有的B都放在左侧
     * 但是只能在相邻字符之间进行交换操作，请问请问至少需要交换几次，
     *
     * @param s
     * @return
     */
    public static int minStep(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        char[] array = s.toCharArray();
        int gi = 0;
        int step1 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'G') {
                step1 += i - gi;
                gi++;
            }
        }

        int bi = 0;
        int step2 = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'B') {
                step2 += i - gi;
                bi++;
            }
        }


        return Math.min(step1, step2);
    }
}
