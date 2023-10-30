package com.example.dsandalgo.aaaa.basic;

/**
 * 现有司机N*2人，调度中心会将所有司机平分给A、B两区域，i号司机去A可得收入为income[i][0]，去B可得收入为income[i][1]
 * 返回能使所有司机总收入最高的方案是多少钱?
 */
public class Drive {

    public static int getBestIncome(int[][] income) {
        if (income == null || income.length < 2 && (income.length & 1) != 0) {
            return 0;
        }
        int n = income.length;
        int m = n >> 1;

        return process(income, 0, m);

    }

    /**
     * @param income 固定参数
     * @param index
     * @param rest   决定去A区的司机个数
     * @return 从index... n上的司机做自由选择rest个司机去A区，返回所有司机获得的最大收入
     */
    private static int process(int[][] income, int index, int rest) {

        if (index == income.length) {
            return 0;
        }
        if (rest == 0) {
            return income[index][1] + process(income, index + 1, rest);
        }
        if (income.length - index == rest) {
            return income[index][0] + process(income, index + 1, rest - 1);
        }

        //当前index 去A
        int pa = income[index][0] + process(income, index + 1, rest - 1);
        //当前index  去B
        int pb = income[index][1] + process(income, index + 1, rest);

        return Math.max(pa, pb);


    }


    public static int getBestIncomeDp(int[][] income) {
        if (income == null || income.length < 2 || (income.length & 1) != 0) {
            return 0;
        }
        int n = income.length;
        int m = n >> 1;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = dp[i + 1][0] + income[i][0];
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 1; j <= m; j++) {
                if (n - i == j) {
                    dp[i][j] = dp[i + 1][j] + income[i][1];
                } else {
                    dp[i][j] = Math.max((dp[i + 1][j - 1] + income[i][0]), (dp[i + 1][j] + income[i][1]));
                }

            }
        }
        return dp[0][m];

    }


}
