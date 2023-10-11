package com.example.dsandalgo.aaaa.basic;

import java.util.*;

/**
 * 本题为leetcode原题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 * 有几个岛
 */
public class NumberOfIslands {

    public static int numIsIsland(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int isIsland = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '1') {
                    isIsland++;
                    infect(board, i, j);

                }

            }
        }
        return isIsland;
    }

    private static void infect(char[][] board, int i, int j) {
        if (i < 0 || i == board.length || j < 0 || j == board[0].length || board[i][j] != '1') {
            return;
        }
        board[i][j] = '0';
        infect(board, i + 1, j);
        infect(board, i - 1, j);
        infect(board, i, j + 1);
        infect(board, i, j - 1);

    }


    public static int numsIsIsland2(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        Dot[][] dots = new Dot[n][m];
        List<Dot> dotList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1') {
                    dots[i][j] = new Dot();
                    dotList.add(dots[i][j]);
                }
            }
        }
        UnionFind<Dot> unionFind = new UnionFind<>(dotList);

        for (int i = 1; i < n; i++) {
            if (board[i - 1][0] == '1' && board[i][0] == '1') {
                unionFind.union(dots[i - 1][0], dots[i][0]);
            }

        }
        for (int i = 1; i < m; i++) {
            if (board[0][i - 1] == '1' && board[0][i] == '1') {
                unionFind.union(dots[0][i - 1], dots[0][i]);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == '1') {
                    if (board[i][j - 1] == '1') {
                        unionFind.union(dots[i][j], dots[i][j - 1]);
                    }
                    if (board[i - 1][j] == '1') {
                        unionFind.union(dots[i][j], dots[i - 1][j]);
                    }
                }


            }

        }

        return unionFind.size();

    }

    public static class Dot {

    }


    public static class UnionFind<V> {
        //记录元素的代表节点
        public HashMap<V, V> fatherMap;
        //代表节点所属集合的元素个数
        public HashMap<V, Integer> sizeMap;

        public UnionFind(List<V> values) {
            for (V v :
                    values) {
                fatherMap.put(v, v);
                sizeMap.put(v, 1);

            }
        }

        public V findFather(V v) {
            Stack<V> stack = new Stack<>();

            while (v != fatherMap.get(v)) {
                v = fatherMap.get(v);
                stack.push(v);
            }
            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), v);
            }
            return v;
        }

        public boolean isSameFather(V a, V b) {
            return findFather(a) == findFather(b);
        }

        public void union(V a, V b) {
            V aFather = findFather(a);
            V bFather = findFather(b);
            if (aFather != bFather) {
                Integer sizeAFather = sizeMap.get(aFather);
                Integer sizeBFather = sizeMap.get(bFather);
                if (sizeAFather >= sizeBFather) {
                    fatherMap.put(bFather, aFather);
                    sizeMap.put(aFather, sizeBFather + sizeBFather);
                    sizeMap.remove(bFather);
                } else {
                    fatherMap.put(aFather, bFather);
                    sizeMap.put(bFather, sizeBFather + sizeBFather);
                    sizeMap.remove(aFather);
                }
            }

        }

        public int size() {
            return sizeMap.size();
        }

    }


}
