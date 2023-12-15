package com.example.dsandalgo.aaaa.basic;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * 大楼轮廓线问题
 * https://leetcode.com/problems/the-skyline-problem/
 */
public class TheSkylineProblem {

    //抽象一个 Node 类，一个高楼抽象成两个 Node
    public class Node {
        public int x;
        //高楼的坐标起点 isAdd == true 代表轮廓在 x 点增加了高度
        //高楼的坐标终点 isAdd == false 代表轮廓在x 点减少了高度
        public boolean isAdd;
        public int h;

        public Node(int xOrdinate, boolean isAdd, int height) {
            x = xOrdinate;
            isAdd = isAdd;
            h = height;
        }

    }

    public Comparator<Node> nodeComparator = new Comparator<Node>() {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x;
        }
    };

    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<Node> lists = new ArrayList<>(buildings.length * 2);
        for (int i = 0; i < buildings.length; i++) {
            lists.add(new Node(buildings[i][0], true, buildings[i][2]));
            lists.add(new Node(buildings[i][1], false, buildings[i][2]));
        }
        lists.sort(nodeComparator);
        TreeMap<Integer, Integer> heightTimesMap = new TreeMap<>();
        TreeMap<Integer, Integer> maxHeightMap = new TreeMap<>();

        for (int i = 0; i < lists.size(); i++) {
            Node node = lists.get(i);
            if (node.isAdd) {
                if (heightTimesMap.containsKey(node.x)) {
                    heightTimesMap.put(node.x, heightTimesMap.get(node.x) + 1);
                } else {
                    heightTimesMap.put(node.x, 1);
                }
            } else {
                if (heightTimesMap.get(node.x) == 1) {
                    heightTimesMap.remove(node.x);
                } else {
                    heightTimesMap.put(node.x, heightTimesMap.get(node.x) - 1);
                }
            }
            if (heightTimesMap.isEmpty()) {
                maxHeightMap.put(node.x, 0);
            } else {
                maxHeightMap.put(node.x, heightTimesMap.lastKey());
            }

        }
        List<List<Integer>> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry :
                maxHeightMap.entrySet()) {
            Integer curX = entry.getKey();
            Integer value = entry.getValue();
            if (res.isEmpty() || res.get(res.size() - 1).get(1) != value) {
                res.add(new ArrayList<>(Arrays.asList(curX, value)));
            }

        }
        return res;


    }

}
