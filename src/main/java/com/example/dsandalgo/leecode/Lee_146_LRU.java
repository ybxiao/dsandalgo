package com.example.dsandalgo.leecode;

import javax.swing.plaf.IconUIResource;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

public class Lee_146_LRU {


    public class LRUCache{

        private DNode head;
        private DNode tail;

        private HashMap<Integer,DNode> valueMap;

        private int size;

        private int limit;

        public LRUCache(int capacity){

            this.size =  0;
            this.limit = capacity;
            valueMap = new HashMap(capacity);
            head = new DNode(-1);
            tail =  head;


        }


        int get(int key) {
            if (!valueMap.containsKey(key)){
                return Integer.MIN_VALUE;
            }
            DNode dNode = valueMap.get(key);
            DNode pre =  dNode.last;
            DNode pos = dNode.last;
            pre.next = pos;
            pos.last = pre;
            tail.next = dNode;
            dNode.last = tail;
            dNode.next = null;
            size --;
            return dNode.val;

        }

        void put(int key, int value) {
            if (size == limit){
               head = head.next;
               head.last = null;
               size --;
            }
            DNode cur = new DNode(value);
            valueMap.put(key,cur);
            if (size == 0){
                head = cur;
                tail = cur;
            }else{
                cur.next =  head;
                cur.last = null;
                head.last =  cur;
                head =  cur;
            }


            size ++;

        }

    }


    public class DNode{
        private int val;
        private DNode last;
        private DNode next;
        public DNode(int val){
            this.val = val;
        }


    }


}
