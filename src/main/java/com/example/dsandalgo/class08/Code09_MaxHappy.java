package com.example.dsandalgo.class08;

import java.util.ArrayList;
import java.util.List;

/**
 * 派对的最大快乐值
 *
 * 员工信息的定义如下:
 * class Employee {
 *     public int happy; // 这名员工可以带来的快乐值
 *     List<Employee> subordinates; // 这名员工有哪些直接下级
 * }
 *
 * 派对的最大快乐值
 *  公司的每个员工都符合 Employee 类的描述。整个公司的人员结构可以看作是一棵标准的、 没有环的多叉树。
 *  树的头节点是公司唯一的老板。除老板之外的每个员工都有唯一的直接上级。
 *  叶节点是没有任何下属的基层员工(subordinates列表为空)，除基层员工外，每个员工都有一个或多个直接下级
 *
 * 派对的最大快乐值
 * 这个公司现在要办party，你可以决定哪些员工来，哪些员工不来，规则：
 * 1.如果某个员工来了，那么这个员工的所有直接下级都不能来
 * 2.派对的整体快乐值是所有到场员工快乐值的累加
 * 3.你的目标是让派对的整体快乐值尽量大
 * 给定一棵多叉树的头节点boss，请返回派对的最大快乐值。
 *
 *
 *  1 暴力解法
 *  2 套路求解
 */
public class Code09_MaxHappy {

    public static class Employee{
        public int happy;
        private List<Employee> nexts;

        public Employee(int v){
            this.happy =v;
            nexts = new ArrayList<>();
        }

    }

    public int maxHappy1(Employee boss){
        if (boss == null){
            return 0;
        }

        int m = process1(boss,false);

        return m;




    }

    private int process1(Employee boss, boolean b) {
        if (b){
            int i = boss.happy;
            for (int j = 0; j < boss.nexts.size(); j++) {
                i=i+process1(boss.nexts.get(j),false);
            }
            return i;
        }else{
            int p1 =0;
            int p2 =0;
            for (int j = 0; j < boss.nexts.size(); j++) {
                p1 += process1(boss.nexts.get(j),false);
                p2 += process1(boss.nexts.get(j),true);

            }
            return Math.max(p1,p2);
        }
    }


    public static class Info{
        //该节点来的时候 最大快乐值
        private int yes;
        //该节点不来的时候最大快乐值
        private int no ;
        public Info(int yes,int no){
            this.yes = yes;
            this.no = no;
        }

    }

    public int maxHappy2(Employee boss){
       if (boss ==null){
           return 0;
       }
       Info info = process2(boss);

       return Math.max(info.yes,info.no);


    }

    private Info process2(Employee boss) {
        if (boss.nexts.isEmpty()){
            return new Info(boss.happy,0);
        }
        int yes = 0;
        int no  =0;
        for(Employee e : boss.nexts){
            Info info = process2(e);
            no += Math.max(info.yes,info.no);
            yes += info.no;
        }
        return new Info(yes,no);
    }


}
