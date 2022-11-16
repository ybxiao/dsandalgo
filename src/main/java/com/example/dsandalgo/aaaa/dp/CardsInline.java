package com.example.dsandalgo.aaaa.dp;

/**
 * 纸牌问题
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明（意味着玩家A和玩家B 都会做出对自己有利的选择，把差的情况留给对方）
 * 请返回最后获胜者的分数
 * <p>
 * <p>
 * 范围上的尝试模型。 定义两个函数，两个函数都是递归函数，
 * f(arr[],l,r)先手函数：代表如果一个玩家先手在arr[l...r]挑选纸牌能得到的最好分数
 * g(arr[],l r)后手函数：代表如果一个玩家后手在arr[l...r]挑选纸牌能得到的最好分数
 */
public class CardsInline {

    //返回最后获胜者的分数
    public static int win1(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int n = arr.length;
        int first = f(arr, 0, n - 1);
        int second = g(arr, 0, n - 1);
        return Math.max(first, second);

    }

    private static int f(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        int p1 = arr[l] + g(arr, l + 1, r);
        int p2 = arr[r] + g(arr, l, r - 1);
        return Math.max(p1, p2);
    }

    private static int g(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int p1 = f(arr, l + 1, r);
        int p2 = f(arr, l, r - 1);

        return Math.min(p1, p2);
    }

    public static int winV2(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //使用-1来标识，是不是计算过缓存
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }

        int first = fV2(arr, 0, n - 1, fmap, gmap);
        int second = gV2(arr, 0, n - 1, fmap, gmap);
        return Math.max(first, second);

    }

    private static int fV2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (fmap[l][r] != -1) {
            return fmap[l][r];
        }
        int ans;
        if (l == r) {
            ans = arr[l];
        } else {
            int p1 = arr[l] + gV2(arr, l + 1, r, fmap, gmap);
            int p2 = arr[r] + gV2(arr, l, r - 1, fmap, gmap);
            ans = Math.max(p1, p2);
        }
        fmap[l][r] = ans;
        return fmap[l][r];
    }

    private static int gV2(int[] arr, int l, int r, int[][] fmap, int[][] gmap) {
        if (gmap[l][r] != -1) {
            return gmap[l][r];
        }
        int ans;
        if (l == r) {
            ans = 0;
        } else {
            int p1 = fV2(arr, l + 1, r, fmap, gmap);
            int p2 = fV2(arr, l, r - 1, fmap, gmap);
            ans = Math.min(p1, p2);
        }
        gmap[l][r] = ans;
        return gmap[l][r];
    }

    /**
     * （l）  0 1 2 3 4 5 6 （r）
     * 0    # # # # # # #
     * 1    #
     * 2    #
     * 3    #
     * 4    #
     * 5    #
     * 6    #
     *
     * @param arr
     * @return
     */
    public static int winV3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return -1;
        }
        int n = arr.length;
        int[][] fmap = new int[n][n];
        int[][] gmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            fmap[i][i] = arr[i];
        }
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1,k = 0; j < n; j++, k++) {
                System.out.println("i :" +k + "---j: " +j);
                gmap[k][j] = Math.min(fmap[k + 1][j], fmap[k][j - 1]);
                fmap[k][j] = Math.max((gmap[k + 1][j] + arr[k]), (gmap[k][j - 1] + arr[j]));
            }
        }

        /*System.out.println("======================");
        for (int startCol = 1; startCol < n; startCol++) {
            int L = 0;
            int R = startCol;
            while (R < n) {
                System.out.println("i :" +L + "---j: " +R);
                fmap[L][R] = Math.max(arr[L] + gmap[L + 1][R], arr[R] + gmap[L][R - 1]);
                gmap[L][R] = Math.min(fmap[L + 1][R], fmap[L][R - 1]);
                L++;
                R++;
            }
        }*/
        return Math.max(fmap[0][n - 1], gmap[0][n - 1]);
    }


    public static void main(String[] args) {
        int[] arr = {5, 7, 4, 5, 8, 1, 6, 0, 3, 4, 6, 1, 7};
        System.out.println(win1(arr));
        System.out.println(winV2(arr));
        System.out.println(winV3(arr));

    }


}
