package com.example.dsandalgo.aaaa.basic;

import java.util.Comparator;
import java.util.PriorityQueue;

//三维接雨水问题
public class TrappingRainWaterIICopy2023 {

    public static class Node {
        public int value;
        public int col;
        public int row;

        public Node(int v, int r, int c) {
            value = v;
            col = c;
            row = r;
        }

    }


    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) {
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;
        boolean[][] isEnter = new boolean[N][M];
        PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);

        for (int i = 0; i < M; i++) {
            isEnter[0][i] = true;
            heap.add(new Node(heightMap[0][i], 0, i));
        }
        for (int i = 0; i < N; i++) {
            isEnter[i][M - 1] = true;
            heap.add(new Node(heightMap[i][M - 1], i, M - 1));
        }
        for (int i = M - 1; i >= 0; i--) {
            isEnter[N - 1][i] = true;
            heap.add(new Node(heightMap[N - 1][i], N - 1, i));
        }
        for (int i = N - 1; i >= 0; i--) {
            isEnter[i][0] = true;
            heap.add(new Node(heightMap[i][0], i, 0));
        }
        int ans = 0;
        int max = 0;

        while (!heap.isEmpty()) {
            Node poll = heap.poll();
            max = Math.max(max, poll.value);
            int curR = poll.row;
            int curC = poll.col;
            if (curR > 0 && !isEnter[curR - 1][curC]) {
                isEnter[curR - 1][curC] = true;
                ans += Math.max(0, max - heightMap[curR - 1][curC]);
                heap.add(new Node(heightMap[curR - 1][curC], curR - 1, curC));
            }

            if (curC < M - 1 && !isEnter[curR][curC + 1]) {
                isEnter[curR][curC + 1] = true;
                ans += Math.max(0, max - heightMap[curR][curC + 1]);
                heap.add(new Node(heightMap[curR][curC + 1], curR, curC + 1));
            }
            if (curR < N - 1 && !isEnter[curR + 1][curC]) {
                isEnter[curR + 1][curC] = true;
                ans += Math.max(0, max - heightMap[curR + 1][curC]);
                heap.add(new Node(heightMap[curR + 1][curC], curR + 1, curC));
            }
            if (curC > 0 && !isEnter[curR][curC - 1]) {
                isEnter[curR][curC - 1] = true;
                ans += Math.max(0, max - heightMap[curR][curC - 1]);
                heap.add(new Node(heightMap[curR][curC - 1], curR, curC - 1));
            }
        }

        return ans;

    }


}
