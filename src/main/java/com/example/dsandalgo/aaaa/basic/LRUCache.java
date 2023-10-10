package com.example.dsandalgo.aaaa.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现LRU
 * 关键点：HashMap 装数据的容器
 * capacity 容量
 * size 存量
 * 双向链表
 */
public class LRUCache {

    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> last;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            last = null;
            next = null;
        }
    }

    public static class NodeDoubleLinkedList<K, V> {
        public Node<K, V> head;
        public Node<K, V> tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }


        public void addNode(Node<K, V> node) {
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.last = tail;
                tail.next = node;
                tail = node;
            }
        }

        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }
            if (head == node) {
                head = head.next;
                head.next = null;
            } else {
                node.last.next = node.next;
                node.next.last = node.last;
            }
            tail.next = node;
            node.last = tail;
            node.next = null;
            tail = node;

        }

        public Node<K, V> removeHead() {
            Node<K, V> res = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node<K, V> temp = head.next;
                head.next = null;
                temp.last = null;
                head = temp;

            }
            return res;
        }
    }

    public static class MyCache<K, V> {
        public Map<K, Node<K, V>> keyNodeHashMap;
        public NodeDoubleLinkedList<K, V> nodeList;
        public final int capacity;

        public MyCache(int cap) {
            this.capacity = cap;
            keyNodeHashMap = new HashMap<K, Node<K, V>>();
            nodeList = new NodeDoubleLinkedList<>();
        }

        public void set(K key, V value) {
            if (keyNodeHashMap.containsKey(key)) {
                Node<K, V> kvNode = keyNodeHashMap.get(key);
                kvNode.value = value;
                nodeList.moveNodeToTail(kvNode);
            } else {
                Node<K, V> newNode = new Node<>(key, value);
                keyNodeHashMap.put(key, newNode);
                nodeList.addNode(newNode);
                if (keyNodeHashMap.size() == capacity + 1) {
                    removeUnMostUsedNode();
                }

            }

        }

        private void removeUnMostUsedNode() {
            Node<K, V> kvNode = nodeList.removeHead();
            keyNodeHashMap.remove(kvNode.key);
        }


    }
}
