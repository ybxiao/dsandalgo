package com.example.dsandalgo.aaaa.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 */
public class QueueReconstructionByHeight {

    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        List<Unit> peoples = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            peoples.add(new Unit(people[i][1], people[i][0]));
        }
        Collections.sort(peoples, new UnitComparator());
        int[][] res = new int[n][2];
        for (int i = 0; i < peoples.size(); i++) {
            res[i][0] = peoples.get(i).h;
            res[i][1] = peoples.get(i).k;
        }

        return res;


    }

    public static class Unit {
        //代表前面的人数
        public int k;
        //代表高度
        public int h;

        public Unit(int k, int h) {
            this.k = k;
            this.h = h;
        }

    }

    public class UnitComparator implements Comparator<Unit> {
        @Override
        public int compare(Unit o1, Unit o2) {
            return o1.h == o2.h ? (o1.k - o2.k) : (o1.h - o2.h);
        }
    }
}
