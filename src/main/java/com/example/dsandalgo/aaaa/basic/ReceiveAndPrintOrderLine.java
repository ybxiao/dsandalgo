package com.example.dsandalgo.aaaa.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * 已知一个消息流会不断地吐出整数1~N，但不一定按照顺序依次吐出，如果上次打印的序号为i，
 * 那么当i+1出现时请打印i+1及其之后接收过的并且连续的所有数，直到1~N全部接收并打印完，
 * 请设计这种接收并打印的结构
 */
public class ReceiveAndPrintOrderLine {
    public static class Node {
        public String info;
        public Node next;

        public Node(String info) {
            this.info = info;
        }
    }

    public static class MessageBox {
        static Map<Integer, Node> headMap;
        static Map<Integer, Node> tailMap;
        static int waitPoint;

        public MessageBox() {
            tailMap = new HashMap<Integer, Node>();
            headMap = new HashMap<Integer, Node>();
            waitPoint = 1;
        }

        public static void receive(int num, String desc) {
            Node cur = new Node(desc);
            headMap.put(num, cur);
            tailMap.put(num, cur);

            if (tailMap.containsKey(num - 1)) {
                tailMap.get(num - 1).next = cur;
                tailMap.remove(num - 1);
                headMap.remove(num);
            }
            if (headMap.containsKey(num + 1)) {
                cur.next = headMap.get(num + 1);
                headMap.remove(num + 1);
                tailMap.remove(num);
            }

            if (num == waitPoint) {
                print(num);
            }

        }

        public static void print(int num) {
            Node node = headMap.get(num);
            headMap.remove(num);
            while (node != null) {
                System.out.println(node.info);
                node = node.next;
                num++;
            }
            tailMap.remove(num - 1);

        }

    }


}
