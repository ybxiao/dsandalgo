package com.example.dsandalgo.leecode;

import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * created on 2020/8/5.
 * time: 11:05
 * 模仿行走机器人
 * @author yibo.xiao
 */


public class Lee_874 {

    public static int robotSim(int[] commands, int[][] obstacles) {
        if (commands == null || commands.length < 1){
            return 0;
        }
        int x = 0;
        int y = 0;
        int ans = 0;
        int[][] Direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int direction = 0;
        for (int i = 0; i < commands.length; i++) {

            if (commands[i] > 0 ){
                int index = 0;
                int nextx = 0;
                int nexty = 0;
                while (index <commands[i]){
                    index ++;

                    nextx= x + Direction[direction][0];
                    nexty = y+ Direction[direction][1];
                    if (isContains(obstacles,nextx,nexty)) {break;}
                    x = nextx;
                    y = nexty;
                    ans = Math.max(ans,x*x +  y*y);

                }

            }else{
                direction = commands[i] == -1 ? (direction +1)%4 :(direction +3)%4 ;
            }


        }

        return ans;

    }

    private static boolean isContains(int[][] obs ,int i, int y) {
        if (obs == null || obs.length <1){
            return false;
        }
        for (int j = 0; j < obs.length; j++) {
            if (Arrays.equals(obs[j],new int[]{i,y})){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {

        //[4,-1,4,-2,4]
        //[[2,4]]
        //[-2,-1,8,9,6]
        //[[-1,3],[0,1],[-1,5],[-2,-4],[5,4],[-2,-3],[5,-1],[1,-1],[5,5],[5,2]]
        System.out.println(robotSim(new int[]{4,-1,4,-2,4},new int[][]{{2,4}}));
    }


}
