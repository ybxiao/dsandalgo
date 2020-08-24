package com.example.dsandalgo.foundationclass.class07;

/**
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，
 * 即折痕突起的方向指向纸条的背面。 如果从纸条的下边向上方连续对折2次，压出折痕后展开，
 * 此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次。 请从上到下打印所有折痕的方向。
 * 例如:N=1时，打印: down N=2时，打印: down down up
 */
public class Code08_PaperFolding {


    public static void printAllFolds(int N){

        if (N <= 0){
            return;
        }
        process(1,N,true);


    }

    /**
     * 二叉树 递归序 每个节点都会被经过3次
     * 模拟二叉树的中序遍历过程
     * 每次折叠都会形成 二叉树左孩子凹 右孩子凸
     *
     * @param i
     * @param n
     * @param b
     */
    private static void process(int i, int n, boolean b) {
        if (i > n){
            return;
        }
        process(i+1,n,true);
        System.out.println(b==true ? "凹":"凸");
        process(i+1,n,false);
    }

    public static void main(String[] args) {
        printAllFolds(2);
    }

}
