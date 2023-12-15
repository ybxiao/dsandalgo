package com.example.dsandalgo.aaaa.basic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TrappingRainWaterII {


    public class Node {
        public int v;
        public int row;
        public int col;

        public Node(int v, int r, int c) {
            this.v = v;
            this.row = r;
            this.col = c;
        }
    }

    public int trapRainWater(int[][] heightMatrix) {
        if (heightMatrix == null || heightMatrix.length == 0 || heightMatrix[0] == null || heightMatrix[0].length == 0) {
            return 0;
        }
        int m = heightMatrix.length;
        int n = heightMatrix[0].length;
        //计算过的节点，为true
        boolean[][] sets = new boolean[m][n];
        PriorityQueue<Node> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a.v));
        // 初始化堆
        for (int col = 0; col < n; col++) {
            sets[0][col] = true;
            heap.add(new Node(heightMatrix[0][col], 0, col));
        }
        for (int row = 1; row < m; row++) {
            sets[row][n - 1] = true;
            heap.add(new Node(heightMatrix[row][n - 1], row, n - 1));
        }
        for (int col = n - 2; col > 0; col--) {
            sets[m - 1][col] = true;
            heap.add(new Node(heightMatrix[m - 1][col], m - 1, col));
        }
        for (int row = m - 2; row > 0; row--) {
            sets[row][0] = true;
            heap.add(new Node(heightMatrix[row][0], row, 0));
        }
        int ans = 0;
        int max = 0;
        while (!heap.isEmpty()) {
            Node poll = heap.poll();
            max = Math.max(max, poll.v);
            int currRow = poll.row;
            int currCol = poll.col;
            if (currRow > 0 && !sets[currRow - 1][currCol]) {
                ans += Math.max(heightMatrix[currRow - 1][currCol] - max, 0);
                sets[currRow - 1][currCol] = true;
                heap.add(new Node(heightMatrix[currRow - 1][currCol], currRow - 1, currCol));
            }


            if (currRow < m - 1 && !sets[currRow + 1][currCol]) {
                ans += Math.max(heightMatrix[currRow + 1][currCol] - max, 0);
                sets[currRow + 1][currCol] = true;
                heap.add(new Node(heightMatrix[currRow + 1][currCol], currRow + 1, currCol));
            }
            if (currCol > 0 && !sets[currRow][currCol - 1]) {
                ans += Math.max(heightMatrix[currRow][currCol - 1] - max, 0);
                sets[currRow][currCol - 1] = true;
                heap.add(new Node(heightMatrix[currRow][currCol - 1], currRow, currCol - 1));
            }
            if (currCol < n - 1 && !sets[currRow][currCol + 1]) {
                ans += Math.max(heightMatrix[currRow][currCol + 1] - max, 0);
                sets[currRow][currCol + 1] = true;
                heap.add(new Node(heightMatrix[currRow][currCol + 1], currRow, currCol + 1));
            }
        }

        return ans;
    }


}
