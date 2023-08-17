package com.example.dsandalgo.aaaa.basic;

/**
 * 前缀树
 * 前缀树的节点有三个属性
 * pass : 有多少个元素经过了当前节点
 * end：有多少个元素以当前节点结束
 * next[]：代表当前节点到下一节点的路
 * 初始化过程：（先初始化节点）
 * 1） 初始化一个前缀树，初始化根节点
 * 2)  插入一个节点（insert）
 * 2.1) 当前节点来到root节点，把待插入节点转成字符数组
 * 2.1) 遍历待插入节点转成的字符数组，如果当前节点nexts包含待插入元素当前字符，就把nexts[x]对应的节点的pass++，当前节点同时指向nexts[x]节点
 * 2.2) 如果当前节点nexts不包含待插入元素当前字符，就新建一个Node，放入到当前节点的nexts中，新Node的pass++，同时当前节点指向新Node
 * 2.3) 一直到字符数组为空，此时的的当前Node，pass++，end++，插入完成。
 * 3) 查询一个元素在前缀树中出现过几次（search）
 * 3.1) 当前节点来到root节点，把待查询元素转成字符数组
 * 3.2) 遍历字符数组，看当前节点nexts是否包含当前字符的路，如果包含，当前节点来到nexts[x]，如果不包含，说明当前元素在树中不存在，直接返回0次
 * 3.3) 遍历完数组，如果过程中没有直接返回，此时当前节点的end值，就代表该元素在树中被插入的次数
 * 4) 删除一个元素（delete）
 *  4.1） 首先查看该元素是否存在于前缀树中，如果不存在，则直接返回
 *  4.2） 如果存在，降待删除元素转换成字符数组，当前节点指向root节点
 *  4.3） 遍历字符数组，查看当前节点的nexts是否包含当前字符，如果包含，则nexts[x].pass -- ,如果在某一个步之后发现节点的pass值为0，降nexts[x]设置为空，直接返回
 *  4.4） 一直跳转到最后一个节点，把当前节点的end减一
 */
public class TrieTree {

    public static class Node {
        private int pass;
        private int end;
        private Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];
        }

    }

    public static class Trie {
        private Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] charArray = word.toCharArray();
            root.pass++;
            Node curNode = root;
            for (int i = 0; i < charArray.length; i++) {
                int cur = charArray[i] - 'a';
                //没有就新建出一个节点
                if (root.nexts[cur] == null) {
                    Node node = new Node();
                    root.nexts[cur] = node;
                }
                curNode = root.nexts[cur];
                curNode.pass++;
            }
            curNode.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] charArray = word.toCharArray();
            Node curNode = root;
            for (int i = 0; i < charArray.length; i++) {
                int cur = charArray[i] - 'a';
                if (curNode.nexts[cur] == null) {
                    return 0;
                }
                curNode = curNode.nexts[cur];
            }
            return curNode.end;

        }

        public void delete(String word){
            if (search(word) <= 0){
                return;
            }
            char[] charArray = word.toCharArray();
            Node curNode = root;
            for (int i = 0; i < charArray.length; i++) {
                int cur = charArray[i] - 'a';
                if ( --curNode.nexts[cur].pass == 0  ){
                    curNode.nexts[cur] = null;
                    return;
                }
                curNode = curNode.nexts[cur];
            }
            curNode.end--;

        }

        public int prefixNum(String word){
            if (word == null){
                return 0;
            }
            char[] charArray = word.toCharArray();
            Node curNode = root;
            for (int i = 0; i < charArray.length; i++) {
                int cur = charArray[i] - 'a';
                if (curNode.nexts[cur] == null){
                    return 0;
                }
                curNode = curNode.nexts[cur];
            }
            return curNode.pass;
        }

    }
}
