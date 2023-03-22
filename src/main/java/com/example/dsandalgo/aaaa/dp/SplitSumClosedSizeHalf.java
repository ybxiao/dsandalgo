package com.example.dsandalgo.aaaa.dp;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回最接近的情况下，较小集合的累加和
 * <p>
 * 和上一题类似，只不过又加了子数组个数的限制
 */
public class SplitSumClosedSizeHalf {

    public static int right(int[] nums) {
        //边界过滤
        if (nums == null || nums.length < 2) {
            return 0;
        }
        // 计算数组的累加和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        if ((nums.length & 1) == 0) {
            return process(0, nums.length / 2, sum / 2, nums);
        } else {
            return Math.max(process(0, nums.length / 2, sum / 2, nums), process(0, nums.length / 2 + 1, sum / 2, nums));
        }


    }

    /**
     * 在nums[index...]上自由选择picks个数，是累加和尽量接近但是不超过rest的最大累加和
     *
     * @param index
     * @param picks
     * @param rest
     * @param nums
     * @return
     */
    private static int process(int index, int picks, int rest, int[] nums) {
        // 用-1 标记一种无效的选择
        if (index == nums.length) {
            return picks == 0 ? 0 : -1;
        }
        int p1 = -1;
        //我选了当前位置的数
        if (rest - nums[index] >= 0) {
            p1 = process(index + 1, picks - 1, rest - nums[index], nums);
            if (p1 != -1) {
                p1 += nums[index];
            }
        }
        // 我不选当前位置的数
        int p2 = process(index + 1, picks, rest, nums);

        return Math.max(p1, p2);

    }

    private static int dp(int[] nums) {
        //边界过滤
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        sum /= 2;
        int n = nums.length;
        int picks = (n + 1) >> 1;
        int[][][] dp = new int[n + 1][picks + 1][sum + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < picks + 1; j++) {
                for (int k = 0; k < sum + 1; k++) {
                    //初始化dp数组的值，用-1表示当前组合无效
                    dp[i][j][k] = -1;
                }
            }
        }

        for (int j = 0; j < sum + 1; j++) {
            dp[n][0][j] = 0;
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int pick = 0; pick < picks + 1; pick++) {
                for (int j = 0; j < sum + 1; j++) {
                    dp[i][pick][j] = dp[i + 1][pick][j];
                    if (j >= nums[i] && pick - 1 >= 0) {
                        if (dp[i + 1][pick - 1][j - nums[i]] != -1) {
                            dp[i][pick][j] = Math.max(dp[i][pick][j], (dp[i + 1][pick - 1][j - nums[i]] + nums[i]));
                        }
                    }

                }
            }

        }

        if ((nums.length & 1) == 0) {
            return dp[0][n / 2][sum];
        } else {
            return Math.max(dp[0][n / 2][sum], dp[0][n / 2 + 1][sum]);
        }

    }

    /**
     * @param index 0 ...index位置数可以自由选择picks，返回累加和不超过rest的最大值。
     * @param picks
     * @param rest
     * @param arr
     * @return
     */
    private static int process1(int index, int picks, int rest, int[] arr) {
        if (index == 0) {
            //其中 -1 代表此种方案无效
            return picks == 1 ? (arr[index] <= rest ? arr[0] : -1) : -1;
        }
        if (picks == 0) {
            return 0;
        } else {
            //还有数可以选
            int p1 = -1;
            if (rest - arr[index] >= 0) {
                p1 = process1(index - 1, picks - 1, rest - arr[index], arr) + arr[index];
            }
            int p2 = process1(index - 1, picks, rest, arr);
            return Math.max(p1, p2);

        }

    }


    public static int[] randomArray(int maxLen, int maxV) {
        int n = (int) (Math.random() * maxLen) + 1;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (int) (Math.random() * maxV) + 1;
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int maxV = 20;
        int maxL = 20;
        int testTimes = 1000;
        System.out.println("begin");
        for (int i = 0; i < testTimes; i++) {
            int[] ints = randomArray(maxL, maxV);
            int ans1 = right(ints);
            int ans2 = dp(ints);
            if (ans1 != ans2) {

                System.out.println(ans1);
                System.out.println(ans2);
                printArray(ints);
                System.out.println("oops");
                break;
            }
        }
        System.out.println("over");
    }
}
