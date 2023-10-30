package com.example.dsandalgo.aaaa.basic;

/**
 * 贩卖机只支持硬币支付，且收退都只支持10 ，50，100三种面额
 * 一次购买只能出一瓶可乐，且投钱和找零都遵循优先使用大钱的原则
 * 需要购买的可乐数量是m，其中手头拥有的10、50、100的数量分别为a、b、c，可乐的价格是x(x是10的倍数)
 * 请计算出需要投入硬币次数
 */
public class Cola {


    /*
     * 买饮料 时间限制： 3000MS 内存限制： 589824KB 题目描述：
     * 游游今年就要毕业了，和同学们在携程上定制了日本毕业旅行。愉快的一天行程结束后大家回到了酒店房间，这时候同学们都很口渴，
     * 石头剪刀布选出游游去楼下的自动贩卖机给大家买可乐。 贩卖机只支持硬币支付，且收退都只支持10 ，50，100
     * 三种面额。一次购买行为只能出一瓶可乐，且每次购买后总是找零最小枚数的硬币。（例如投入100圆，可乐30圆，则找零50圆一枚，10圆两枚）
     * 游游需要购买的可乐数量是 m，其中手头拥有的 10,50,100 面额硬币的枚数分别是 a,b,c，可乐的价格是x(x是10的倍数)。
     * 如果游游优先使用大面额购买且钱是够的情况下,请计算出需要投入硬币次数？ 输入描述 依次输入， 需要可乐的数量为 m 10元的张数为 a 50元的张数为 b
     * 100元的张树为 c 1瓶可乐的价格为 x 输出描述 输出当前金额下需要投入硬币的次数
     * 例如需要购买2瓶可乐，每瓶可乐250圆，手里有100圆3枚，50圆4枚，10圆1枚。 购买第1瓶投递100圆3枚，找50圆 购买第2瓶投递50圆5枚
     * 所以是总共需要操作8次金额投递操作 样例输入 2 1 4 3 250 样例输出 8
     */

    /**
     * @param m 需要买的可乐数目
     * @param a 10元钱币的个数
     * @param b 50元钱币的个数
     * @param c 100元钱币的个数
     * @param x 一瓶可乐的售价
     * @return 买到m瓶可乐，所需要的投币次数
     * 暴力尝试方法，关键是设计两个递归函数
     */
    public static int right(int m, int a, int b, int c, int x) {
        int[] qian = {100, 50, 10};
        int[] zhang = {c, b, a};
        int puts = 0;
        while (m > 0) {
            int cur = buy(qian, zhang, x);
            if (cur == -1) {
                return -1;
            }
            puts += cur;
            m--;
        }

        return puts;

    }

    /**
     * @param qian  对应的钱币种类
     * @param zhang 对应每种钱币的张数
     * @param x     一瓶可乐的价钱
     * @return 当前钱币状况下，购买一瓶可乐所需的最小投币次数
     * -1 代表怎么都搞不定
     */
    public static int buy(int[] qian, int[] zhang, int x) {
        int first = -1;
        for (int i = 0; i < qian.length; i++) {
            if (zhang[i] != 0) {
                first = i;
                break;
            }
        }
        if (first == -1) {
            return -1;
        }

        if (qian[first] >= x) {
            zhang[first]--;
            giveRest(qian, zhang, first + 1, qian[first] - x, 1);
            return 1;
        } else {
            zhang[first]--;
            int next = buy(qian, zhang, x - qian[first]);
            if (next == -1) {
                return -1;
            }
            return next + 1;
        }

    }

    public static void giveRest(int[] qian, int[] zhang, int i, int rest, int times) {
        for (; i < 3; i++) {
            zhang[i] = rest / qian[i] * times;
            rest = rest % qian[i];

        }

    }

    public static int putTimes(int m, int a, int b, int c, int x) {
        int[] zhangs = {c, b, a};
        int[] qian = {100, 50, 10};
        int puts = 0;
        int preQianRest = 0;
        int preQianZhang = 0;

        for (int i = 0; i < 3 & m > 0; i++) {
            int curQianFirstBuyZhang = (x - preQianRest + qian[i] - 1) / qian[i];
            if (zhangs[i] >= curQianFirstBuyZhang) {
                giveRest(qian, zhangs, i + 1, preQianRest + curQianFirstBuyZhang * qian[i] - x, curQianFirstBuyZhang);
                zhangs[i] -= curQianFirstBuyZhang;
                puts += curQianFirstBuyZhang + preQianZhang;
                m--;
            } else {
                preQianRest += zhangs[i] * qian[i];
                preQianZhang += zhangs[i];
            }
            //当前钱币买一瓶需要多少张
            int curQianBuyOneZhang = (x + qian[i] - 1) / qian[i];
            //当前钱币一共可以买多少瓶
            int curQianBuyNums = Math.min(zhangs[i] / curQianBuyOneZhang, m);
            //买一瓶剩的前
            int buyOneRest = curQianBuyOneZhang * qian[i] - x;
            giveRest(qian, zhangs, i + 1, buyOneRest, curQianBuyNums);

            puts += curQianBuyOneZhang * curQianBuyNums;
            m -= curQianBuyNums;
            zhangs[i] -= curQianBuyOneZhang * curQianBuyNums;
            preQianZhang = zhangs[i];
            preQianRest = zhangs[i] * qian[i];
            
        }
        return puts;


    }

}
