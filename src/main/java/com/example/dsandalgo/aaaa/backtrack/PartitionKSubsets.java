package com.example.dsandalgo.aaaa.backtrack;

/**
 * 给你输入一个数组 nums 和一个正整数 k，
 * 请你判断 nums 是否能够被平分为元素和相同的 k 个子集
 */
public class PartitionKSubsets {


    public static boolean canPartitionKSubsets(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length;
        if (k > n) return false;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        if (sum % k != 0) return false;
        boolean[] used = new boolean[n];

        return backTrack(k, 0, arr, 0, used, sum / k);


    }

    /**
     * @param k
     * @param bucket
     * @param arr
     * @param start
     * @param used
     * @param target
     * @return arr数组是否能够被平分成元素之和相同的k个子集
     */
    private static boolean backTrack(int k, int bucket, int[] arr, int start, boolean[] used, int target) {
        // k 个桶，是不包含0号桶的。
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            return backTrack(k - 1, 0, arr, 0, used, target);
        }

        for (int i = start; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }
            if (bucket + arr[i] > target) {
                continue;
            }

            used[i] = true;
            bucket += arr[i];
            if (backTrack(k, bucket, arr, start + 1, used, target)) {
                return true;
            }
            ;
            used[i] = false;
            bucket -= arr[i];

        }

        return false;
    }


    //1、使用备忘录减少重复计算
    //2、使用位运算技巧简化备忘录状态生成
    // 数组长度不能超过32，使用int类型各个bit上 1/0来表示是否元素已经被使用
    public static boolean canPartitionKSubsets2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length;
        if (k > n) return false;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }
        if (sum % k != 0) return false;
        int used = 0;

        return backTrack2(k, 0, arr, 0, used, sum / k);


    }

    private static boolean backTrack2(int k, int bucket, int[] arr, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            return backTrack2(k - 1, 0, arr, 0, used, target);
        }
        for (int i = start; i < arr.length; i++) {
            if ((used >> i & 1) == 1) {
                continue;
            }
            if (bucket + arr[i] > target) {
                continue;
            }
            used |= 1 << i;
            bucket += arr[i];
            if (backTrack2(k, bucket, arr, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;
            bucket -= arr[i];

        }

        return false;
    }

}
