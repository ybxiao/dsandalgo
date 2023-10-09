package com.example.dsandalgo.aaaa.basic;

import java.util.ArrayList;

public class SkipListMap {


    public static class SkipListNode<K extends Comparable<K>, V> {
        public K key;
        public V value;
        public ArrayList<SkipListNode<K, V>> nextNodes;


        public SkipListNode(K key, V value) {
            this.key = key;
            this.value = value;
            nextNodes = new ArrayList<SkipListNode<K, V>>();

        }

        public boolean isLessKey(K otherKey) {
            if (key == null && otherKey == null) {
                return false;
            }
            return key == null || (otherKey != null && key.compareTo(otherKey) < 0);
        }

        public boolean isEqual(K otherKey) {
            if (key == null && otherKey == null) return true;
            return key != null && otherKey != null && key.compareTo(otherKey) == 0;

        }

    }

    public static class SkipListNodeMap<K extends Comparable<K>, V> {
        public final double probability = 0.5;
        public SkipListNode<K, V> head;
        public int size;
        public int maxLevel;

        public SkipListNodeMap() {
            head = new SkipListNode<>(null, null);
            //初始化头结点的第零层
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }

        //找到第0层最右的比当前节点小的节点
        public SkipListNode<K, V> mostRightLessNodeInTree(K key) {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = mostRightLessNodeInLevel(key, cur, level--);
            }
            return cur;
        }

        public SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K, V> cur, int level) {
            SkipListNode<K, V> nextNode = cur.nextNodes.get(level);
            while (nextNode != null && nextNode.isLessKey(key)) {
                cur = nextNode;
                nextNode = nextNode.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containKey(K key) {
            if (key == null) {
                return false;
            }
            SkipListNode<K, V> mostLess = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = mostLess.nextNodes.get(0);
            return find != null && find.isEqual(key);
        }

        public void putVal(K key, V value) {
            // 查看跳表中是否有该节点，如果有的话，直接修改值
            // 没有的话，生成跳表节点
            // 1) 随机造层，看当前节点的最大层数
            // 2) 判断当前跳表的最大层数和该节点的层数关系
            // 3) 依次把每层的节点连接好

            if (key == null) {
                return;
            }
            SkipListNode<K, V> mostLess = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = mostLess.nextNodes.get(0);
            if (find != null && find.isEqual(key)) {
                find.value = value;
            } else {
                size++;
                int newNodeLevel = 0;
                while (Math.random() > probability) {
                    newNodeLevel++;
                }
                while (maxLevel < newNodeLevel) {
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K, V> newNode = new SkipListNode<>(key, value);

                for (int i = 0; i < newNodeLevel; i++) {
                    newNode.nextNodes.add(null);
                }
                while (newNodeLevel >= 0) {
                    SkipListNode<K, V> preNode = mostRightLessNodeInLevel(key, head, newNodeLevel);
                    SkipListNode<K, V> next = preNode.nextNodes.get(newNodeLevel);
                    preNode.nextNodes.set(newNodeLevel, newNode);
                    newNode.nextNodes.set(newNodeLevel, next);
                    newNodeLevel--;
                }

            }

        }

        public V get(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> lessNodeInTree = mostRightLessNodeInTree(key);
            SkipListNode<K, V> findNode = lessNodeInTree.nextNodes.get(0);
            return findNode.isEqual(key) ? findNode.value : null;
        }

        public void remove(K key) {
            // 1）先判断跳表中是否存在该key
            // 2）从最高层依次往下进行删除
            // 3）如果当前层删除之后，没有其他元素了，则把当前层消灭掉，降低跳表的层数
            if (containKey(key)) {
                // 首先更新跳表数量
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while (level >= 0) {
                    pre = mostRightLessNodeInLevel(key, pre, level);
                    SkipListNode<K, V> curNode = pre.nextNodes.get(level);
                    SkipListNode<K, V> nextNextNode = curNode.nextNodes.get(level);
                    if (curNode != null && curNode.isEqual(key)) {
                        pre.nextNodes.set(level, nextNextNode);
                    }
                    if (level != 0 && pre == head && pre.nextNodes.get(level) == null) {
                        pre.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;

                }

            }

        }

        public K firstKey() {
            SkipListNode<K, V> kvSkipListNode = head.nextNodes.get(0);
            return kvSkipListNode == null ? null : kvSkipListNode.key;

        }

        public K lastKey() {
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while (level >= 0) {
                cur = cur.nextNodes.get(level);
                while (cur != null) {
                    cur = cur.nextNodes.get(level);
                }
                level--;
            }
            return cur.key;
        }

        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next != null && next.key.equals(key) ? key : less.key;

        }

        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next == null ? null : next.key;


        }

    }
}


